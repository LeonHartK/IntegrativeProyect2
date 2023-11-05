# Especificación del TAD Vertex<K>

## Representación
- `value`: Tipo K - Valor asociado al vértice.
- `priority`: Entero - Prioridad utilizada en algoritmos como Dijkstra.
- `parent`: `Vertex<K>` - Vértice padre en recorridos.
- `distance`: Entero - Distancia desde el vértice origen.
- `color`: `Color` - Color del vértice en algoritmos de búsqueda.
- `adjacentList`: `ArrayList<Edge<K>>` - Lista de bordes adyacentes.

## Operaciones

1. `Vertex(value: K)`: Constructor que crea un vértice con un valor especificado.
   - **Entrada:** Un valor de tipo K.
   - **Salida:** Un nuevo objeto `Vertex` con el valor especificado y valores iniciales para otras propiedades.

2. `getValue()`: Devuelve el valor asociado al vértice.
   - **Salida:** El valor de tipo K asociado al vértice.

3. `setValue(value: K)`: Establece el valor del vértice.
   - **Entrada:** Un nuevo valor de tipo K.
   - **Salida:** Ninguna.

4. `getPriority()`: Devuelve la prioridad del vértice (utilizado en algoritmos como Dijkstra).
   - **Salida:** Un entero que representa la prioridad.

5. `getParent()`: Devuelve el vértice padre en recorridos.
   - **Salida:** Un objeto `Vertex<K>` que es el padre del vértice.

6. `setParent(parent: Vertex<K>)`: Establece el vértice padre.
   - **Entrada:** Un objeto `Vertex<K>` que se convertirá en el padre del vértice.
   - **Salida:** Ninguna.

7. `getDistance()`: Devuelve la distancia desde el vértice origen.
   - **Salida:** Un entero que representa la distancia.

8. `setDistance(distance: int)`: Establece la distancia desde el vértice origen.
   - **Entrada:** Un entero que representa la nueva distancia.
   - **Salida:** Ninguna.

9. `getColor()`: Devuelve el color del vértice (utilizado en algoritmos de búsqueda).
   - **Salida:** Un valor del tipo `Color` que indica el color del vértice.

10. `setColor(color: Color)`: Establece el color del vértice.
    - **Entrada:** Un valor del tipo `Color` que representa el nuevo color.
    - **Salida:** Ninguna.

11. `getAdjacentList()`: Devuelve la lista de bordes adyacentes al vértice.
    - **Salida:** Una lista de objetos `Edge<K>`.

12. `existVertex(vertex: Vertex<K>)`: Comprueba si existe un vértice adyacente con un vértice dado.
    - **Entrada:** Un objeto `Vertex<K>` a verificar.
    - **Salida:** Verdadero si el vértice adyacente existe, falso en caso contrario.

13. `addEdge(vertex: Edge<K>)`: Agrega un borde al vértice.
    - **Entrada:** Un objeto `Edge<K>` que se agregará a la lista de bordes adyacentes.
    - **Salida:** Verdadero si se agregó correctamente.

14. `getWeight(vertex: Vertex<K>)`: Obtiene el peso del borde entre el vértice actual y un vértice dado.
    - **Entrada:** Un objeto `Vertex<K>` representando el vértice destino.
    - **Salida:** El peso del borde entre el vértice actual y el vértice destino.

15. `deleteEdge(v: Vertex<K>, id: String)`: Elimina un borde entre el vértice actual y un vértice dado (opcionalmente, con un identificador).
    - **Entrada:** Un objeto `Vertex<K>` representando el vértice destino y opcionalmente una cadena de identificación `id`.
    - **Salida:** Verdadero si se eliminó el borde correctamente, falso si no se encontró.

16. `getEdge(vertex: Vertex<K>)`: Obtiene el vértice adyacente con el mismo valor que un vértice dado.
    - **Entrada:** Un objeto `Vertex<K>` representando el vértice destino.
    - **Salida:** El vértice adyacente con el mismo valor, o nulo si no se encuentra.

17. `findEdge(to: Vertex<K>)`: Encuentra el borde con un vértice destino específico y el peso más bajo.
    - **Entrada:** Un objeto `Vertex<K>` representando el vértice destino.
    - **Salida:** Un objeto `Edge<K>` que es el borde con el vértice destino y el peso más bajo.

18. `selfDeleteVertex()`: Elimina el vértice actual y todos sus bordes adyacentes.
    - **Salida:** Ninguna.

**Nota:** La estructura de datos `Color` se asume como una enumeración (enum) que define los valores posibles de color utilizados en algoritmos de búsqueda.
