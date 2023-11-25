# Especificación del Método Floyd-Warshall

## Descripción
Este método implementa el algoritmo de Floyd-Warshall para encontrar las distancias más cortas entre todos los pares de vértices en un grafo ponderado dirigido o no dirigido.

## Variables Internas
- `vrtx`: `ArrayList<Vertex<K>>` - Lista de todos los vértices del grafo.
- `dist`: `int[][]` - Matriz que almacena las distancias más cortas entre todos los pares de vértices.
- `parents`: `MatrizGenerica<Vertex<K>, Vertex<K>>` - Matriz que almacena los vértices padres para reconstruir los caminos más cortos.

## Precondiciones
- El grafo está representado por un objeto de la clase que contiene este método.
- El grafo es ponderado.

## Postcondiciones
- `dist` contiene las distancias más cortas entre todos los pares de vértices.
- `parents` contiene los vértices padres para reconstruir los caminos más cortos.

## Algoritmo
1. Crear una lista `vrtx` que contiene todos los vértices del grafo.
2. Crear una matriz `dist` de tamaño `vertexList.size() x vertexList.size()` e inicializar todas las entradas con `Integer.MAX_VALUE`.
3. Crear una matriz `parents` utilizando la clase `MatrizGenerica` e inicializar todas las entradas con `null`.
4. Para cada vértice `u` en `vrtx`:
   - Inicializar la entrada `dist[u][u]` con 0 y la entrada correspondiente en `parents` con `u`.
5. Para cada borde `(u, v)` en el grafo:
   - Asignar la entrada `dist[u][v]` con el peso del borde `(u, v)` y la entrada correspondiente en `parents` con `u`.
6. Para cada vértice `k` en `vrtx`:
   - Para cada vértice `i` en `vrtx`:
     - Para cada vértice `j` en `vrtx`:
       - Calcular la distancia alternativa `dis` desde `i` hasta `j` a través de `k`.
       - Actualizar `dist[i][j]` y `parents[i][j]` si `dis` es menor que la distancia actual.
7. Devolver un objeto `Pair` que contiene la matriz `dist` y la matriz `parents`.

## Excepciones
- Ninguna.

## Ejemplo de Uso
```java
Graph<Integer> graph = new Graph<>(true);
// Agregar vértices y bordes ponderados al grafo...
Pair<int[][], MatrizGenerica<Vertex<Integer>, Vertex<Integer>>> result = graph.floyWarshall();
// Después de llamar al método floyWarshall, result contiene las matrices dist y parents con las distancias más cortas y los vértices padres respectivamente.
