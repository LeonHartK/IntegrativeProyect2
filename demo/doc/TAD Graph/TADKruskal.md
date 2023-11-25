# Especificación del Método Kruskal

## Descripción
Este método implementa el algoritmo de Kruskal para encontrar el árbol de expansión mínima de un grafo ponderado y no dirigido.

## Variables Internas
- `vertices`: `ArrayList<Vertex<K>>` - Lista de todos los vértices del grafo.
- `a`: `ArrayList<Pair<Vertex<K>, Edge<K>>>` - Lista de pares que contienen un vértice y un borde del grafo.
- `pairComparator`: `Comparator<Pair<Vertex<K>, Edge<K>>>` - Comparador utilizado para ordenar la lista `a` por pesos de bordes.
- `disjointSet`: `DisjointSet<Vertex<K>>` - Conjunto disjunto para realizar operaciones de conjunto.
- `mst`: `ArrayList<Pair<Vertex<K>, Edge<K>>>` - Lista de pares que forman el árbol de expansión mínima.

## Precondiciones
- El grafo está representado por un objeto de la clase que contiene este método.
- El grafo es ponderado y no dirigido.

## Postcondiciones
- `mst` contiene los vértices y bordes que forman el árbol de expansión mínima.

## Algoritmo
1. Crear una lista `vertices` que contiene todos los vértices del grafo.
2. Crear una lista `a` que contiene pares de vértices y bordes del grafo.
3. Crear un comparador `pairComparator` para ordenar la lista `a` por pesos de bordes.
4. Ordenar la lista `a` utilizando el comparador `pairComparator`.
5. Crear un conjunto disjunto `disjointSet`.
6. Para cada vértice `vertex` en `vertices`:
   - Crear un conjunto en `disjointSet` para el vértice `vertex`.
7. Crear una lista `mst` para almacenar los pares de vértices y bordes del árbol de expansión mínima.
8. Para cada par `edge` en la lista `a`:
   - Obtener los vértices `u` y `v` del par `edge`.
   - Si los conjuntos de `u` y `v` en `disjointSet` son diferentes:
      - Agregar el par `edge` a la lista `mst`.
      - Unir los conjuntos de `u` y `v` en `disjointSet`.
9. Devolver la lista `mst`.

## Excepciones
- Ninguna.

## Ejemplo de Uso
```java
Graph<Integer> graph = new Graph<>(false);
// Agregar vértices y bordes ponderados al grafo...
ArrayList<Pair<Vertex<Integer>, Edge<Integer>>> mst = graph.Kruskal();
// Después de llamar al método Kruskal, mst contiene los vértices y bordes que forman el árbol de expansión mínima.
