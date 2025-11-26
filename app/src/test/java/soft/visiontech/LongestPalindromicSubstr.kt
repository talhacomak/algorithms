package soft.visiontech

import org.junit.Test

class LongestPalindromicSubstr {

    @Test
    fun test() {
        println(longestPalindrome(""))
    }

    fun longestPalindrome(list: String): String {
        var longest = ""
        for (i in 0 until list.length) {
            var j = i -1
            var k = i
            val cur1 = mutableListOf<Char>()
            while (j >= 0 && k < list.length && list[j] == list[k]) {
                cur1.add(0, list[j])
                cur1.add(list[k])
                j --
                k ++
            }
            if (cur1.size > longest.length)
                longest = cur1.joinToString("")

            var m = i - 1
            var n = i + 1
            val cur2 = mutableListOf<Char>()
            cur2.add(list[i])
            while (m >= 0 && n < list.length && list[m] == list[n]) {
                cur2.add(0, list[m])
                cur2.add(list[n])
                m --
                n ++
            }
            if (cur2.size > longest.length)
                longest = cur2.joinToString("")
        }

        return longest
    }

    /*
    Tek merkez + çift merkez expand
    substring oluşturma yok. Her şey index + length ile takip
    Erken kırpma (pruning)
    Aynı harf bloklarını tek merkez sayma (büyük hız sağlar)
     */

    fun longestPalindromeFastest(s: String): String {
        val n = s.length
        if (n < 2) return s

        var bestStart = 0
        var bestLen = 1
        var i = 0

        while (i < n) {

            // Kalan uzunlukla en iyi palindromu bile geçemeyeceksek erken dur
            if (n - i <= bestLen / 2) break

            var left = i
            var right = i

            // 1) Aynı karakterlerden oluşan bloğu genişlet
            while (right + 1 < n && s[right + 1] == s[left]) {
                right++
            }

            // Yeni merkez: bu bloğun son karakterinin sonrası
            i = right + 1

            // 2) Bloğun iki tarafını simetrik genişlet
            while (left > 0 && right < n - 1 && s[left - 1] == s[right + 1]) {
                left--
                right++
            }

            // 3) Bu palindrom en iyiden uzunsa güncelle
            val newLen = right - left + 1
            if (newLen > bestLen) {
                bestLen = newLen
                bestStart = left
            }
        }

        return s.substring(bestStart, bestStart + bestLen)
    }

}