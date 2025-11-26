package soft.visiontech.algorithms


class BinarySearchTree {

    class TreeNode(
        var value: Int,
        var left: TreeNode? = null,
        var right: TreeNode? = null
    )

    var root: TreeNode? = null
        private set

    fun insert(value: Int) {
        root = insertRec(root, value)
    }

    private fun insertRec(node: TreeNode?, value: Int): TreeNode {
        if (node == null) return TreeNode(value)

        when {
            value < node.value -> node.left = insertRec(node.left, value)
            value > node.value -> node.right = insertRec(node.right, value)
            else -> {
                // duplicate. ignore
            }
        }
        return node
    }

    fun contains(value: Int): Boolean {
        return containsRec(root, value)
    }

    private fun containsRec(node: TreeNode?, value: Int): Boolean {
        node ?: return false
        return when {
            value == node.value -> true
            value < node.value -> containsRec(node.left, value)
            else -> containsRec(node.right, value)
        }
    }

    fun inorder(): List<Int> {
        val result = mutableListOf<Int>()
        inorderRec(root, result)
        return result
    }

    private fun inorderRec(node: TreeNode?, result: MutableList<Int>) {
        if (node == null) return
        inorderRec(node.left, result)
        result.add(node.value)
        inorderRec(node.right, result)
    }

    fun preorder(): List<Int> {
        val result = mutableListOf<Int>()
        preorderRec(root, result)
        return result
    }

    private fun preorderRec(node: TreeNode?, result: MutableList<Int>) {
        if (node == null) return
        result.add(node.value)
        preorderRec(node.left, result)
        preorderRec(node.right, result)
    }

    fun postorder(): List<Int> {
        val result = mutableListOf<Int>()
        postorderRec(root, result)
        return result
    }

    private fun postorderRec(node: TreeNode?, result: MutableList<Int>) {
        if (node == null) return
        postorderRec(node.left, result)
        postorderRec(node.right, result)
        result.add(node.value)
    }

    fun findMin(): Int? {
        var current = root ?: return null
        while (current.left != null) {
            current = current.left!!
        }
        return current.value
    }

    fun findMax(): Int? {
        var current = root ?: return null
        while (current.right != null) {
            current = current.right!!
        }
        return current.value
    }

    fun delete(value: Int) {
        root = deleteRec(root, value)
    }

    private fun deleteRec(node: TreeNode?, value: Int): TreeNode? {
        node ?: return null

        when {
            value < node.value -> {
                node.left = deleteRec(node.left, value)
            }
            value > node.value -> {
                node.right = deleteRec(node.right, value)
            }
            else -> {
                // Case 1: No child
                if (node.left == null && node.right == null) {
                    return null
                }

                // Case 2: One child
                if (node.left == null) return node.right
                if (node.right == null) return node.left

                // Case 3: Two children
                val successorValue = findMin(node.right!!)
                node.value = successorValue
                node.right = deleteRec(node.right, successorValue)
            }
        }
        return node
    }

    private fun findMin(node: TreeNode): Int {
        var current = node
        while (current.left != null) {
            current = current.left!!
        }
        return current.value
    }
}