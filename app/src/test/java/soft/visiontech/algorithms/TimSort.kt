package soft.visiontech.algorithms

class TimSort {
    object TimSort {

        private const val MIN_RUN = 32

        fun sort(array: IntArray) {
            val size = array.size
            if (size < 2) return

            val minRun = minRunLength(size)

            // 1) Split into small runs and sort each run via insertion sort
            var startIndex = 0
            while (startIndex < size) {
                val endIndex = minOf(startIndex + minRun - 1, size - 1)
                insertionSort(array, startIndex, endIndex)
                startIndex += minRun
            }

            // 2) Merge runs in a bottom-up manner, doubling run size each time
            var runSize = minRun
            while (runSize < size) {
                var leftStart = 0

                while (leftStart < size) {
                    val mid = minOf(leftStart + runSize - 1, size - 1)
                    val rightEnd = minOf(leftStart + 2 * runSize - 1, size - 1)

                    if (mid < rightEnd) {
                        merge(array, leftStart, mid, rightEnd)
                    }

                    leftStart += 2 * runSize
                }

                runSize *= 2
            }
        }

        /**
         * Calculate the minimum run length for TimSort style splitting.
         * This is the same idea as CPython and Java implementation.
         */
        private fun minRunLength(nValue: Int): Int {
            var n = nValue
            var remainderBit = 0

            while (n >= MIN_RUN) {
                // If any of the shifted out bits is 1, remember that
                remainderBit = remainderBit or (n and 1)
                n = n shr 1
            }
            return n + remainderBit
        }

        /**
         * Simple insertion sort on the range [leftIndex, rightIndex].
         */
        private fun insertionSort(array: IntArray, leftIndex: Int, rightIndex: Int) {
            for (i in (leftIndex + 1)..rightIndex) {
                val currentValue = array[i]
                var position = i - 1

                while (position >= leftIndex && array[position] > currentValue) {
                    array[position + 1] = array[position]
                    position--
                }
                array[position + 1] = currentValue
            }
        }

        /**
         * Merge two sorted subarrays:
         * left part is [leftIndex, middleIndex]
         * right part is [middleIndex + 1, rightIndex]
         */
        private fun merge(array: IntArray, leftIndex: Int, middleIndex: Int, rightIndex: Int) {
            val leftSize = middleIndex - leftIndex + 1
            val rightSize = rightIndex - middleIndex

            // Copy left and right parts into temporary arrays
            val leftArray = IntArray(leftSize)
            val rightArray = IntArray(rightSize)

            for (i in 0 until leftSize) {
                leftArray[i] = array[leftIndex + i]
            }
            for (j in 0 until rightSize) {
                rightArray[j] = array[middleIndex + 1 + j]
            }

            var leftPointer = 0
            var rightPointer = 0
            var mergedIndex = leftIndex

            // Merge back into original array
            while (leftPointer < leftSize && rightPointer < rightSize) {
                if (leftArray[leftPointer] <= rightArray[rightPointer]) {
                    array[mergedIndex] = leftArray[leftPointer]
                    leftPointer++
                } else {
                    array[mergedIndex] = rightArray[rightPointer]
                    rightPointer++
                }
                mergedIndex++
            }

            // Copy leftovers from leftArray
            while (leftPointer < leftSize) {
                array[mergedIndex] = leftArray[leftPointer]
                leftPointer++
                mergedIndex++
            }

            // Copy leftovers from rightArray
            while (rightPointer < rightSize) {
                array[mergedIndex] = rightArray[rightPointer]
                rightPointer++
                mergedIndex++
            }
        }
    }

}