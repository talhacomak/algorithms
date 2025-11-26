package soft.visiontech

import org.junit.Test

class AddTwoLinked {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    @Test
    fun test() {
        val l1 = ListNode(2)
        l1.next = ListNode(4)
        l1.next!!.next = ListNode(3)

        val l2 = ListNode(5)
        l2.next = ListNode(6)
        l2.next!!.next = ListNode(4)
        var res = addTwoNumbers(l1, l2)
        while (res != null) {
            println(res.`val`)
            res = res.next
        }
    }

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var list1 = l1
        var list2 = l2
        var remain: Int

        var sum : Int = (list1?.`val` ?: 0) + (list2?.`val` ?: 0)
        var digit = sum%10
        remain = sum/10
        println("l1 digit: ${list1?.`val`}, l2 digit: ${list2?.`val`}")
        println("sum: $sum, digit: $digit, remain: $remain")
        list1 = list1?.next
        list2 = list2?.next
        var result = ListNode(digit)
        val head = result

        while (list1 != null || list2 != null) {
            sum = (list1?.`val` ?: 0) + (list2?.`val` ?: 0) + remain
            digit = sum%10
            remain = sum/10

            println("l1 digit: ${list1?.`val`}, l2 digit: ${list2?.`val`}")
            println("sum: $sum, digit: $digit, remain: $remain")

            result.next = ListNode(digit)
            result = result.next!!

            list1 = list1?.next
            list2 = list2?.next
        }

        if (remain != 0)
            result.next = ListNode(remain)

        return head
    }
}