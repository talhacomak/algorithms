package soft.visiontech

import org.junit.Test

class SubArrayMaxSum {

    @Test
    fun test() {
        val nums1  = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)          // 6
        println(maxSubArray(nums1))

        val nums2  = intArrayOf(1)                                      // 1
        println(maxSubArray(nums2))

        val nums3  = intArrayOf(5,4,-1,7,8)                             // 23
        println(maxSubArray(nums3))

        val nums4  = intArrayOf(-1,-2,-3,-4)                            // -1
        println(maxSubArray(nums4))

        val nums5  = intArrayOf(0,0,0,0)                                // 0
        println(maxSubArray(nums5))

        val nums6  = intArrayOf(1,2,3,4,5)                              // 15
        println(maxSubArray(nums6))

        val nums7  = intArrayOf(-2,-1)                                  // -1
        println(maxSubArray(nums7))

        val nums8  = intArrayOf(100,-90,80,-70,60,-50,40,-30,20,-10)    // 100
        println(maxSubArray(nums8))

        val nums9  = intArrayOf(-10,20,-30,40,-50,60)                   // 60
        println(maxSubArray(nums9))

        val nums10 = intArrayOf(2,-1,2,3,4,-5)                          // 10
        println(maxSubArray(nums10))

        val nums11 = intArrayOf(-5,4,-1,7,-8,10)                        // 12
        println(maxSubArray(nums11))

        val nums12 = intArrayOf(8,-19,5,-4,20)                          // 21
        println(maxSubArray(nums12))

        val nums13 = intArrayOf(-3,1,3,-1,2,-1)                         // 5
        println(maxSubArray(nums13))

        val nums14 = intArrayOf(0,-3,1,1,-1,1,1,-3,0)                   // 3
        println(maxSubArray(nums14))

        val nums15 = intArrayOf(-1,0,-1,0,-1)                           // 0
        println(maxSubArray(nums15))

        val nums16 = intArrayOf(Int.MIN_VALUE)                         // Int.MIN_VALUE
        println(maxSubArray(nums16))

        val nums17 = intArrayOf(100, -1, -1, -1, -1, 200)               // 200
        println(maxSubArray(nums17))

        val nums18 = intArrayOf(-2, -3, 4, -1, -2, 1, 5, -3)            // 7
        println(maxSubArray(nums18))

        val nums19 = intArrayOf(3, -2, 5, -1)                           // 6
        println(maxSubArray(nums19))

        val nums20 = intArrayOf(-5, -1, -8, -9, -3)                     // -1
        println(maxSubArray(nums20))
    }

    /*
    (-2, 1, -3, 4, -7, 1, 1, 1, 1, 1)
    largestSum = 4
    currentSum = 3
     */

    fun maxSubArray(nums: IntArray): Int {
        var currentSum = nums[0]
        var largestSum = nums[0]

        for (i in 1 until nums.size) {
            val num = nums[i]
            currentSum = maxOf(num, currentSum + num)
            largestSum = maxOf(largestSum, currentSum)
        }

        return largestSum
    }


}