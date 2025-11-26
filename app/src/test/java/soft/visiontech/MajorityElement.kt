package soft.visiontech

import org.junit.Test

class MajorityElement {

    @Test
    fun majority_element() {
        val elements1 = arrayOf(1, 2, 0, 2, 1, 1, 2)
        println(majorityElementsNOver3(elements1))
    }

    fun findMajorityElement(elements: Array<Int>): Int {
        val size = elements.size
        val condition = size/2 + 1
        elements.sort()
        var prev = Int.MAX_VALUE
        var counter = 1
        var result = 0
        for (element in elements) {
            if (element == prev)
                counter ++
            else {
                counter = 1
                prev = element
            }
            if (counter == condition) {
                result = prev
                break
            }
        }

        return result
    }

    fun findMajorityElement2(elements: Array<Int>): Int {
        val size = elements.size
        elements.sort()
        return elements[size/2]
    }

    fun majorityElement(nums: IntArray): Int {
        var candidate = 0
        var count = 0

        for (num in nums) {
            if (count == 0) {
                candidate = num
            }
            count += if (num == candidate) 1 else -1
        }

        return candidate
    }

    // 1, 2, 0, 2, 1, 1, 2

    // first one is candidate one. do the same process as original algo
    // second different element is candidate two, then set the count as - current index
    fun majorityElementsNOver3(nums: Array<Int>): List<Int> {
        var candidate1: Int? = null
        var candidate2: Int? = null
        var count1 = 0
        var count2 = 0

        // 1) First pass: find possible candidates
        for (num in nums) {
            when {
                candidate1 != null && num == candidate1 -> {
                    count1++
                }
                candidate2 != null && num == candidate2 -> {
                    count2++
                }
                count1 == 0 -> {
                    candidate1 = num
                    count1 = 1
                }
                count2 == 0 -> {
                    candidate2 = num
                    count2 = 1
                }
                else -> {
                    count1--
                    count2--
                }
            }
        }

        // 2) Second pass: verify counts
        count1 = 0
        count2 = 0
        for (num in nums) {
            if (candidate1 != null && num == candidate1) {
                count1++
            } else if (candidate2 != null && num == candidate2) {
                count2++
            }
        }

        val result = mutableListOf<Int>()
        val threshold = nums.size / 3

        if (candidate1 != null && count1 > threshold) {
            result.add(candidate1)
        }
        if (candidate2 != null && candidate2 != candidate1 && count2 > threshold) {
            result.add(candidate2)
        }

        return result
    }


}