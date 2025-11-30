package soft.visiontech

import soft.visiontech.algorithms.Dijkstra.Edge
import java.util.PriorityQueue

class MinCost {

    data class TestCase(
        val name: String,
        val nodeCount: Int,
        val couponCount: Int,
        val edges: List<Triple<Int, Int, Int>>,
        val expected: Long
    )

    fun minCostWithCoupons(
        nodeCount: Int,
        couponCount: Int,
        edges: List<Triple<Int, Int, Int>>
    ): Long {
        val adjacencyList = List(nodeCount) { mutableListOf<Edge>() }
        for ((from, to, weight) in edges) {
            adjacencyList[from].add(Edge(to, weight))
            adjacencyList[to].add(Edge(from, weight))
        }
        val result = dijkstraTarget(
            nodeCount = nodeCount,
            adjacencyList = adjacencyList,
            startNode = 1,
            targetNode = nodeCount
        )
        return result.size.toLong()
    }

    fun dijkstraTarget(
        nodeCount: Int,
        adjacencyList: List<List<Edge>>,
        startNode: Int,
        targetNode: Int
    ): List<Edge> {
        val mutableList = mutableListOf<Edge>()

        val distanceFromStart = IntArray(nodeCount) { Int.MAX_VALUE }
        distanceFromStart[startNode] = 0

        // (distance, node)
        val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        priorityQueue.add(0 to startNode)

        while (priorityQueue.isNotEmpty()) {
            val (currentDistance, currentNode) = priorityQueue.poll()!!
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

        return listOf()
    }

    fun main() {
        val testCases = listOf(
            // Test 1 – basic example, 1 coupon (should switch 10 -> 5)
            TestCase(
                name = "Basic_1_coupon",
                nodeCount = 4,
                couponCount = 1,
                edges = listOf(
                    Triple(1, 2, 10),
                    Triple(2, 4, 10),
                    Triple(1, 3, 100),
                    Triple(3, 4, 1)
                ),
                expected = 15L
            ),
            // Test 2 – K = 0 (no coupons)
            TestCase(
                name = "No_coupons",
                nodeCount = 5,
                couponCount = 0,
                edges = listOf(
                    Triple(1, 2, 1),
                    Triple(2, 3, 1),
                    Triple(3, 4, 1),
                    Triple(4, 5, 1),
                    Triple(2, 5, 10)
                ),
                expected = 4L // 1-2-3-4-5
            ),
            // Test 3 – unreachable target
            TestCase(
                name = "Unreachable",
                nodeCount = 3,
                couponCount = 2,
                edges = listOf(
                    Triple<Int, Int, Int>(1, 2, 5)
                    // Node 3 is isolated
                ),
                expected = -1L
            ),
            // Test 4 – multiple coupons, best path uses different edges
            TestCase(
                name = "Multiple_coupons_best_on_8s",
                nodeCount = 5,
                couponCount = 2,
                edges = listOf(
                    Triple<Int, Int, Int>(1, 2, 8),
                    Triple<Int, Int, Int>(2, 5, 8),
                    Triple<Int, Int, Int>(1, 3, 5),
                    Triple<Int, Int, Int>(3, 4, 5),
                    Triple<Int, Int, Int>(4, 5, 5)
                ),
                // Best path: 1-2-5, both edges halved: 8/2 + 8/2 = 4 + 4 = 8
                expected = 8L
            ),
            // Test 5 – K larger than needed, extra coupons do nothing
            TestCase(
                name = "Too_many_coupons",
                nodeCount = 4,
                couponCount = 10,
                edges = listOf(
                    Triple<Int, Int, Int>(1, 2, 9),
                    Triple<Int, Int, Int>(2, 3, 9),
                    Triple<Int, Int, Int>(3, 4, 9)
                ),
                // 3 edges, each 9 -> 4 when halved. 4 + 4 + 4 = 12
                expected = 12L
            ),
            // Test 6 – best path changes when coupons are used
            TestCase(
                name = "Path_switch_with_coupons",
                nodeCount = 5,
                couponCount = 2,
                edges = listOf(
                    Triple<Int, Int, Int>(1, 2, 100),
                    Triple<Int, Int, Int>(2, 5, 100),
                    Triple<Int, Int, Int>(1, 3, 60),
                    Triple<Int, Int, Int>(3, 4, 60),
                    Triple<Int, Int, Int>(4, 5, 60)
                ),
                // Without coupons: best is 1-3-4-5 = 180
                // With 2 coupons:
                //   On path 1-2-5: 100/2 + 100/2 = 50 + 50 = 100  (better)
                expected = 100L
            ),
            // Test 7 – odd weights, floor division matters
            TestCase(
                name = "Odd_weights_floor_division",
                nodeCount = 3,
                couponCount = 1,
                edges = listOf(
                    Triple<Int, Int, Int>(1, 2, 1),
                    Triple<Int, Int, Int>(2, 3, 3)
                ),
                // Path: 1-2-3
                // Coupon on 1-2: 1/2 + 3 = 0 + 3 = 3
                // Coupon on 2-3: 1 + 3/2 = 1 + 1 = 2 (best)
                expected = 2L
            )
        )

        for (test in testCases) {
            val result = try {
                minCostWithCoupons(
                    nodeCount = test.nodeCount,
                    couponCount = test.couponCount,
                    edges = test.edges
                )
            } catch (e: Throwable) {
                println("Test ${test.name}: EXCEPTION -> ${e.message}")
                continue
            }

            val status = if (result == test.expected) "OK" else "FAIL"
            println(
                "Test ${test.name}: $status  (result = $result, expected = ${test.expected})"
            )
        }
    }

}