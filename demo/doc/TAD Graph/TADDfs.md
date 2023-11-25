# Especificación del Método DFS

## Descripción
Este método realiza un recorrido DFS (Depth-First Search) en un grafo. Asigna colores, distancias y padres a los vértices del grafo según su participación en el recorrido DFS.

## Variables Internas
- `vertices`: `ArrayList<Vertex<K>>` - Lista de todos los vértices del grafo.

## Precondiciones
- El grafo está representado por un objeto de la clase que contiene este método.

## Postcondiciones
- Los vértices del grafo tienen colores, distancias y padres asignados según su participación en el recorrido DFS.

## Algoritmo
1. Crear una lista `vertices` que contiene todos los vértices del grafo.
2. Para cada vértice `u` en `vertices`:
   - Asignar `WHITE` al color de `u`.
   - Asignar `Integer.MAX_VALUE` a la distancia de `u`.
   - Asignar `null` al padre de `u`.
3. Para cada vértice `u` en `vertices`:
   - Si el color de `u` es `WHITE`:
     - Llamar al método `DFSvisit(u)` para realizar el recorrido DFS desde `u`.
4. Asignar la lista `vertices` a la variable `forSearch` para su uso externo.

## Método Interno
### `DFSvisit(u: Vertex<K>)`
- **Descripción:** Este método auxiliar realiza el recorrido DFS a partir de un vértice `u`. Marca el vértice como visitado, y para cada vértice adyacente no visitado, llama recursivamente a `DFSvisit`.
- **Parámetros:**
  - `u`: `Vertex<K>` - Vértice actual en el recorrido DFS.
- **Variables Internas:**
  - Ninguna.
- **Precondiciones:**
  - El grafo está representado por un objeto de la clase que contiene este método.
- **Postcondiciones:**
  - Los vértices del grafo tienen colores, distancias y padres asignados según su participación en el recorrido DFS desde `u`.

## Excepciones
- Ninguna.

## Ejemplo de Uso
```java
Graph<Integer> graph = new Graph<>(true);
// Agregar vértices y bordes al grafo...
graph.DFS();
// Después de llamar al método DFS, los vértices del grafo tendrán colores, distancias y padres asignados según el recorrido DFS.
