# Especificación del Método Dijkstra

## Descripción
Este método implementa el algoritmo de Dijkstra para encontrar las distancias más cortas desde un vértice origen a todos los demás vértices en un grafo ponderado.

## Parámetros
- `value`: Tipo K - Valor del vértice origen para el algoritmo de Dijkstra.

## Variables Internas
- `source`: `Vertex<K>` - Vértice origen obtenido del grafo utilizando el valor `value`.
- `dist`: `HashMap<Vertex<K>, Integer>` - Mapa que almacena las distancias más cortas desde el vértice origen a cada vértice.
- `prev`: `HashMap<Vertex<K>, Vertex<K>>` - Mapa que almacena los padres de cada vértice en el camino más corto desde el vértice origen.
- `Q`: `PriorityQueue<Vertex<K>>` - Cola de prioridad utilizada para seleccionar vértices con menor prioridad.

## Precondiciones
- El grafo está representado por un objeto de la clase que contiene este método.
- El grafo es ponderado y dirigido.

## Postcondiciones
- `dist` contiene las distancias más cortas desde el vértice origen a todos los demás vértices.
- `prev` contiene los padres de cada vértice en el camino más corto desde el vértice origen.

## Algoritmo
1. Obtener el vértice origen `source` utilizando el valor `value` en el mapa `vertexList`.
2. Crear un mapa `dist` para almacenar las distancias más cortas y un mapa `prev` para almacenar los padres.
3. Inicializar `dist` con el valor 0 para `source` y `Integer.MAX_VALUE` para todos los demás vértices.
4. Establecer la prioridad de `source` como 0.
5. Crear una cola de prioridad `Q` con comparador basado en la prioridad de los vértices.
6. Para cada vértice `v` en el grafo:
   - Si `v` no es igual a `source`:
     - Establecer la prioridad de `v` como `Integer.MAX_VALUE`.
     - Inicializar `dist(v)` con `Integer.MAX_VALUE`.
     - Establecer el padre de `v` como nulo.
     - Establecer el padre de `v` en `prev(v)` como nulo.
     - Establecer la prioridad de `v` en la cola `Q`.
7. Mientras `Q` no esté vacía:
   - Extraer el vértice `u` con la menor prioridad de `Q`.
   - Para cada borde `v` en la lista de bordes adyacentes de `u`:
     - Calcular la distancia alternativa `alt` desde `source` hasta `v` a través de `u`.
     - Si `alt` es menor que la prioridad actual de `v`:
       - Actualizar `dist(v)`, `prev(v)` y la prioridad de `v`.
       - Remover y volver a agregar `v` en la cola `Q` con su nueva prioridad.
8. Devolver un objeto `Pair` que contiene los mapas `dist` y `prev`.

## Excepciones
- Ninguna.

## Ejemplo de Uso
```java
Graph<Integer> graph = new Graph<>(true);
// Agregar vértices y bordes ponderados al grafo...
Pair<HashMap<Vertex<Integer>, Integer>, HashMap<Vertex<Integer>, Vertex<Integer>>> result = graph.dijsktra(1);
// Después de llamar al método dijsktra, result contiene los mapas dist y prev con las distancias más cortas y los padres respectivamente.
