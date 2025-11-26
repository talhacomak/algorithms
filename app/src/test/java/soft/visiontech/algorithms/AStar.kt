package soft.visiontech.algorithms

import java.util.PriorityQueue

class AStar {

    data class AStarEdge(
        val targetNode: Int,
        val cost: Int
    )

    /**
     * A* shortest path algorithm on a weighted graph with a heuristic.
     *
     * @param nodeCount      Number of nodes in the graph (nodes are 0..nodeCount-1)
     * @param adjacencyList  adjacencyList[u] = list of outgoing edges from node u
     * @param startNode      Start node
     * @param goalNode       Goal node
     * @param heuristic      h(node): estimated distance from node to goal.
     *                       Must be optimistic (never overestimates) for optimality.
     *
     * @return List of node indices representing the path from startNode to goalNode.
     *         Empty list if no path exists.
     */
    fun aStar(
        nodeCount: Int,
        adjacencyList: List<List<AStarEdge>>,
        startNode: Int,
        goalNode: Int,
        heuristic: IntArray
    ): List<Int> {

        // gScore: best known distance from start to each node
        val gScore = IntArray(nodeCount) { Int.MAX_VALUE }
        gScore[startNode] = 0

        // For path reconstruction
        val cameFrom = IntArray(nodeCount) { -1 }

        // Min-heap ordered by fScore = gScore + heuristic
        data class State(val node: Int, val fScore: Int)

        val openSet = PriorityQueue<State>(compareBy { it.fScore })
        openSet.add(State(startNode, heuristic[startNode]))

        val isClosed = BooleanArray(nodeCount)

        while (openSet.isNotEmpty()) {
            val current = openSet.poll()
            val currentNode = current.node

            // If we already processed a better version of this node, skip
            if (isClosed[currentNode]) continue

            // Goal reached: reconstruct and return path
            if (currentNode == goalNode) {
                return reconstructPath(cameFrom, goalNode)
            }

            isClosed[currentNode] = true

            for (edge in adjacencyList[currentNode]) {
                val neighbor = edge.targetNode
                if (isClosed[neighbor]) continue

                val tentativeGScore = gScore[currentNode] + edge.cost
                if (tentativeGScore < gScore[neighbor]) {
                    gScore[neighbor] = tentativeGScore
                    cameFrom[neighbor] = currentNode

                    val newFScore = tentativeGScore + heuristic[neighbor]
                    openSet.add(State(neighbor, newFScore))
                }
            }
        }

        // No path found
        return emptyList()
    }

    private fun reconstructPath(
        cameFrom: IntArray,
        goalNode: Int
    ): List<Int> {
        if (cameFrom[goalNode] == -1) return emptyList()

        val path = mutableListOf<Int>()
        var current = goalNode
        while (current != -1) {
            path.add(current)
            current = cameFrom[current]
        }
        path.reverse()
        return path
    }

}