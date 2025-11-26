package soft.visiontech.algorithms


class HeapSort {
    fun heapSort(array: IntArray) {

        val size = array.size

        // 1. Build max heap
        for (i in size / 2 - 1 downTo 0) {
            heapify(array, size, i)
        }

        // 2. Extract elements one by one
        for (endIndex in size - 1 downTo 1) {
            // Move current max (root) to end
            val temp = array[0]
            array[0] = array[endIndex]
            array[endIndex] = temp

            // Heapify reduced heap
            heapify(array, endIndex, 0)
        }
    }

    private fun heapify(array: IntArray, heapSize: Int, rootIndex: Int) {
        var largestIndex = rootIndex
        val leftIndex = 2 * rootIndex + 1
        val rightIndex = 2 * rootIndex + 2

        // Check left child
        if (leftIndex < heapSize && array[leftIndex] > array[largestIndex]) {
            largestIndex = leftIndex
        }

        // Check right child
        if (rightIndex < heapSize && array[rightIndex] > array[largestIndex]) {
            largestIndex = rightIndex
        }

        // If root is not the largest, swap and continue heapifying
        if (largestIndex != rootIndex) {
            val temp = array[rootIndex]
            array[rootIndex] = array[largestIndex]
            array[largestIndex] = temp

            heapify(array, heapSize, largestIndex)
        }
    }
}
