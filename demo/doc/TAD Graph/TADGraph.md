# Especificación del TAD Graph<K>

## Representación
- `vertexList`: `HashMap<K, Vertex<K>>` - Un mapa que mapea claves de tipo K a objetos `Vertex<K>`.
- `forSearch`: `ArrayList<Vertex<K>>` - Una lista de vértices para realizar búsquedas.
- `isDirected`: `boolean` - Un indicador de si el grafo es dirigido o no.
- `time`: `int` - Un contador de tiempo para ciertas operaciones.

## Invariante
- Los vértices en `vertexList` son únicos y no nulos.
- Los bordes en el grafo se gestionan a través de la estructura `Vertex` y `Edge`, asegurando que sean consistentes.

## Operaciones

1. `Graph(isDirected: boolean)`: Crea un nuevo grafo con la dirección especificada.
   - **Entrada:** Un valor booleano `isDirected` que indica si el grafo es dirigido o no.
   - **Salida:** Un nuevo objeto `Graph` con la dirección especificada.

2. `getVertexList()`: Devuelve el mapa de vértices `vertexList`.
   - **Salida:** Un mapa que mapea claves de tipo K a objetos `Vertex<K>`.

3. `getForSearch()`: Devuelve la lista `forSearch`.
   - **Salida:** Una lista de vértices para búsquedas.

4. `setForSearch(forSearch: ArrayList<Vertex<K>>)`: Establece la lista `forSearch`.
   - **Entrada:** Una lista `forSearch` de vértices.
   - **Salida:** Ninguna.

5. `addVertex(vertex: K)`: Agrega un vértice con el valor especificado al grafo.
   - **Entrada:** Un valor `vertex` de tipo K para el nuevo vértice.
   - **Salida:** Verdadero si el vértice se agregó correctamente, falso si ya existe.

6. `addVertex(vertex: Vertex<K>)`: Agrega un vértice dado al grafo.
   - **Entrada:** Un objeto `vertex` de tipo `Vertex<K>`.
   - **Salida:** Verdadero si el vértice se agregó correctamente, falso si ya existe.

7. `addEdge(init: K, end: K, weight: int, id: String)`: Agrega un borde entre dos vértices con pesos y un identificador.
   - **Entrada:** Valores `init` y `end` de tipo K para los vértices de inicio y final, un valor entero `weight` para el peso del borde y una cadena `id` como identificador.
   - **Salida:** Verdadero si el borde se agregó correctamente, falso si los vértices no existen.

8. `deleteVertex(vertex: K)`: Elimina un vértice del grafo.
   - **Entrada:** Un valor `vertex` de tipo K que representa el vértice a eliminar.
   - **Salida:** Verdadero si el vértice se eliminó correctamente, falso si el vértice no existe.

9. `deleteEdge(init: K, end: K, id: String)`: Elimina un borde entre dos vértices con un identificador dado.
   - **Entrada:** Valores `init` y `end` de tipo K para los vértices de inicio y final, y una cadena `id` como identificador del borde.
   - **Salida:** Verdadero si el borde se eliminó correctamente, falso si los vértices o el borde no existen.

10. `vertexExists(vertex: K)`: Comprueba si un vértice con el valor especificado existe en el grafo.
   - **Entrada:** Un valor `vertex` de tipo K para el vértice a verificar.
   - **Salida:** Verdadero si el vértice existe, falso en caso contrario.

11. `edgeExists(init: K, end: K)`: Comprueba si existe un borde entre dos vértices con valores `init` y `end`.
   - **Entrada:** Valores `init` y `end` de tipo K para los vértices de inicio y final.
   - **Salida:** Verdadero si el borde existe, falso en caso contrario.

12. `BFS(ver: K)`: Realiza un recorrido BFS (Breadth-First Search) en el grafo a partir de un vértice dado.
   - **Entrada:** Un valor `ver` de tipo K para el vértice de inicio.
   - **Salida:** Ninguna.

13. `DFS()`: Realiza un recorrido DFS (Depth-First Search) en el grafo.
   - **Entrada:** Ninguna.
   - **Salida:** Ninguna.

14. `dijsktra(value: K)`: Calcula las distancias más cortas desde un vértice de origen hasta todos los demás vértices en el grafo utilizando el algoritmo de Dijkstra.
   - **Entrada:** Un valor `value` de tipo K para el vértice de origen.
   - **Salida:** Un par de mapas que representan las distancias más cortas y los predecesores de los vértices en el grafo.

Además de estas operaciones, existen algunas operaciones internas utilizadas en la implementación, como `DFSvisit` y otras operaciones auxiliares que no se han incluido en esta especificación, pero que son necesarias para el funcionamiento interno del grafo.
