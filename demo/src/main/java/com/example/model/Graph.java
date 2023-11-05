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
}
