package soft.visiontech.algorithms

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class InsertionSort {

    @Test
    fun insertion_sort() {
        val list = mutableListOf(80, 75, 80, 60)
        val res = insertionSort(list)
        println(res)
    }

    fun insertionSort(list: MutableList<Int>): List<Int> {
        for (i in 1 until list.size) {
            val key = list[i]
            var j = i - 1
            while (j >= 0 && list[j] > key) {
                list[j + 1] = list[j]
                j--
            }
            list[j + 1] = key
        }
        return list
    }

    /* Insertion Sort
    for i = 1 to n-1:
    key = A[i]
    j = i-1
    while j >= 0 and A[j] > key:
        A[j+1] = A[j]
        j--
    A[j+1] = key
     */
}