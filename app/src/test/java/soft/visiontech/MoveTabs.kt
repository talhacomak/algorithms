package soft.visiontech

class MoveTabs(
    private val tabs: MutableList<Int> // current tab order
) {

    // the only way to move a tab is using moveTab function
    // Primitive: this is the only allowed way to change the tab ordering.
    fun moveTab(tabId: Int, targetIndex: Int) {
        val currentIndex = tabs.indexOf(tabId)
        if (currentIndex == -1 || currentIndex == targetIndex) return

        tabs.removeAt(currentIndex)
        tabs.add(targetIndex, tabId)
    }

    fun moveTabGroups(tabGroups: List<Int>, index: Int) {
        if (tabGroups.isEmpty()) return

        // 1) Build a position map: tabId -> current index
        val positionById = mutableMapOf<Int, Int>()
        for ((i, id) in tabs.withIndex()) {
            positionById[id] = i
        }

        // 2) Sort selected tabs by current position (left to right)
        val selectedTabs = tabGroups
            .filter { positionById.containsKey(it) } // ignore invalid ids
            .sortedBy { positionById[it]!! }

        if (selectedTabs.isEmpty()) return

        // 3) Adjust the drop index:
        // index is given in the original array *before* removing the selected tabs.
        // After removing selected tabs that are before 'index', the insertion index shifts left.
        val countSelectedBeforeIndex = selectedTabs.count { positionById[it]!! < index }
        val adjustedIndex = index - countSelectedBeforeIndex

        val firstSelectedPos = positionById[selectedTabs.first()]!!
        val lastSelectedPos = positionById[selectedTabs.last()]!!

        // 4) Decide moving direction:
        // If we are moving block to the left side, we move from left to right.
        // If we are moving to the right side, we move from right to left.
        if (adjustedIndex <= firstSelectedPos) {
            // Move block to the left (or keep in place).
            var currentTargetIndex = adjustedIndex
            for (tabId in selectedTabs) {
                moveTab(tabId, currentTargetIndex)
                currentTargetIndex++
            }
        } else if (adjustedIndex > lastSelectedPos) {
            // Move block to the right.
            // After the move, the block will occupy [adjustedIndex, adjustedIndex + size - 1].
            var currentTargetIndex = adjustedIndex + selectedTabs.size - 1
            for (tabId in selectedTabs.asReversed()) {
                moveTab(tabId, currentTargetIndex)
                currentTargetIndex--
            }
        } else {
            // Edge case: target inside the block itself.
            // In many UIs, dropping inside the same block is a no-op.
            // We can simply return.
            return
        }
    }
}