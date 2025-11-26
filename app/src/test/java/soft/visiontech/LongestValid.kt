package soft.visiontech

import org.junit.Test
import kotlin.math.max
import kotlin.text.iterator

class LongestValid {

    @Test
    fun test() {
        println(longestValidParentheses("))))())()()(()"))
        println(longestValidParentheses("())()())"))
        println(longestValidParentheses("(()"))
        println(longestValidParentheses("()(()"))
        println(longestValidParentheses("(()()"))
    }

    fun longestValidParentheses(s: String): Int {
        val start = '('
        val end = ')'

        var stackCount = 0
        var result = 0
        var size = 0
        var index = 0
        var startIndex = 0
        while (index < s.length) {
            val ch = s[index]
            if (ch == start)
                stackCount++
            else if (ch == end) {
                if (stackCount > 0) {
                    stackCount--
                    size++
                }
                else {
                    result = max(size, result)
                    stackCount = 0
                    size = 0
                    startIndex = index
                }
            }
            index ++
        }
        if (stackCount > 0) {
            var reversSize = 0
            var reverseResult = 0
            val reverseStack = mutableMapOf<Char, Int>()

            var i = index - 1
            while (i >= startIndex) {
                val ch = s[i]
                if (ch == end) {
                    reverseStack.put(end, (reverseStack[end] ?: 0) + 1)
                }
                else if (ch == start) {
                    val stackCount = reverseStack[end]
                    if (stackCount != null && stackCount > 0) {
                        reverseStack.put(end, stackCount - 1)
                        reversSize++
                    }
                    else {
                        reverseResult = max(reversSize, reverseResult)
                        reverseStack.clear()
                        reversSize = 0
                    }
                }
                reverseResult = max(reversSize, reverseResult)

                i--
            }
            size = reverseResult
        }

        result = max(size, result)
        return 2*result
    }

    fun longestValidParenthesesBest(s: String): Int {
        var maxLen = 0

        // Left to right
        var leftCount = 0
        var rightCount = 0
        for (ch in s) {
            if (ch == '(') {
                leftCount++
            } else {
                rightCount++
            }

            if (leftCount == rightCount) {
                val currentLen = 2 * rightCount
                if (currentLen > maxLen) {
                    maxLen = currentLen
                }
            } else if (rightCount > leftCount) {
                leftCount = 0
                rightCount = 0
            }
        }

        // Right to left
        leftCount = 0
        rightCount = 0
        for (i in s.indices.reversed()) {
            val ch = s[i]
            if (ch == '(') {
                leftCount++
            } else {
                rightCount++
            }

            if (leftCount == rightCount) {
                val currentLen = 2 * leftCount
                if (currentLen > maxLen) {
                    maxLen = currentLen
                }
            } else if (leftCount > rightCount) {
                leftCount = 0
                rightCount = 0
            }
        }

        return maxLen
    }
}