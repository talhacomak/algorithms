package soft.visiontech

import org.junit.Test

class RotateMatrix {

    @Test
    fun test() {
        val matrix = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        )
        rotateMatrix(matrix)
        printMatrix(matrix)
    }

    fun printMatrix(matrix: Array<IntArray>) {
        for (row in matrix) {
            println(row.joinToString(" "))
        }
    }

    fun rotateMatrix(matrix: Array<IntArray>) {
        val n = matrix.size

        // 1) Transpose the matrix (swap rows <-> columns)
        for (i in 0 until n) {
            for (j in i until n) {
                val temp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = temp
            }
        }

        // 2) Reverse each row (makes it 90° clockwise)
        for (i in 0 until n) {
            matrix[i].reverse()
        }
    }
}