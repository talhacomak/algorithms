package soft.visiontech

import org.junit.Test
import kotlin.math.max

class LargestStableSubarray {

    @Test
    fun test() {
        runLongestStableSubarrayTests()
    }

    fun longestStableSubarray(nums: IntArray, k: Int): Int {
        if (nums.isEmpty())
            return 0
        if (nums.size == 1) {
            return if (k == 0)
                1
            else if (nums[0] <= k)
                1
            else
                0
        }

        var max = 0
        if (k == 0) {
            var i = 0
            while (i < nums.size - 1) {
                var cur = 0
                while (i < nums.size - 1 && nums[i] == nums[i+1]) {
                    cur++
                    i++
                }
                max = max(max, cur + 1)
                i++
            }

            return max
        }
        else {
            for (i in nums) {
                
            }
        }
        

        /*
        var i = 0
        var j = nums.size - 1
        while (i < j) { // at least 2 elements
            if (nums[j] - nums[i] <= k)
                return (j - i + 1)
            if (nums[j] - nums[j - 1] > nums[i + 1] - nums[i])
                j --
            else
                i ++
        }
        if (nums[0] <= k)
            return 1*/
        return 0
    }


    data class TestCase(
        val nums: IntArray,
        val k: Int,
        val expected: Int
    )

    fun runLongestStableSubarrayTests() {
        val testCases = listOf(
            // Test 1: Single element, k = 0
            TestCase(nums = intArrayOf(1), k = 0, expected = 1),

            // Test 2: Single element, larger k
            TestCase(nums = intArrayOf(1), k = 5, expected = 1),

            // Test 3: Mixed small array, non-trivial answer (should be 4: [3,2,4,2])
            TestCase(nums = intArrayOf(1, 3, 2, 4, 2), k = 2, expected = 4),

            // Test 4: All equal, k = 0
            TestCase(nums = intArrayOf(5, 5, 5, 5), k = 0, expected = 4),

            // Test 5: Example style with k = 5
            TestCase(nums = intArrayOf(10, 1, 2, 4, 7, 2), k = 5, expected = 4),

            // Test 6: Same array, k = 0, only single elements stable
            TestCase(nums = intArrayOf(10, 1, 2, 4, 7, 2), k = 0, expected = 1),

            // Test 7: Increasing, large k, whole array is stable
            TestCase(nums = intArrayOf(1, 2, 3, 4, 5), k = 10, expected = 5),

            // Test 8: Increasing, k = 0, only equal neighbors
            TestCase(nums = intArrayOf(1, 2, 3, 4, 5), k = 0, expected = 1),

            // Test 9: Increasing, small k
            // Best is any pair like [1,2], [2,3], etc. so length 2
            TestCase(nums = intArrayOf(1, 2, 3, 4, 5), k = 1, expected = 2),

            // Test 10: All equal negatives, k = 0
            TestCase(nums = intArrayOf(-1, -1, -1), k = 0, expected = 3),

            // Test 11: Mixed negatives, k = 3
            TestCase(nums = intArrayOf(-5, -2, -3, -4, -1), k = 3, expected = 4),

            // Test 12: Same negatives, tighter k
            TestCase(nums = intArrayOf(-5, -2, -3, -4, -1), k = 1, expected = 2),

            // Test 13: Sparse bigger jumps
            TestCase(nums = intArrayOf(1, 3, 6, 7, 9, 10), k = 3, expected = 3),

            // Test 14: Same array, k = 0, only single elements
            TestCase(nums = intArrayOf(1, 3, 6, 7, 9, 10), k = 0, expected = 1),

            // Test 15: Classic style example
            TestCase(nums = intArrayOf(8, 2, 4, 7), k = 4, expected = 2),

            // Test 16: Same array, slightly larger k
            TestCase(nums = intArrayOf(8, 2, 4, 7), k = 5, expected = 3),

            // Test 17: Many repeats, k = 0
            // Any stable subarray must have all equal elements
            TestCase(nums = intArrayOf(4, 2, 2, 2, 4, 4, 2, 2), k = 0, expected = 3),

            // Test 18: Same array, k = 2, whole array should be stable
            TestCase(nums = intArrayOf(4, 2, 2, 2, 4, 4, 2, 2), k = 2, expected = 8),

            // Test 19: Clustered values, k = 0
            TestCase(nums = intArrayOf(1, 1, 1, 2, 2, 3, 3, 3, 3), k = 0, expected = 4),

            // Test 20: Same array, k = 1
            TestCase(nums = intArrayOf(1, 1, 1, 2, 2, 3, 3, 3, 3), k = 1, expected = 6),

            // Test 21: Same array, k = 2, full array is stable
            TestCase(nums = intArrayOf(1, 1, 1, 2, 2, 3, 3, 3, 3), k = 2, expected = 9),

            // Test 22: Extreme values, k large enough to include both
            TestCase(nums = intArrayOf(1000000000, -1000000000), k = 2000000000, expected = 2),

            // Test 23: Extreme values, k just smaller than needed
            TestCase(nums = intArrayOf(1000000000, -1000000000), k = 1999999999, expected = 1),

            // Test 24: Mixed positive and negative, k = 5
            TestCase(nums = intArrayOf(3, 4, 7, -2, 2, 1, 4, 2), k = 5, expected = 4),

            // Test 25: Same array, k = 7
            TestCase(nums = intArrayOf(3, 4, 7, -2, 2, 1, 4, 2), k = 7, expected = 5)
        )

        for ((index, test) in testCases.withIndex()) {
            val actual = longestStableSubarray(test.nums, test.k)
            val passed = actual == test.expected

            println(
                buildString {
                    append("Test ${index + 1}: ")
                    append("nums=${test.nums.contentToString()}, k=${test.k} -> ")
                    append("expected=${test.expected}, actual=$actual, ")
                    append("passed=$passed")
                }
            )
        }
    }
}