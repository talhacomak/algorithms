package soft.visiontech.algorithms

class QuickSort {

    fun quickSort(array: IntArray, startIndex: Int = 0, endIndex: Int = array.size - 1) {
        if (startIndex >= endIndex) return

        val pivotIndex = partition(array, startIndex, endIndex)

        quickSort(array, startIndex, pivotIndex)
        quickSort(array, pivotIndex + 1, endIndex)
    }

    private fun partition(array: IntArray, startIndex: Int, endIndex: Int): Int {
        val pivotValue = array[(startIndex + endIndex) / 2]  // median-of-three yok, basic version
        var leftPointer = startIndex - 1
        var rightPointer = endIndex + 1

        while (true) {
            do { leftPointer++ } while (array[leftPointer] < pivotValue)
            do { rightPointer-- } while (array[rightPointer] > pivotValue)

            if (leftPointer >= rightPointer) return rightPointer

            val temp = array[leftPointer]
            array[leftPointer] = array[rightPointer]
            array[rightPointer] = temp
        }
    }

}