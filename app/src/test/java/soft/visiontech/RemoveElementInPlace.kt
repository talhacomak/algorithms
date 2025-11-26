package soft.visiontech

import org.junit.Test

class RemoveElementInPlace {

    @Test
    fun test() {
        val nums = intArrayOf(3,2,2,3)
        val res = removeElement(nums, 3)
        nums.forEach {
            println(it)
        }
        println("res: $res")
    }

    fun removeElement(nums: IntArray, element: Int): Int {
        if (nums.isEmpty())
            return 0
        var count = if (nums[0] == element) 1 else 0
        for (i in 1 until nums.size) {
            if (nums[i] == element) {
                count++
                continue
            }
            val key = nums[i]
            var j = i - 1
            while (j >= 0 && nums[j] == element) {
                nums[j + 1] = nums[j]
                j--
            }
            nums[j + 1] = key
        }
        return nums.size - count
    }
}