# Especificación del Método Prim

## Descripción
Este método implementa el algoritmo de Prim para encontrar el árbol de expansión mínima de un grafo ponderado y no dirigido.

## Variables Internas
- `as`: `ArrayList<Vertex<K>>` - Lista de todos los vértices del grafo.
- `QU`: `PriorityQueue<Edge<K>>` - Cola de prioridad de bordes, ordenada por peso.
- `minummEdge`: `Edge<K>` - Borde mínimo extraído de `QU`.
- `r`: `Vertex<K>` - Vértice de inicio del árbol de expansión mínima.

## Precondiciones
- El grafo está representado por un objeto de la clase que contiene este método.
- El grafo es ponderado y no dirigido.

## Postcondiciones
- Los vértices del árbol de expansión mínima tienen padres asignados.

## Algoritmo
1. Para cada vértice `u` en el grafo:
   - Inicializar las propiedades `distance`, `priority`, `color` y `parent`.
2. Crear una lista `as` que contiene todos los vértices del grafo.
3. Crear una cola de prioridad `QU` de bordes, ordenada por peso.
4. Agregar todos los bordes de los vértices en `as` a `QU`.
5. Extraer el borde mínimo `minummEdge` de `QU`.
6. Establecer el vértice `r` como el vértice asociado a `minummEdge`.
7. Inicializar las propiedades `distance` y `priority` de `r` como 0.
8. Crear una cola de prioridad `Q` de vértices, ordenada por `priority`.
9. Agregar todos los vértices del grafo a `Q`.
10. Mientras `Q` no esté vacía:
    - Extraer el vértice `u` con menor prioridad de `Q`.
    - Para cada borde `v` en la lista de bordes adyacentes de `u`:
        - Si el vértice asociado a `v` tiene color blanco y el peso de `v` es menor que la prioridad del vértice asociado a `v`:
            - Actualizar la prioridad del vértice asociado a `v` con el peso de `v`.
            - Actualizar el padre del vértice asociado a `v` con `u`.
            - Eliminar el vértice asociado a `v` de `Q`.
            - Agregar el vértice asociado a `v` a `Q`.
    - Establecer el color de `u` como negro.

## Excepciones
- Ninguna.

## Ejemplo de Uso
```java
Graph<Integer> graph = new Graph<>(false);
// Agregar vértices y bordes ponderados al grafo...
graph.Prim();
// Después de llamar al método Prim, los vértices del grafo tendrán padres asignados, formando el árbol de expansión mínima.
