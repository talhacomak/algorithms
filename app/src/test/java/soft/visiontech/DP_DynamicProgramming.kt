package soft.visiontech

import org.junit.Test

class DP_DynamicProgramming {

    /*
    For mxn grid, find all possible ways from top-left to bottom-right
    1 1 1
    1 2 3
    1 3 6
     */

    @Test
    fun test() {
        val grid = arrayOf(arrayOf(0,1,0,0))
        println(uniquePathsWithObstacles(grid))
    }

    fun uniquePathsWithObstacles(obstacleGrid: Array<Array<Int>>): Int {
        if (obstacleGrid[obstacleGrid.size -1][obstacleGrid[0].size-1] == 1
            || obstacleGrid[0][0] == 1)
            return 0

        if (obstacleGrid.size == 1) {
            for (j in obstacleGrid[0].indices) {
                if (obstacleGrid[0][j] == 1) {
                    return 0
                }
            }
            return 1
        }
        else {
            var hasObs = false
            for (i in obstacleGrid.indices) {
                if (hasObs)
                    obstacleGrid[i][0] = 1
                else {
                    hasObs = obstacleGrid[i][0] == 1
                    if (!hasObs)
                        obstacleGrid[i][0] = -1
                }
            }
        }

        if (obstacleGrid[0].size == 1) {
            for (i in obstacleGrid.indices) {
                if (obstacleGrid[i][0] == 1) {
                    return 0
                }
            }
            return 1
        }
        else {
            var hasObs = false
            for (j in obstacleGrid[0].indices) {
                if (hasObs)
                    obstacleGrid[0][j] = 1
                else {
                    hasObs = obstacleGrid[0][j] == 1
                    if (!hasObs)
                        obstacleGrid[0][j] = -1
                }
            }
        }

        for (i in 1 until obstacleGrid.size) {
            for (j in 1 until obstacleGrid[i].size) {
                if (obstacleGrid[i][j] != 1) {
                    val left = if (obstacleGrid[i - 1][j] != 1) obstacleGrid[i - 1][j] else 0
                    val top = if (obstacleGrid[i][j - 1] != 1) obstacleGrid[i][j - 1] else 0
                    obstacleGrid[i][j] = left + top
                }
            }
        }
        return -1 * obstacleGrid[obstacleGrid.size -1][obstacleGrid[0].size -1]
    }

    fun uniquePaths(m: Int, n: Int): Int {
        // dp[row][col] = number of paths to reach (row, col)
        val dp = Array(m) { IntArray(n) }

        // First row = 1
        for (col in 0 until n) {
            dp[0][col] = 1
        }

        // First column = 1
        for (row in 0 until m) {
            dp[row][0] = 1
        }

        // Fill the rest
        for (row in 1 until m) {
            for (col in 1 until n) {
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1]
            }
        }

        return dp[m - 1][n - 1]
    }

}