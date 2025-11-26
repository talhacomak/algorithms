package soft.visiontech

import org.junit.Test
import kotlin.math.min

class LongestCommonPrefix {

    @Test
    fun test() {
        println(longestCommonPrefix(arrayOf("asdf", "asd", "asf")))
    }

    fun longestCommonPrefix(strs: Array<String>): String {
        var minSize = Int.MAX_VALUE
        for (str in strs) {
            minSize = min(str.length, minSize)
        }

        var i = 0
        while (i < minSize) {
            var exit = false
            for (j in 1 until strs.size) {
                if (strs[j][i] != strs[j - 1][i]) {
                    exit = true
                    break
                }
            }
            if (exit)
                break
            i ++
        }
        return strs[0].substring(0, i)
    }
}