package soft.visiontech

import org.junit.Test

class LargestSquareProblem {

    @Test
    fun largest_square_problem() {
        val matrix = arrayOf(
            intArrayOf(1, 1, 1, 1, 1),
            intArrayOf(1, 1, 1, 0, 0),
            intArrayOf(1, 1, 1, 1, 0),
            intArrayOf(1, 1, 0, 1, 1),
            intArrayOf(0, 1, 1, 1, 1)
        )

        println(largestSquareOfOnes(matrix))
    }

    fun largestSquareBruteForce(matrix: Array<IntArray>): Int {
        val rows = matrix.size
        val cols = matrix[0].size
        var maxSide = 0

        for (i in 0 until rows) {
            for (j in 0 until cols) {

                if (matrix[i][j] == 0) continue

                var currentSize = 1
                while (i + currentSize <= rows && j + currentSize <= cols) {
                    var allOnes = true
                    for (r in i until i + currentSize) {
                        for (c in j until j + currentSize) {
                            println("Checking square: start=($i,$j), size=$currentSize, cell=($r,$c) value=${matrix[r][c]}")
                            if (matrix[r][c] == 0) {
                                allOnes = false
                                break
                            }
                        }
                        if (!allOnes) break
                    }
                    if (!allOnes) break
                    maxSide = maxOf(maxSide, currentSize)
                    currentSize++
                }
            }
        }

        return maxSide
    }

    fun largestSquareBruteForce2(matrix: Array<IntArray>): Int {
        val rows = matrix.size
        val cols = matrix[0].size
        var maxSide = 0

        for (i in 0 until rows) {
            for (j in 0 until cols) {

                if (matrix[i][j] == 0) continue
                var currentSize = 1

                while (i + currentSize <= rows && j + currentSize <= cols) {

                    var allOnes = true

                    if (currentSize != 1) {
                        val lastSize = currentSize - 1
                        val bottomRow = i + lastSize
                        val rightCol = j + lastSize

                        for (r in i until i + lastSize) {
                            // println("Checking right edge: start=($i,$j), size=$currentSize, cell=($r,$rightCol)")
                            if (matrix[r][rightCol] == 0) {
                                allOnes = false
                                break
                            }
                        }

                        if (allOnes) {
                            for (c in j until j + currentSize) {
                                // println("Checking bottom edge: start=($i,$j), size=$currentSize, cell=($bottomRow,$c)")
                                if (matrix[bottomRow][c] == 0) {
                                    allOnes = false
                                    break
                                }
                            }
                        }
                    }

                    if (!allOnes) break

                    maxSide = maxOf(maxSide, currentSize)
                    currentSize++
                }
            }
        }

        return maxSide
    }

    fun largestSquareOfOnes(matrix: Array<IntArray>): Int {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return 0

        val rows = matrix.size
        val cols = matrix[0].size
        var maxSide = 0

        val dp = Array(rows) { IntArray(cols) }

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (matrix[i][j] == 1) {

                    dp[i][j] = if (i == 0 || j == 0) {
                        1
                    } else {
                        minOf(
                            dp[i - 1][j],     // up
                            dp[i][j - 1],     // left
                            dp[i - 1][j - 1]  // up-left
                        ) + 1
                    }

                    if (dp[i][j] > maxSide)
                        maxSide = dp[i][j]
                }
            }
        }

        return maxSide
    }
}