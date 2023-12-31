package com.example.model;

import java.util.*;

public class Graph<K> implements IGraph<K> {
    HashMap<K, Vertex<K>> vertexList;
    ArrayList<Vertex<K>> forSearch;
    private boolean isDirected;
    int time;

    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
        vertexList = new HashMap<>();
    }

    @Override
    public HashMap<K, Vertex<K>> getVertexList() {
        return vertexList;
    }

    public ArrayList<Vertex<K>> getForSearch() {
        return forSearch;
    }

    public void setForSearch(ArrayList<Vertex<K>> forSearch) {
        this.forSearch = forSearch;
    }

    @Override
    public boolean addVertex(K vertex) {
        if (vertexList.get(vertex) == null) {
            vertexList.put(vertex, new Vertex<>(vertex));
            return true;
        }
        return false;
    }

    public boolean addVertex(Vertex<K> vertex) {
        if (vertexList.get(vertex.getValue()) == null) {
            vertexList.put(vertex.getValue(), vertex);
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(K init, K end, int weight, String id) {
        Vertex<K> first = vertexList.get(init);
        Vertex<K> last = vertexList.get(end);
        if (first != null && last != null) {
            if (isDirected) {
                return first.addEdge(new Edge<>(last, weight, id));
            }
            return first.addEdge(new Edge<>(last, weight, id)) && last.addEdge(new Edge<>(first, weight, id));
        }
        return false;
    }

    @Override
    public boolean deleteVertex(K vertex) {
        Vertex<K> vertex1 = vertexList.get(vertex);
        if (vertex1 != null) {
            vertex1.selfDeleteVertex();
            vertexList.remove(vertex);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEdge(K init, K end, String id) {
        Vertex<K> first = vertexList.get(init);
        Vertex<K> last = vertexList.get(end);
        if (first != null && last != null) {
            boolean result1 = vertexList.get(init).deleteEdge(last, id);
            if (isDirected) {
                return result1;
            }
            boolean result2 = vertexList.get(end).deleteEdge(first, id);
            return result2 && result1;
        }
        return false;
    }

    @Override
    public boolean vertexExists(K vertex) {
        return vertexList.get(vertex) != null;
    }

    @Override
    public boolean edgeExists(K init, K end) {
        Vertex<K> first = vertexList.get(init);
        Vertex<K> last = vertexList.get(end);
        if (first != null && last != null) {
            if (isDirected) {
                return first.existVertex(last);
            }
            return first.existVertex(last) || last.existVertex(first);
        }
        return false;
    }

    @Override
    public void BFS(K ver) {
        Vertex<K> s = vertexList.get(ver);
        if (s == null) {
            return;
        }
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());

        for (Vertex<K> u : vertices) {
            u.color = Color.WHITE;
            u.distance = Integer.MAX_VALUE;
            u.parent = null;
        }
        s.color = Color.GRAY;
        s.distance = 0;
        s.parent = null;
        Queue<Vertex<K>> Q = new LinkedList<>();
        Q.add(s);
        Vertex<K> u;
        while (!Q.isEmpty()) {
            u = Q.remove();
            for (Edge<K> a : u.adjacentList) {
                Vertex<K> v = a.getVertex();
                if (v.color == Color.WHITE) {
                    v.color = Color.GRAY;
                    v.distance = u.distance + 1;
                    v.parent = u;
                    Q.add(v);
                }
            }
            u.color = Color.BLACK;
        }
        forSearch = vertices;
    }

    @Override
    public void DFS() {
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());
        for (Vertex<K> u : vertices) {
            u.color = Color.WHITE;
            u.distance = Integer.MAX_VALUE;
            u.parent = null;
        }
        for (Vertex<K> u : vertices) {
            if (u.color == Color.WHITE) {
                DFSvisit(u);
            }
        }
        forSearch = vertices;
    }

    public void DFSvisit(Vertex<K> u) {
        u.color = Color.GRAY;
        for (Edge<K> a : u.adjacentList) {
            Vertex<K> v = a.getVertex();
            if (v.color == Color.WHITE) {
                v.parent = u;
                DFSvisit(v);
            }
        }
        u.color = Color.BLACK;
    }

    @Override
    public Pair<HashMap<Vertex<K>, Integer>, HashMap<Vertex<K>, Vertex<K>>> dijsktra(K value) {
        Vertex<K> source = vertexList.get(value);
        HashMap<Vertex<K>, Integer> dist = new HashMap<>();
        HashMap<Vertex<K>, Vertex<K>> prev = new HashMap<>();
        dist.put(source, 0);
        source.priority = 0;
        PriorityQueue<Vertex<K>> Q = new PriorityQueue<>(Comparator.comparingInt(o -> o.priority));
        for (Vertex<K> v : vertexList.values()) {
            if (!v.equals(source)) {
                v.priority = Integer.MAX_VALUE;
                dist.put(v, Integer.MAX_VALUE);
                prev.put(v, null);
                v.parent = null;
            }
            Q.add(v);
        }

        while (!Q.isEmpty()) {
            Vertex<K> u = Q.remove();
            for (Edge<K> v : u.adjacentList) {
                int alt = u.priority + v.getWeight();
                if (alt < v.getVertex().priority) {
                    dist.put(v.getVertex(), alt);
                    prev.put(v.getVertex(), u);
                    v.getVertex().parent = u;
                    Q.remove(v.getVertex());
                    v.getVertex().priority = alt;
                    Q.add(v.getVertex());
                }
            }
        }
        return new Pair<>(dist, prev);
    }

    @Override
    public Pair<int[][], MatrizGenerica<Vertex<K>, Vertex<K>>> floyWarshall() {
        ArrayList<Vertex<K>> vrtx = new ArrayList<>(vertexList.values());
        int[][] dist = new int[vertexList.size()][vertexList.size()];
        MatrizGenerica<Vertex<K>, Vertex<K>> parents = new MatrizGenerica<>();
        for (int i = 0; i < vrtx.size(); i++) {
            for (int j = 0; j < vrtx.size(); j++) {
                dist[i][j] = Integer.MAX_VALUE;
                parents.setValor(vrtx.get(i), vrtx.get(j), null);
            }
            dist[i][i] = 0;
            parents.setValor(vrtx.get(i), vrtx.get(i), vrtx.get(i));
        }
        int uI;
        int vI;
        for (Vertex<K> u : vrtx) {
            uI = vrtx.indexOf(u);
            for (Edge<K> p : u.getAdjacentList()) {
                Vertex<K> v = p.getVertex();
                vI = vrtx.indexOf(v);
                dist[uI][vI] = p.getWeight();
                parents.setValor(vrtx.get(uI), vrtx.get(vI), u);
            }
        }
        for (int k = 0; k < vrtx.size(); k++) {
            for (int i = 0; i < vrtx.size(); i++) {
                for (int j = 0; j < vrtx.size(); j++) {
                    int dis;
                    if (dist[i][k] == Integer.MAX_VALUE || dist[k][j] == Integer.MAX_VALUE) {
                        dis = Integer.MAX_VALUE;
                    } else {
                        dis = dist[i][k] + dist[k][j];
                    }
                    if (dist[i][j] > dis) {
                        dist[i][j] = dis;
                        parents.setValor(vrtx.get(i), vrtx.get(j), parents.getValor(vrtx.get(k), vrtx.get(j)));
                    }
                }
            }
        }
        return new Pair<>(dist, parents);
    }

    @Override
    public void Prim() {
        for (Vertex<K> u : vertexList.values()) {
            u.distance = Integer.MAX_VALUE;
            u.priority = Integer.MAX_VALUE;
            u.color = Color.WHITE;
            u.parent = null;
        }
        ArrayList<Vertex<K>> as = new ArrayList<>(vertexList.values());
        PriorityQueue<Edge<K>> QU = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        for (Vertex<K> a : as) {
            QU.addAll(a.getAdjacentList());
        }
        Edge<K> minummEdge = QU.remove();
        Vertex<K> r = minummEdge.getVertex();
        r.distance = 0;
        r.priority = 0;
        PriorityQueue<Vertex<K>> Q = new PriorityQueue<Vertex<K>>(Comparator.comparingInt(o -> o.priority));
        Q.addAll(vertexList.values());
        while (!Q.isEmpty()) {
            Vertex<K> u = Q.remove();
            for (Edge<K> v : u.adjacentList) {
                if (v.getVertex().color == Color.WHITE && v.getWeight() < v.getVertex().priority) {
                    Q.remove(v.getVertex());
                    v.getVertex().priority = v.getWeight();
                    Q.add(v.getVertex());
                    v.getVertex().parent = u;
                }
            }
            u.color = Color.BLACK;
        }
    }

    @Override
    public ArrayList<Pair<Vertex<K>, Edge<K>>> Kruskal() {
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());
        ArrayList<Pair<Vertex<K>, Edge<K>>> a = new ArrayList<>();
        for (Vertex<K> vertex : vertices) {
            for (Edge<K> edge : vertex.adjacentList) {
                a.add(new Pair<>(vertex, edge));
            }
        }
        Comparator<Pair<Vertex<K>, Edge<K>>> pairComparator = (o1, o2) -> o1.getValue2().getWeight()
                - o2.getValue2().getWeight();

        a.sort(pairComparator);

        DisjointSet<Vertex<K>> disjointSet = new DisjointSet<>();
        for (Vertex<K> vertex : vertices) {
            disjointSet.makeSet(vertex);
        }

        ArrayList<Pair<Vertex<K>, Edge<K>>> mst = new ArrayList<>();

        for (Pair<Vertex<K>, Edge<K>> edge : a) {
            Vertex<K> u = edge.getValue1();
            Vertex<K> v = edge.getValue2().getVertex();
            if (disjointSet.findSet(u) != disjointSet.findSet(v)) {
                mst.add(edge);
                disjointSet.union(u, v);
            }
        }

        return mst;

    }
}
