package soft.visiontech

import org.junit.Test
import kotlin.math.pow

class UniqueBSTrees {

    @Test
    fun test() {
        println(numTrees(1))
        println(numTrees(2))
        println(numTrees(3))
        println(numTrees(4))
        println(numTrees(5))
        println(numTrees(6))
    }

    data class TreeNode(
        val value: Int,
        var left: TreeNode? = null,
        var right: TreeNode? = null
    )

    // 1 SRRR
    // 2 SRRL
    // 3 SRLR
    // 4 SRLL

    fun numTrees(n: Int): Int {
        return 2.0.pow((n - 1).toDouble()).toInt()*(n - 2) + 2
    }
}