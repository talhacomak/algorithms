package soft.visiontech.algorithms

class BubbleSort {

    fun bubbleSort(nums: IntArray) {
        for (i in 0 until nums.size - 1) {
            var swapped = false

            for (j in 0 until nums.size - 1 - i) {
                if (nums[j] > nums[j + 1]) {
                    val temp = nums[j]
                    nums[j] = nums[j + 1]
                    nums[j + 1] = temp
                    swapped = true
                }
            }

            if (!swapped) break
        }
    }


}