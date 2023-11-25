# Especificación del Método BFS

## Descripción
Este método realiza un recorrido BFS (Breadth-First Search) en un grafo a partir de un vértice dado. Asigna colores, distancias y padres a los vértices del grafo según su participación en el recorrido BFS.

## Parámetros
- `ver`: Tipo K - Valor del vértice de inicio del recorrido BFS.

## Variables Internas
- `s`: `Vertex<K>` - Vértice de inicio obtenido del grafo utilizando el valor `ver`.
- `vertices`: `ArrayList<Vertex<K>>` - Lista de todos los vértices del grafo.
- `Q`: `Queue<Vertex<K>>` - Cola utilizada para el recorrido BFS.
- `u`: `Vertex<K>` - Vértice actualmente en proceso durante el recorrido BFS.

## Precondiciones
- El grafo está representado por un objeto de la clase que contiene este método.

## Postcondiciones
- Los vértices del grafo tienen colores, distancias y padres asignados según su participación en el recorrido BFS.

## Algoritmo
1. Obtener el vértice de inicio `s` utilizando el valor `ver` en el mapa `vertexList`.
2. Si `s` es nulo, terminar el método.
3. Crear una lista `vertices` que contiene todos los vértices del grafo.
4. Para cada vértice `u` en `vertices`:
   - Asignar `WHITE` al color de `u`.
   - Asignar `Integer.MAX_VALUE` a la distancia de `u`.
   - Asignar `null` al padre de `u`.
5. Asignar `GRAY` al color de `s`.
6. Asignar 0 a la distancia de `s`.
7. Asignar `null` al padre de `s`.
8. Crear una cola `Q` e insertar `s` en la cola.
9. Mientras `Q` no esté vacía:
   - Extraer un vértice `u` de la cola `Q`.
   - Para cada borde `a` en la lista de bordes adyacentes de `u`:
     - Obtener el vértice adyacente `v` del borde `a`.
     - Si el color de `v` es `WHITE`:
       - Asignar `GRAY` al color de `v`.
       - Asignar la distancia de `u` más 1 a la distancia de `v`.
       - Asignar `u` como el padre de `v`.
       - Insertar `v` en la cola `Q`.
   - Asignar `BLACK` al color de `u`.
10. Asignar la lista `vertices` a la variable `forSearch` para su uso externo.

## Excepciones
- Ninguna.

## Ejemplo de Uso
```java
Graph<Integer> graph = new Graph<>(true);
// Agregar vértices y bordes al grafo...
graph.BFS(1);
// Después de llamar al método BFS, los vértices del grafo tendrán colores, distancias y padres asignados según el recorrido BFS.
