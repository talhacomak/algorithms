package soft.visiontech

import org.junit.Test

class MoveDuplicates {

    @Test
    fun test() {
        val nums = intArrayOf(0,0,1,1,1,2,2,3,3,4)
        val count = removeDuplicates(nums)
        println("count: $count")
        nums.forEach {
            println(it)
        }
    }

    fun removeDuplicates(nums: IntArray): Int {
        var i = 0
        var j = 0
        while (i < nums.size - 1) {
            while (j < nums.size -1 && nums[j] == nums[j + 1]) {
                j++
            }
            if (j == nums.size -1)
                break
            nums[i + 1] = nums[j + 1]
            i++
            j++
        }

        return i + 1
    }

}
