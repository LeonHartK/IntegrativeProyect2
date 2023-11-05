# Especificación del TAD Edge<K>

## Representación
- `id`: Cadena de texto - Identificador del borde (puede ser nulo).
- `vertex`: `Vertex<K>` - Vértice destino del borde.
- `weight`: Entero - Peso del borde.

## Operaciones

1. `Edge(vertex: Vertex<K>, weight: int, id: String)`: Constructor que crea un borde entre un vértice origen y un vértice destino con un peso y un identificador especificados.
   - **Entrada:** Un objeto `Vertex<K>` representando el vértice destino, un entero `weight` para el peso del borde y una cadena de texto `id` como identificador (puede ser nulo).
   - **Salida:** Un nuevo objeto `Edge<K>` con las propiedades especificadas.

2. `getId()`: Devuelve el identificador del borde.
   - **Salida:** Una cadena de texto que representa el identificador del borde.

3. `setId(id: String)`: Establece el identificador del borde.
   - **Entrada:** Una cadena de texto como nuevo identificador del borde.
   - **Salida:** Ninguna.

4. `getVertex()`: Devuelve el vértice destino del borde.
   - **Salida:** Un objeto `Vertex<K>` que es el vértice destino del borde.

5. `setVertex(vertex: Vertex<K>)`: Establece el vértice destino del borde.
   - **Entrada:** Un objeto `Vertex<K>` que se convertirá en el vértice destino del borde.
   - **Salida:** Ninguna.

6. `getWeight()`: Devuelve el peso del borde.
   - **Salida:** Un entero que representa el peso del borde.

7. `setWeight(weight: int)`: Establece el peso del borde.
   - **Entrada:** Un entero como nuevo peso del borde.
   - **Salida:** Ninguna.

**Nota:** Esta estructura de datos `Edge<K>` se utiliza para representar un borde entre vértices en un grafo. El identificador (`id`) es opcional y el peso (`weight`) representa la ponderación del borde en un grafo ponderado.
