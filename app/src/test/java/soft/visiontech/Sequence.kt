package soft.visiontech

import org.junit.Test

class Sequence {

    @Test
    fun sequence() {
        val list = arrayOf(1, 2, 3, 4, 5, 6, 7)
        list.asSequence().take(5)

        val sequenceList = generateSequence(2) {
            it*it
        }.take(5).toList()
    }
}