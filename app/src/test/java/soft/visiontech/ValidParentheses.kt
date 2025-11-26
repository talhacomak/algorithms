package soft.visiontech

import org.junit.Test

class ValidParentheses {

    @Test
    fun test() {
        println(isValid("()"))
        println(isValid("()[]{}"))
        println(isValid("(]"))
        println(isValid("([])"))
        println(isValid("([)]"))
    }

    fun isValid(s: String): Boolean {
        if (s.isEmpty()) return true
        if (s.length % 2 == 1) return false

        val map = mapOf(
            ')' to '(',
            '}' to '{',
            ']' to '['
        )

        val chars : MutableList<Char> = mutableListOf()
        s.forEachIndexed { index: Int, ch: Char ->
            if (map.containsValue(ch)) {
                chars.add(ch)
            }
            else if (map[ch] != null) {
                if (chars.isEmpty())
                    return false
                else if (chars.last() == map[ch]) {
                    chars.removeAt(chars.lastIndex)
                }
                else
                    return false
            }
        }

        return chars.isEmpty()
    }

    fun isValidBetter(s: String): Boolean {
        if (s.isEmpty()) return true
        if (s.length % 2 == 1) return false

        val stack = ArrayDeque<Char>(s.length)

        for (ch in s) {
            when (ch) {
                '(', '{', '[' -> {
                    stack.addLast(ch)
                }

                ')' -> {
                    if (stack.isEmpty() || stack.removeLast() != '(') return false
                }

                '}' -> {
                    if (stack.isEmpty() || stack.removeLast() != '{') return false
                }

                ']' -> {
                    if (stack.isEmpty() || stack.removeLast() != '[') return false
                }

                else -> {}
            }
        }

        return stack.isEmpty()
    }
}