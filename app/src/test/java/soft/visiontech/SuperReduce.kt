package soft.visiontech

import org.junit.Test

class SuperReduce {

    @Test
    fun super_reduce_test() {
        val s = "caabbcx"
        val result = superReducedString(s)
        println(result)
    }

    fun superReducedString(s: String): String {
        val result = s.toMutableList()
        var size = result.size
        var index = 1
        while (index < size) {
            if (result[index - 1] == result[index]) {
                result.removeAt(index)
                result.removeAt(index - 1)
                size -= 2
                index -= 2
            }
            index++
            if (index < 1)
                index = 1
        }

        return if (result.isEmpty()) "Empty String" else result.joinToString(separator = "")
    }
}