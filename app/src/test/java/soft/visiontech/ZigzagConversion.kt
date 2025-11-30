package soft.visiontech

import org.junit.Test

class ZigzagConversion {

    @Test
    fun test() {
        println(fastConvert("PAYPALISHIRING", 3))
    }

    fun fastConvert(s: String, numRows: Int): String {
        val skip = (numRows -1)*2 -1
        val arr: MutableList<Char> = MutableList(s.length) { ' ' }
        for (i in 0 until arr.size) {
            val index = (i + skip*i) % s.length
            arr[i] = s[index]
        }
        return arr.joinToString("").replace(" ", "")
    }

    fun convertBest(s: String, numRows: Int): String {
        if (numRows == 1 || numRows >= s.length) return s

        val result = StringBuilder(s.length)
        val cycleLen = 2 * (numRows - 1)

        for (row in 0 until numRows) {
            var index = row
            while (index < s.length) {
                // vertical element
                result.append(s[index])

                // diagonal element for middle rows
                if (row != 0 && row != numRows - 1) {
                    val diagonalIndex = index + cycleLen - 2 * row
                    if (diagonalIndex < s.length) {
                        result.append(s[diagonalIndex])
                    }
                }

                index += cycleLen
            }
        }

        return result.toString()
    }

    fun convert(s: String, numRows: Int): String {
        if (s.length <= 1)
            return s
        if (numRows == 1 || numRows >= s.length) return s
        val size = s.length/2 + 1
        val list = MutableList(numRows) { MutableList(size) {' '} }
        var index = 1
        var col = 0
        list[0][0] = s[0]
        while (col < size) {
            var row = 1
            while (row < numRows) {
                list[row][col] = s[index]
                index ++
                row++
                if (index == s.length)
                    break
            }

            if (index == s.length)
                break

            row -= 2
            col ++
            while (row >= 0) {
                list[row][col] = s[index]
                index ++
                row--
                col ++
                if (index == s.length)
                    break
            }
            col --
            if (index == s.length)
                break
        }
        var str = ""
        for (i in 0 until numRows) {
            println("list: ${list[i]}")
            str += list[i].joinToString("").replace(" ", "")
        }

        return str
    }
}