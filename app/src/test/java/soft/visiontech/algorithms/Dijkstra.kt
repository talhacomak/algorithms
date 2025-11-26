package soft.visiontech.algorithms

import org.junit.Test
import java.util.PriorityQueue

class Dijkstra {

    data class Edge(
        val targetNode: Int,
        val weight: Int
    )

    /**
     * Dijkstra's algorithm.
     *
     * @param nodeCount   Total number of nodes in the graph. Nodes are assumed to be 0..nodeCount-1
     * @param adjacencyList  Graph represented as adjacency list:
     *                       adjacencyList[u] = list of outgoing edges from node u
     * @param startNode   Source node to calculate shortest paths from
     *
     * @return IntArray of shortest distances from startNode to every node.
     *         If a node is unreachable, its distance will be Int.MAX_VALUE.
     */
    fun dijkstra(
        nodeCount: Int,
        adjacencyList: List<List<Edge>>,
        startNode: Int
    ): IntArray {

        val distanceFromStart = IntArray(nodeCount) { Int.MAX_VALUE }
        distanceFromStart[startNode] = 0

        // (distance, node)
        val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        priorityQueue.add(0 to startNode)

        while (priorityQueue.isNotEmpty()) {
            val (currentDistance, currentNode) = priorityQueue.poll()!!
            println("curDist: $currentDistance, curNode: $currentNode, distanceFromStart: ${distanceFromStart[currentNode]}")
            // If this entry is outdated, skip it
            if (currentDistance > distanceFromStart[currentNode]) continue

            // Explore all neighbors
            for (edge in adjacencyList[currentNode]) {
                println("edge start: $currentNode, target: ${edge.targetNode}")
                val nextNode = edge.targetNode
                val newDistance = currentDistance + edge.weight
                println("edge.weight: ${edge.weight}, newDistance: ${newDistance}")

                if (newDistance < distanceFromStart[nextNode]) {
                    distanceFromStart[nextNode] = newDistance
                    priorityQueue.add(newDistance to nextNode)
                }
            }
        }

        return distanceFromStart
    }

    fun dijkstraTarget(
        nodeCount: Int,
        adjacencyList: List<List<Edge>>,
        startNode: Int,
        targetNode: Int
    ): Int {

        val distanceFromStart = IntArray(nodeCount) { Int.MAX_VALUE }
        distanceFromStart[startNode] = 0

        // (distance, node)
        val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        priorityQueue.add(0 to startNode)

        while (priorityQueue.isNotEmpty()) {
            val (currentDistance, currentNode) = priorityQueue.poll()!!
            println("curDist: $currentDistance, curNode: $currentNode")
            // If this entry is outdated, skip it
            if (currentDistance > distanceFromStart[currentNode]) continue

            // Explore all neighbors
            for (edge in adjacencyList[currentNode]) {
                val nextNode = edge.targetNode
                val newDistance = currentDistance + edge.weight

                if (newDistance < distanceFromStart[nextNode]) {
                    distanceFromStart[nextNode] = newDistance
                    priorityQueue.add(newDistance to nextNode)
                }
            }
        }

        return if (distanceFromStart[targetNode] != Int.MAX_VALUE)
            distanceFromStart[targetNode]
        else -1
    }

    @Test
    fun test() {
        val nodeCount = 5

        // Directed weighted graph
        val adjacencyList = List(nodeCount) { mutableListOf<Edge>() }

        fun addEdge(from: Int, to: Int, weight: Int) {
            adjacencyList[from].add(Edge(to, weight))
            // Eğer graph undirected ise şunu da ekle:
            // adjacencyList[to].add(Edge(from, weight))
        }

        addEdge(0, 1, 2)
        addEdge(0, 2, 5)
        addEdge(1, 2, 1)
        addEdge(1, 3, 2)
        addEdge(2, 3, 3)
        addEdge(3, 4, 1)

        val startNode = 0
        val distances = dijkstra(nodeCount, adjacencyList, startNode)

        println(distances.joinToString()) // örn: 0, 2, 3, 4, 5
    }

    data class TestCase(
        val nodeCount: Int,
        val edges: List<Triple<Int, Int, Int>>,
        val startNode: Int,
        val targetNode: Int,
        val expected: Int
    )

    @Test
    fun test2() {
        val testCases = listOf(
            // Test 1
            TestCase(
                nodeCount = 5,
                edges = listOf(
                    Triple(0, 1, 2),
                    Triple(0, 2, 5),
                    Triple(1, 2, 1),
                    Triple(1, 3, 2),
                    Triple(2, 3, 3),
                    Triple(3, 4, 1)
                ),
                startNode = 0,
                targetNode = 4,
                expected = 5
            ),
            // Test 2: unreachable node
            TestCase(
                nodeCount = 4,
                edges = listOf(
                    Triple(0, 1, 3),
                    Triple(1, 2, 4)
                ),
                startNode = 0,
                targetNode = 3,
                expected = -1
            ),
            // Test 3: multiple paths, must choose best
            TestCase(
                nodeCount = 4,
                edges = listOf(
                    Triple(0, 1, 10),
                    Triple(0, 2, 1),
                    Triple(2, 1, 1),
                    Triple(1, 3, 2),
                    Triple(2, 3, 100)
                ),
                startNode = 0,
                targetNode = 3,
                expected = 4
            ),
            // Test 4: start == target
            TestCase(
                nodeCount = 3,
                edges = listOf(
                    Triple(0, 1, 5),
                    Triple(1, 2, 7)
                ),
                startNode = 1,
                targetNode = 1,
                expected = 0
            ),
            // Test 5: bigger graph
            TestCase(
                nodeCount = 6,
                edges = listOf(
                    Triple(0, 1, 4),
                    Triple(0, 2, 2),
                    Triple(1, 2, 1),
                    Triple(1, 3, 5),
                    Triple(2, 3, 8),
                    Triple(2, 4, 10),
                    Triple(3, 4, 2),
                    Triple(3, 5, 6),
                    Triple(4, 5, 3)
                ),
                startNode = 0,
                targetNode = 5,
                expected = 13
            )
        )

        fun buildAdjacencyList(nodeCount: Int, edges: List<Triple<Int, Int, Int>>): List<MutableList<Edge>> {
            val adjacencyList = List(nodeCount) { mutableListOf<Edge>() }
            for ((from, to, weight) in edges) {
                // Undirected graph: add both directions
                adjacencyList[from].add(Edge(to, weight))
                adjacencyList[to].add(Edge(from, weight))
            }
            return adjacencyList
        }

        for ((index, testCase) in testCases.withIndex()) {
            val adjacencyList = buildAdjacencyList(testCase.nodeCount, testCase.edges)
            val result = dijkstraTarget(
                nodeCount = testCase.nodeCount,
                adjacencyList = adjacencyList,
                startNode = testCase.startNode,
                targetNode = testCase.targetNode
            )

            val status = if (result == testCase.expected) "OK" else "FAIL"
            println(
                "Test ${index + 1}: $status " +
                        "(result = $result, expected = ${testCase.expected})"
            )
        }
    }
}
