package soft.visiontech

import org.junit.Test

class KthSmallest {
    class TreeNode(
        val value: Int,
        var left: TreeNode? = null,
        var right: TreeNode? = null,
        var size: Int = 1
    )

    fun inorderTraversal(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        traverse(root, list)
        return list
    }

    fun traverse(root: TreeNode?, list: MutableList<Int>) {
        val traverse: TreeNode? = root
        if (traverse != null) {
            if (traverse.left != null)
                traverse(traverse.left, list)
            list.add(traverse.value)
            if (traverse.right != null)
                traverse(traverse.right, list)
        }
    }

    fun kthSmallest(root: TreeNode?, k: Int): Int {
        val targetSize = k
        var s = 0
        root?.let {
            var traverse: TreeNode? = it
            while (traverse != null) {
                val curOrder = traverse.size + s - (traverse.right?.size?:0)
                if (curOrder == targetSize)
                    return traverse.value
                else if (curOrder > targetSize && traverse.left != null)
                    traverse = traverse.left
                else {
                    traverse = traverse.right
                    s = curOrder
                }
            }

        }
        return -1
    }

    fun insert(root: TreeNode?, v: Int): TreeNode {
        if (root == null) return TreeNode(v)

        if (v < root.value)
            root.left = insert(root.left, v)
        else
            root.right = insert(root.right, v)

        root.size = 1 +
                (root.left?.size ?: 0) +
                (root.right?.size ?: 0)

        return root
    }


    @Test
    fun runKthSmallestTests() {
        fun testCase(values: List<Int>, k: Int, expected: Int) {
            var root: TreeNode? = null
            for (v in values) root = insert(root, v)

            val result = inorderTraversal(root)
            println(result)
        }

        println("=== Kth Smallest Test Cases ===")

        // 1) Single element
        testCase(listOf(5), 1, 5)

        // 2) Simple balanced BST
        testCase(listOf(2,1,3), 1, 1)
        testCase(listOf(2,1,3), 2, 2)
        testCase(listOf(2,1,3), 3, 3)

        // 3) Left-skewed tree
        testCase(listOf(5,4,3,2,1), 1, 1)
        testCase(listOf(5,4,3,2,1), 3, 3)
        testCase(listOf(5,4,3,2,1), 5, 5)

        // 4) Right-skewed tree
        testCase(listOf(1,2,3,4,5), 1, 1)
        testCase(listOf(1,2,3,4,5), 4, 4)
        testCase(listOf(1,2,3,4,5), 5, 5)

        // 5) Mixed unsorted input
        testCase(listOf(5,1,9,3,7,2,8), 1, 1)
        testCase(listOf(5,1,9,3,7,2,8), 3, 3)
        testCase(listOf(5,1,9,3,7,2,8), 5, 7)
        testCase(listOf(5,1,9,3,7,2,8), 7, 9)

        // 6) Duplicates (BST allows equal on right)
        testCase(listOf(5,3,7,3,4,5), 1, 3)
        testCase(listOf(5,3,7,3,4,5), 2, 3)
        testCase(listOf(5,3,7,3,4,5), 3, 4)
        testCase(listOf(5,3,7,3,4,5), 4, 5)
        testCase(listOf(5,3,7,3,4,5), 5, 5)
        testCase(listOf(5,3,7,3,4,5), 6, 7)

        println("=== End of Tests ===")
    }
}