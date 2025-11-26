package soft.visiontech

import org.junit.Test

class TwoSumUnsorted {

    @Test
    fun test() {
        println("answer = " + twoSumAllUnSorted(intArrayOf(1, 1, 1), 2).joinToString(" "))
    }

    /*fun alternate(s: String): Int {
        val uniqueSet = s.toSet()
        val charMap = mutableMapOf<Pair<Char, Char>, Int>()
        uniqueSet.forEach { first ->
            uniqueSet.forEach { second ->
                charMap.put(Pair(first, second), 0)
            }
        }

        // "abbca"
        s.forEachIndexed { index, ch ->
            charMap.get(Pair(ch, ))
        }
    }*/

    fun twoSumUnSorted(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        for (i in nums.indices) {
            val complement = target - nums[i]

            if (map.containsKey(complement)) {
                return intArrayOf(map[complement]!!, i)
            }
            map[nums[i]] = i
        }

        return intArrayOf()
    }

    fun twoSumBruteForceUnSorted(nums: IntArray, target: Int): IntArray {
        for (i in 0 until nums.size) {
            for (j in i + 1 until nums.size){
                if (nums[i] + nums[j] == target)
                    return intArrayOf(nums[i], nums[j])
            }
        }

        return intArrayOf()
    }

    fun twoSumAllUnSorted(nums: IntArray, target: Int): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()

        for (i in 0 until nums.size) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target) {
                    result.add(nums[i] to nums[j])
                }
            }
        }

        return result
    }

    fun twoSumSorted(nums: IntArray, target: Int): IntArray {
        var i = 0
        var j = nums.size -1
        while (i < j) {
            if (nums[i] + nums[j] == target)
                return intArrayOf(i, j)
            if (nums[i] + nums[j] < target)
                i++
            else
                j--
        }
        return intArrayOf()
    }


    fun threeSum(nums: IntArray): List<List<Int>> {
        val list = mutableListOf<List<Int>>()
        val tripleArray = mutableListOf<List<Int>>()
        for (i in 0 until nums.size) {
            for (j in i + 1 until nums.size) {
                val target = -1*(nums[i] + nums[j])
                val t = listOf(nums[i], nums[j], target).sorted()
                if (tripleArray.contains(t))
                    continue
                tripleArray.add(t)
                for (k in j + 1 until nums.size) {
                    if (nums[k] == target) {
                        list.add(listOf(nums[i], nums[j], nums[k]))
                        break
                    }
                }
            }
        }
        return list
    }

    fun threeSumBest(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (nums.size < 3) return result

        nums.sort()

        for (i in 0 until nums.size - 2) {
            // Skip duplicates for i
            if (i > 0 && nums[i] == nums[i - 1]) continue

            var left = i + 1
            var right = nums.size - 1

            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]

                when {
                    sum == 0 -> {
                        result.add(listOf(nums[i], nums[left], nums[right]))

                        // Skip duplicates for left
                        while (left < right && nums[left] == nums[left + 1]) left++

                        // Skip duplicates for right
                        while (left < right && nums[right] == nums[right - 1]) right--

                        left++
                        right--
                    }

                    sum < 0 -> left++
                    else -> right--
                }
            }
        }

        return result
    }

}