package soft.visiontech

import org.junit.Test

class NonDivisibleSubset {

    @Test
    fun non_divisible_test() {
        println(nonDivisibleSubset(1, arrayOf(1,2,3,4,5)))
    }


    fun nonDivisibleSubset(k: Int, s: Array<Int>): Int {
        val subset = mutableMapOf<Int, Int>() // element, occurrence
        s.forEachIndexed { index, element ->
            for (nextIndex in (index + 1) until s.size) {
                val nextValue = s[nextIndex]
                if ((element + nextValue) % k != 0) {
                    subset[element] = (subset[element] ?: 0) + 1
                    subset[nextValue] = (subset[nextValue] ?: 0) + 1
                }

                if (k == 2 && subset.size >= 2)
                    return 2
            }
        }

        if (subset.isEmpty())
            return 0
        if ((subset.filter { it.value > 1 }).isEmpty())
            return 1

        val sortedSet: List<Int> =
            subset.entries
                .sortedByDescending { it.value }
                .map { it.key }
        val finalSet = mutableListOf<Int>()
        for (candidateElement in sortedSet) {
            var isValid = true
            for (existingElement in finalSet) {
                if ((existingElement + candidateElement) % k == 0) {
                    isValid = false
                    break
                }
            }
            if (isValid) {
                finalSet.add(candidateElement)
            }
        }

        return if (finalSet.isEmpty()) 1 else finalSet.size
    }

    fun nonDivisibleSubset2(k: Int, s: Array<Int>): Int {
        val finalSet = mutableListOf<Int>()
        for (candidateElement in s) {
            var isValid = true
            for (existingElement in finalSet) {
                if ((existingElement + candidateElement) % k == 0) {
                    isValid = false
                    break
                }
            }
            if (isValid) {
                finalSet.add(candidateElement)
            }
        }

        return if (finalSet.isEmpty()) 1 else finalSet.size
    }
}