# Different Graph Algorithms

- **Disjoint Set Union (DSU):** Merge connected components and quickly count the number of disconnected components.

- **DFS (Depth-First Search):** Visit a vertex, then explore its unvisited neighbors as deeply as possible before backtracking.

- **BFS (Breadth-First Search):** Visit vertices level by level, exploring all immediate neighbors before moving to the next level.

- **Kruskal’s Algorithm:** Build a minimum spanning tree by repeatedly adding the lowest-weight edge that does not create a cycle.

- **Prim’s Algorithm:** Expand a minimum spanning tree by repeatedly selecting the lowest-weight edge connecting a visited vertex to an unvisited vertex.

## Shortest Paths in Weighted Graphs

- **Dijkstra’s Algorithm:** Repeatedly select the unvisited vertex with the smallest known distance from the source, then update the distances to its neighbors if shorter paths are found.

- **Bellman–Ford Algorithm:** Repeatedly examine all edges and update the shortest-distance estimates until no further improvements are possible or the maximum number of iterations is reached.

## Topological Sorting

- **Kahn’s Algorithm:** Start with tasks that have an indegree of zero. Process each task, decrease the indegrees of its neighbors, and add any neighbors whose indegrees become zero to the queue. If all vertices are processed, the graph has a valid topological ordering.