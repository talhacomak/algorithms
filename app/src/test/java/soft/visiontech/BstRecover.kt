package soft.visiontech

import org.junit.Test

class BstRecover {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    var root: TreeNode? = null
        private set

    fun insert(value: Int) {
        root = insertRec(root, value)
    }

    private fun insertRec(node: TreeNode?, value: Int): TreeNode {
        if (node == null) return TreeNode(value)

        when {
            value < node.`val` -> node.left = insertRec(node.left, value)
            value > node.`val` -> node.right = insertRec(node.right, value)
            else -> {
                // duplicate. ignore
            }
        }
        return node
    }


    @Test
    fun test() {
        fun testCase(values: List<Int>) {
            for (v in values)
                insert(v)

            val temp = root!!.right!!.right!!.`val`
            root!!.right!!.right!!.`val` = root!!.left!!.`val`
            root!!.left!!.`val` = temp
            println("false1: $temp, false2: ${root!!.right!!.right!!.`val`}")
            val result = recoverTree(root)
            println(result)
        }
        testCase(listOf(5, 3, 1, 7, 4, 6, 9, 11, 13))
    }

    private var first: TreeNode? = null
    private var second: TreeNode? = null
    private var prev: TreeNode? = TreeNode(Int.MIN_VALUE)

    fun recoverTree(root: TreeNode?) {
        inorderCheck(root)

        val tmp = first!!.`val`
        first!!.`val` = second!!.`val`
        second!!.`val` = tmp
    }

    private fun inorderCheck(node: TreeNode?) {
        if (node == null) return
        inorderCheck(node.left)
        if (prev!!.`val` > node.`val`) {
            if (first == null) {
                first = prev
                println("first prev: " + prev?.`val`)
            }
            second = node
            println("second node: " + second?.`val`)
        }
        prev = node
        println("prev: " + prev?.`val`)

        inorderCheck(node.right)
    }

    fun isValidBST(node: TreeNode?): Boolean {
        if (node == null) return true
        if (!isValidBST(node.left)) return false
        if (prev!!.`val` >= node.`val`) return false
        prev = node
        return isValidBST(node.right)
    }
}
