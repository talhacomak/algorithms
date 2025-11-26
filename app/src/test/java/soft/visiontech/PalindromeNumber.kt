package soft.visiontech

import org.junit.Test
import kotlin.math.abs

class PalindromeNumber {
    @Test
    fun test() {
        println(isPalindrome(101))
    }

    fun isPalindrome(x: Int): Boolean {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false

        var num = x
        var reversedHalf = 0

        while (num > reversedHalf) {
            reversedHalf = reversedHalf * 10 + num % 10
            num /= 10
        }

        return num == reversedHalf || num == reversedHalf / 10
    }
}