package soft.visiontech

import org.junit.Test

class MergeTwoLinked {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        var tail1 = list1
        var tail2 = list2
        var result = ListNode(0)
        val headResult = result
        while (tail1 != null && tail2 != null) {
            if (tail1.`val` <= tail2.`val`) {
                result.next = ListNode(tail1.`val`)
                tail1 = tail1.next
            } else {
                result.next = ListNode(tail2.`val`)
                tail2 = tail2.next
            }
            result = result.next!!
        }

        while (tail1 != null) {
            result.next = ListNode(tail1.`val`)
            result = result.next!!
            tail1 = tail1.next
        }
        while (tail2 != null) {
            result.next = ListNode(tail2.`val`)
            result = result.next!!
            tail2 = tail2.next
        }

        return headResult.next
    }


    // list1 = [1,2,4], list2 = [1,3,4]

    @Test
    fun test() {
        val l1 = ListNode(1)
        l1.next = ListNode(2)
        l1.next!!.next = ListNode(4)

        val l2 = ListNode(1)
        l2.next = ListNode(3)
        l2.next!!.next = ListNode(4)
        var res = mergeTwoLists(l1, l2)
        while (res != null) {
            println(res.`val`)
            res = res.next
        }
    }
}