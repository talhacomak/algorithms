package soft.visiontech

import org.junit.Test
import kotlin.math.max

class LongestSubString {

    @Test
    fun test() {
        println(longestSubsequentSubstring("dvdf"))
    }

    fun lengthOfLongestSubstringSimple(s: String): Int {
        return s.toSet().size
    }

    fun lengthOfLongestSubstring(s: String): Int {
        val map = mutableMapOf<Char, Int>()
        s.forEach { it ->
            println("char: $it map[char]: ${map[it]}")
            map.put(it, (map[it] ?: 0) + 1)
            println("map size: ${map.size}")
        }
        return map.size
    }

    fun longestSubsequentSubstring(s: String): Int {
        if (s == "")
            return 0
        var max = 1
        val list = s.toList()
        var subs = mutableListOf<Char>()
        subs.add(list[0])
        for (i in 0 until s.length - 1) {
            if (!subs.contains(list[i + 1])) {
                subs.add(list[i + 1])
                max = max(max, subs.size)
            }
            else {
                val index = subs.indexOf(list[i + 1])
                if (list[i] == list[i + 1])
                    subs.clear()
                else {
                    subs = subs.subList(index + 1, subs.size)
                }
                subs.add(list[i + 1])
            }
        }

        return max(max, subs.size)
    }
}