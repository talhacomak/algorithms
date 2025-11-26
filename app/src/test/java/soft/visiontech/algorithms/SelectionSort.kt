package soft.visiontech.algorithms

class SelectionSort {
    // Min Swap
    fun selectionSort(arr: IntArray): IntArray {
        for (i in arr.indices) {
            var minIndex = i

            for (j in i + 1 until arr.size) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j
                }
            }

            if (minIndex != i) {
                val temp = arr[i]
                arr[i] = arr[minIndex]
                arr[minIndex] = temp
            }
        }
        arr.sort()
        return arr
    }
}