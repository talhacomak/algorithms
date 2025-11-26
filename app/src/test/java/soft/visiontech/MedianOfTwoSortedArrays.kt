package soft.visiontech

import org.junit.Test

class MedianOfTwoSortedArrays {
    @Test
    fun test() {
        println(findMedianSortedArrays(nums1 = intArrayOf(1), nums2 = intArrayOf(2,3,4,5,6)))
    }


    private fun medianOfSingleArray(nums: IntArray): Double {
        val size = nums.size
        val midIndex = size / 2
        return if (size % 2 == 0) {
            (nums[midIndex - 1] + nums[midIndex]) / 2.0
        } else {
            nums[midIndex].toDouble()
        }
    }

    private fun medianFromPrefixAndTail(
        prefix: IntArray,
        prefixSize: Int,
        tail: IntArray,
        tailStartIndex: Int,
        totalLength: Int
    ): Double {
        val isEven = (totalLength % 2 == 0)
        val medianIndex = totalLength / 2

        fun getAt(globalIndex: Int): Int {
            return if (globalIndex < prefixSize) {
                prefix[globalIndex]
            } else {
                val offset = globalIndex - prefixSize
                tail[tailStartIndex + offset]
            }
        }

        return if (!isEven) {
            getAt(medianIndex).toDouble()
        } else {
            val left = getAt(medianIndex - 1)
            val right = getAt(medianIndex)
            (left + right) / 2.0
        }
    }

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val size1 = nums1.size
        val size2 = nums2.size
        val totalLength = size1 + size2

        if (size1 == 0) return medianOfSingleArray(nums2)
        if (size2 == 0) return medianOfSingleArray(nums1)

        val isEven = (totalLength % 2 == 0)
        val medianIndex = totalLength / 2

        if (nums1[size1 - 1] < nums2[0]) {
            return if (!isEven) {
                val idx = medianIndex
                val value = if (idx < size1) nums1[idx] else nums2[idx - size1]
                value.toDouble()
            } else {
                val leftIndex = medianIndex - 1
                val rightIndex = medianIndex
                val left = if (leftIndex < size1) nums1[leftIndex] else nums2[leftIndex - size1]
                val right = if (rightIndex < size1) nums1[rightIndex] else nums2[rightIndex - size1]
                (left + right) / 2.0
            }
        }

        if (nums2[size2 - 1] < nums1[0]) {
            return if (!isEven) {
                val idx = medianIndex
                val value = if (idx < size2) nums2[idx] else nums1[idx - size2]
                value.toDouble()
            } else {
                val leftIndex = medianIndex - 1
                val rightIndex = medianIndex
                val left = if (leftIndex < size2) nums2[leftIndex] else nums1[leftIndex - size2]
                val right = if (rightIndex < size2) nums2[rightIndex] else nums1[rightIndex - size2]
                (left + right) / 2.0
            }
        }

        var index1 = 0
        var index2 = 0

        val prefix = IntArray(medianIndex + 1)
        var prefixSize = 0

        while (prefixSize <= medianIndex && index1 < size1 && index2 < size2) {
            val nextValue = if (nums1[index1] <= nums2[index2]) {
                nums1[index1++]
            } else {
                nums2[index2++]
            }
            prefix[prefixSize] = nextValue
            prefixSize++
        }

        if (prefixSize > medianIndex) {
            return if (!isEven) {
                prefix[medianIndex].toDouble()
            } else {
                val left = prefix[medianIndex - 1]
                val right = prefix[medianIndex]
                (left + right) / 2.0
            }
        }

        return if (index1 < size1) {
            medianFromPrefixAndTail(prefix, prefixSize, nums1, index1, totalLength)
        } else {
            medianFromPrefixAndTail(prefix, prefixSize, nums2, index2, totalLength)
        }
    }
}