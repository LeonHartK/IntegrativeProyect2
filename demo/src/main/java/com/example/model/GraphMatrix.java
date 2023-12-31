package com.example.model;

import java.util.*;

public class GraphMatrix<K> implements IGraph<K> {
    private boolean isDirected;
    int time;

    public GraphMatrix(boolean isDirected) {
        this.isDirected = isDirected;
        adjacentMatrix = new MatrizGenerica<>();
        vertexList = new HashMap<>();
    }

    public MatrizGenerica<K, Integer> adjacentMatrix;

    @Override
    public HashMap<K, Vertex<K>> getVertexList() {
        return vertexList;
    }

    public void setVertexList(HashMap<K, Vertex<K>> vertexList) {
        this.vertexList = vertexList;
    }

    HashMap<K, Vertex<K>> vertexList;

    ArrayList<Vertex<K>> forSearch;

    public ArrayList<Vertex<K>> getForSearch() {
        return forSearch;
    }

    public void setForSearch(ArrayList<Vertex<K>> forSearch) {
        this.forSearch = forSearch;
    }

    @Override
    public boolean addVertex(K vertex) {
        if (adjacentMatrix.getValor(vertex, vertex) == null) {
            Vertex<K> v = new Vertex<>(vertex);
            adjacentMatrix.setValor(vertex, vertex, 0);
            vertexList.put(vertex, v);
            return true;
        }
        return false;

    }

    @Override
    public boolean addEdge(K init, K end, int weight, String id) {
        Vertex<K> first = vertexList.get(init);
        Vertex<K> last = vertexList.get(end);
        if (first != null && last != null) {
            if (adjacentMatrix.getValor(init, end) != null) {
                if (weight > adjacentMatrix.getValor(init, end)) {
                    return true;
                }
            }
            if (isDirected) {
                adjacentMatrix.setValor(init, end, weight);
                return first.addEdge(new Edge<>(last, weight, id));
            }
            adjacentMatrix.setValor(init, end, weight);
            adjacentMatrix.setValor(end, init, weight);
            return first.addEdge(new Edge<>(last, weight, id)) && last.addEdge(new Edge<>(first, weight, id));

        }
        return false;
    }

    @Override
    public boolean deleteVertex(K vertex) {
        Vertex<K> vertex1 = vertexList.get(vertex);
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());
        if (vertex1 != null) {
            vertexList.remove(vertex);
            for (int i = 0; i < vertexList.size(); i++) {
                adjacentMatrix.deleteValor(vertex, vertices.get(i).getValue());
            }
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean deleteEdge(K init, K end, String id) {
        Vertex<K> first = vertexList.get(init);
        Vertex<K> last = vertexList.get(end);
        if (first != null && last != null) {
            adjacentMatrix.setValor(init, end, null);
            boolean result1 = vertexList.get(init).deleteEdge(last, id);
            if (isDirected) {
                return result1;
            }
            adjacentMatrix.setValor(end, init, null);
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
        return adjacentMatrix.getValor(init, end) != null;
    }

    @Override
    public void BFS(K ver) {
        Vertex<K> s = vertexList.get(ver);
        if (s == null) {
            return;
        }
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());
        for (Vertex<K> u : vertices) {
            u.color = com.example.model.Color.WHITE;
            u.distance = Integer.MAX_VALUE;
            u.parent = null;
        }
        s.color = com.example.model.Color.GRAY;
        s.distance = 0;
        s.parent = null;
        Queue<Vertex<K>> Q = new LinkedList<>();
        Q.add(s);
        Vertex<K> u;
        while (!Q.isEmpty()) {
            u = Q.remove();
            for (int i = 0; i < vertexList.size(); i++) {
                if (adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue()) != null
                        && adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue()) != 0) {
                    Vertex<K> v = vertices.get(i);
                    if (v.color == com.example.model.Color.WHITE) {
                        v.color = com.example.model.Color.GRAY;
                        v.distance = u.distance + 1;
                        v.parent = u;
                        Q.add(v);
                    }
                }
            }
            u.color = com.example.model.Color.BLACK;
        }
        forSearch = vertices;
    }

    @Override
    public void DFS() {
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());
        for (Vertex<K> u : vertices) {
            u.color = com.example.model.Color.WHITE;
            u.distance = Integer.MAX_VALUE;
            u.parent = null;
        }
        for (Vertex<K> u : vertices) {
            if (u.color == com.example.model.Color.WHITE) {
                DFSvisit(u);
            }
        }
        forSearch = vertices;
    }

    public void DFSvisit(Vertex<K> u) {
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());
        u.color = com.example.model.Color.GRAY;
        for (int i = 0; i < vertices.size(); i++) {
            if (adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue()) != null
                    && adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue()) != 0) {
                Vertex<K> v = vertices.get(i);
                if (v.color == com.example.model.Color.WHITE) {
                    v.parent = u;
                    DFSvisit(v);
                }
            }
        }
        u.color = com.example.model.Color.BLACK;
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
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());
        while (!Q.isEmpty()) {
            Vertex<K> u = Q.remove();
            for (int i = 0; i < vertexList.values().size(); i++) {
                if (adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue()) != null
                        && adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue()) != 0) {
                    Vertex<K> v = vertices.get(i);
                    int weight = adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue());
                    int alt = u.priority + weight;
                    if (alt < v.priority) {
                        dist.put(v, alt);
                        prev.put(v, u);
                        v.parent = u;
                        Q.remove(v);
                        v.priority = alt;
                        Q.add(v);
                    }
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
            for (int i = 0; i < vertexList.values().size(); i++) {
                if (adjacentMatrix.getValor(u.getValue(), vrtx.get(i).getValue()) != null
                        && adjacentMatrix.getValor(u.getValue(), vrtx.get(i).getValue()) != 0) {
                    Vertex<K> v = vrtx.get(i);
                    int p = adjacentMatrix.getValor(u.getValue(), vrtx.get(i).getValue());
                    vI = vrtx.indexOf(v);
                    dist[uI][vI] = p;
                    parents.setValor(vrtx.get(uI), vrtx.get(vI), u);
                }
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
            u.color = com.example.model.Color.WHITE;
            u.parent = null;
        }
        ArrayList<Vertex<K>> as = new ArrayList<>(vertexList.values());
        PriorityQueue<Integer> QU = new PriorityQueue<>();
        Integer minummEdge = Integer.MAX_VALUE;
        Vertex<K> r = new Vertex<>(null);
        for (int i = 0; i < as.size(); i++) {
            for (Vertex<K> a : as) {
                if (adjacentMatrix.getValor(as.get(i).getValue(), a.getValue()) != null) {
                    if (adjacentMatrix.getValor(as.get(i).getValue(), a.getValue()) < minummEdge) {
                        minummEdge = adjacentMatrix.getValor(as.get(i).getValue(), a.getValue());
                        r = as.get(i);
                    }
                }
            }
        }
        r.distance = 0;
        r.priority = 0;
        PriorityQueue<Vertex<K>> Q = new PriorityQueue<Vertex<K>>(Comparator.comparingInt(o -> o.priority));
        Q.addAll(vertexList.values());
        while (!Q.isEmpty()) {
            Vertex<K> u = Q.remove();
            for (int i = 0; i < vertexList.values().size(); i++) {
                if (adjacentMatrix.getValor(u.getValue(), as.get(i).getValue()) != null
                        && adjacentMatrix.getValor(u.getValue(), as.get(i).getValue()) != 0) {
                    Vertex<K> v = as.get(i);
                    int weight = adjacentMatrix.getValor(u.getValue(), as.get(i).getValue());
                    if (v.color == com.example.model.Color.WHITE && weight < v.priority) {
                        Q.remove(v);
                        v.priority = weight;
                        Q.add(v);
                        v.parent = u;
                    }
                }
            }
            u.color = com.example.model.Color.BLACK;
        }
    }

    @Override
    public ArrayList<Pair<Vertex<K>, Edge<K>>> Kruskal() {
        ArrayList<Vertex<K>> vertices = new ArrayList<>(vertexList.values());
        ArrayList<Pair<Vertex<K>, Edge<K>>> a = new ArrayList<>();
        for (Vertex<K> u : vertices) {
            for (int i = 0; i < vertexList.values().size(); i++) {
                if (adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue()) != null
                        && adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue()) != 0) {
                    a.add(new Pair<>(u, new Edge<>(vertices.get(i),
                            adjacentMatrix.getValor(u.getValue(), vertices.get(i).getValue()), "")));
                }
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