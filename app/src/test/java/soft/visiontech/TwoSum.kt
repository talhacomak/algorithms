package soft.visiontech

import org.junit.Test

class TwoSum {
    @Test
    fun two_sum_test() {
        val nums1 = intArrayOf(1,2,3,4,6)
        val target1 = 10
        println(twoSumSorted(nums1, target1).contentToString())

        val nums2 = intArrayOf(-5,-3,-1,2,4,6)
        val target2 = 1
        println(twoSumSorted(nums2, target2).contentToString())

        val nums3 = intArrayOf(0,1,2,3,4)
        val target3 = 7
        println(twoSumSorted(nums3, target3).contentToString())

        val nums4 = intArrayOf(-10,-5,-2,0,9)
        val target4 = -12
        println(twoSumSorted(nums4, target4).contentToString())

        val nums5 = intArrayOf(1,3,5,7)
        val target5 = 10
        println(twoSumSorted(nums5, target5).contentToString())

        val nums6 = intArrayOf(-8,-4,-2,1,3,5)
        val target6 = -6
        println(twoSumSorted(nums6, target6).contentToString())

        val nums7 = intArrayOf(2,3,4)
        val target7 = 6
        println(twoSumSorted(nums7, target7).contentToString())

        val nums8 = intArrayOf(-3,-1,0,2,4,8)
        val target8 = 7
        println(twoSumSorted(nums8, target8).contentToString())

        val nums9 = intArrayOf(1,2)
        val target9 = 4
        println(twoSumSorted(nums9, target9).contentToString())

        val nums10 = intArrayOf(-2,-1,1,2)
        val target10 = 0
        println(twoSumSorted(nums10, target10).contentToString())

        val nums11 = intArrayOf(5,6,7,8,9)
        val target11 = 13
        println(twoSumSorted(nums11, target11).contentToString())

        val nums12 = intArrayOf(-7,-3,2,3,11)
        val target12 = 8
        println(twoSumSorted(nums12, target12).contentToString())

        val nums13 = intArrayOf(1,1,1,1,1)
        val target13 = 2
        println(twoSumSorted(nums13, target13).contentToString())

        val nums14 = intArrayOf(-1,0,1,2,3)
        val target14 = 5
        println(twoSumSorted(nums14, target14).contentToString())

        val nums15 = intArrayOf(0,0,0,0)
        val target15 = 0
        println(twoSumSorted(nums15, target15).contentToString())

        val nums16 = intArrayOf(-9,-4,-1,0,5,10)
        val target16 = 1
        println(twoSumSorted(nums16, target16).contentToString())

        val nums17 = intArrayOf(3,4,5,6,7)
        val target17 = 50
        println(twoSumSorted(nums17, target17).contentToString())

        val nums18 = intArrayOf(-6,-2,0,2,6)
        val target18 = 4
        println(twoSumSorted(nums18, target18).contentToString())

        val nums19 = intArrayOf(1,4,8,12,15)
        val target19 = 20
        println(twoSumSorted(nums19, target19).contentToString())

        val nums20 = intArrayOf(-5,-4,-3,-2,-1)
        val target20 = -7
        println(twoSumSorted(nums20, target20).contentToString())

    }

    fun twoSumSorted(nums: IntArray, target: Int): IntArray {
        var found = false
        for (firstIn in nums.indices) {
            var secondIn = firstIn + 1
            while (secondIn < nums.size) {
                if (nums[firstIn] + nums[secondIn] == target) {
                    found = true
                    break
                }
                else if (nums[firstIn] + nums[secondIn] > target)
                    break

                secondIn ++
            }

            if (found)
                return intArrayOf(firstIn, secondIn)
        }

        return intArrayOf()
    }

    fun twoSumSortedOptimized(nums: IntArray, target: Int): IntArray {
        var left = 0
        var right = nums.lastIndex

        while (left < right) {
            val sum = nums[left] + nums[right]

            if (sum == target)
                return intArrayOf(left, right)
            else if (sum < target)
                left++
            else right--
        }

        return intArrayOf()
    }
}