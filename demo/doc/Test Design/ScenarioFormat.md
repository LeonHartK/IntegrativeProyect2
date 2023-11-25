## Scenario configuration

| Name                | Class          | Stage                                       |
|-----------------------------------|----------------|-----------------------------------------------------|
| setUp                     | GraphTest      | Initialize a Graph object |
| setUp2                     | GraphTest      | Initialize a GraphMatrix Object |
| addVertexTest                     | GraphTest      | Test the successful addition of three vertices to the graph. |
| addVertexDuplicateTest            | GraphTest      | Tests detection of attempted addition of a duplicate vertex and successful addition of two other vertices. |
| addMultipleVerticesTest           | GraphTest      | Tests the successful addition of four vertices and verifies that the number of vertices in the list is as expected.|
| addVertexTestMatrix               | GraphTest      | Tests the successful addition of three vertices to a graph represented by a matrix. |
| addVertexDuplicateTestMatrix      | GraphTest      | Tests detection of attempts to add a duplicate vertex in a graph represented by a matrix. |
| addMultipleVerticesTestMatrix     | GraphTest      | Tests the successful addition of four vertices to a graph represented by a matrix and checks the expected number of vertices in the list. |
| addVertexDirectTest               | GraphTest      | Try the direct addition of three vertices and verify that the values ​​are as expected. |
| addVertexDirectDuplicateTest      | GraphTest      | Test the detection of attempting to add a duplicate vertex directly and verify that the values ​​are as expected. |
| addVertexDirectOrderTest           | GraphTest      | Try direct addition of three vertices and verify that the values ​​are in the correct order. |
| addVertexDirectMatrixTest          | GraphTest      | Test the direct addition of three vertices to a graph represented by a matrix and verify that the values ​​in the matrix are as expected. |
| addVertexDirectMatrixDuplicateTest         | GraphTest      | Tests detection of attempts to add a duplicate vertex directly into a graph represented by a matrix. |
| addVertexDirectMatrixOrderTest             | GraphTest      | Test the direct addition of three vertices in a graph represented by a matrix and verify that the values ​​in the matrix are in the correct order. |
| addAlreadyExistingVertex                   | GraphTest      | Test the detection of an attempt to add an existing vertex and verify that the addition of the first vertex is successful and the second is not. |
| addAlreadyExistingVertexMatrix             | GraphTest      | Tests detection of attempts to add an existing vertex to a graph represented by a matrix. |
| addEdgeTest                                | GraphTest      | Tests the successful addition of an edge between two vertices and verifies its existence. |
| addEdgeTestMatrix                          | GraphTest      | Tests the successful addition of an edge between two vertices in a graph represented by a matrix and verifies its existence. |
| addEdgeTestNoExistingVertex                | GraphTest      | Tests detection of an attempt to add an edge where at least one of the vertices does not exist and verifies that the addition is unsuccessful. |
| addEdgeTestNoExistingVertexMatrix          | GraphTest      | Tests the detection of an attempt to add an edge in a graph represented by a matrix, where at least one of the vertices does not exist. |
| addAlreadyExistingEdgeTest                 | GraphTest      | Tests the successful addition of an edge between two vertices, followed by an attempt to add the same edge again. |
| addAlreadyExistingEdgeMatrixTest           | GraphTest      | Tests the successful addition of an edge between two vertices in a graph represented by a matrix, followed by an attempt to add the same edge again. |
| deleteEdgeTest                             | GraphTest      | Tests the successful removal of an edge between two vertices and verifies that the edge no longer exists. |
| deleteEdgeMatrixTest                       | GraphTest      | Tests the successful removal of an edge between two vertices in a graph represented by a matrix and verifies that the edge no longer exists. |
| deleteNotExistingEdgeTest                   | GraphTest      | Test removing a non-existent edge and verify that the operation is unsuccessful. |
| deleteNotExistingEdgeMatrixTest             | GraphTest      | Tests the elimination of an edge in a graph represented by a matrix, which does not exist, and verifies that the operation is unsuccessful. |
| deleteEdgeNotExistingVertexTest             | GraphTest      | Tests removing an edge where at least one of the vertices does not exist and verifies that the operation is unsuccessful. |
| deleteEdgeNotExistingVertexMatrixTest       | GraphTest      | Tests the elimination of an edge in a graph represented by a matrix, where at least one of the vertices does not exist, and verifies that the operation is unsuccessful. |
| deleteVertexTest                            | GraphTest      | Tests the successful deletion of a vertex and verifies that the associated edges are also deleted. |
| deleteVertexMatrixTest                      | GraphTest      | Tests the successful deletion of a vertex in a graph represented by a matrix and verifies that the associated edges are also deleted. |
| deleteVertexDirectTest                      | GraphTest      | Tests the successful deletion of a vertex and verifies that the associated edges are also deleted. |
| deleteVertexDirectMatrixTest                | GraphTest      | Tests the successful deletion of a vertex in a graph represented by a matrix and verifies that the associated edges are also deleted. |
| deleteNotExistingVertexTest                 | GraphTest      | Test deleting a non-existent vertex and verify that the operation is unsuccessful. |
| deleteNotExistingVertexMatrixTest           | GraphTest      | Tests the elimination of a vertex in a graph represented by a matrix, which does not exist, and verifies that the operation is unsuccessful. |
| BFSTestParents                             | GraphTest      | Test the BFS traversal on a graph and verify that the parents of the vertices are as expected. |
| BFSTestParentsMatrix                       | GraphTest      | Tests BFS traversal on a graph represented by a matrix and verifies that the parents of the vertices are as expected. |
| BFSTestDistance                            | GraphTest      | Test the BFS traversal on a graph and verify that the distances from the root to the other vertices are as expected. |
| BFSTestDistanceMatrix                      | GraphTest      | Tests the BFS traversal on a graph represented by a matrix and verifies that the distances from the root to the other vertices are as expected. |
| BFSTestColor                               | GraphTest      | Try the BFS traversal on a graph and verify that all vertices are colored black at the end of the traversal.|
| BFSTestColorMatrix                         | GraphTest      | Test the BFS traversal on a graph represented by a matrix and verify that all vertices are colored black at the end of the traversal. |
| DFSTestParents                             | GraphTest      | Test the DFS traversal on a graph and verify that the parents of the vertices are as expected. |
| DFSTestParentsMatrix                       | GraphTest      | Tests DFS traversal on a graph represented by a matrix and verifies that the parents of the vertices are as expected. |
| DFSTestColor                               | GraphTest      | Test the DFS traversal on a graph and verify that all vertices are colored black at the end of the traversal. |
| DFSTestColorMatrix                         | GraphTest      | Test DFS traversal on a graph represented by a matrix and verify that all vertices are colored black at the end of the traversal. |
| DijsktraTest                               | GraphTest      | Test Dijkstra's algorithm on a graph and verify that the predecessors are as expected. |
| DijsktraTestMatrix                         | GraphTest      | Test Dijkstra's algorithm on a graph represented by a matrix and verify that the predecessors are as expected. |
| floydWarshallTest                          | GraphTest      | Test the Floyd-Warshall algorithm on a graph and verify that the minimum distances are as expected. |
| floydWarshallMatrixTest                     | GraphTest      | Test the Floyd-Warshall algorithm on a graph represented by a matrix and verify that the minimum distances are as expected. |
| floydWarshallTest2                          | GraphTest      | Test the Floyd-Warshall algorithm on a graph with vertices of type `String` and verify that the minimum distances are as expected. |
| floydWarshallNegativeCycleTest              | GraphTest      | Try the Floyd-Warshall algorithm on a graph with a negative cycle and verify that the minimum distances reflect this. |
| floydWarshallMatrixTest2                    | GraphMatrixTest| Test the Floyd-Warshall algorithm on a graph represented by a matrix and verify that the minimum distances are as expected. |
| primTest                                    | GraphTest      | Test Prim's algorithm on a graph and verify that the selected vertices are as expected. |
| primTest2                        | GraphTest      | Test Prim's algorithm on a graph and verify that the selected vertices are as expected, and that the total weight is as expected. |
| primMatrixTest                   | GraphMatrixTest| Test Prim's algorithm on a graph represented by a matrix and verify that the selected vertices are as expected. Print the results. |
| KruskalTest                      | GraphTest      | Test Kruskal's algorithm on a graph and verify that the selected vertices are as expected. Print the results.|
| kruskalEmptyGraphTest            | GraphTest      | Try Kruskal's algorithm on an empty graph and verify that the result is an empty list. |
| kruskalSingleVertexTest          | GraphTest      | Try Kruskal's algorithm on a graph with a single vertex and verify that the result is an empty list. |

## Test case design

### Test objective

Assert that vertex are added correctly

| Class                | Method          | Stage                                       | Input values | Expected result |
|-----------------------------------|----------------|-----------------------------------------------------|----------------------- | ----------------- |
| Graph | addVertex | setUp | Vertex = 1, 2, 3 | The three vertices are added to the graph |
| GraphMatrix | addVertex | setUp2 | Vertex = 1, 2, 3 | The three vertices are added to the matrix graph |
