package soft.visiontech

import org.junit.Test

class DrawTriangle {
    @Test
    fun test() {
        drawRightAlignedTriangle(6)
        println()
        drawInvertedTriangle(6)
        println()
        drawPyramid(6)
        println()
        drawHollowTriangle(6)
        println()
        drawRightAlignedHollowTriangle(6)
        println()
        drawNumberTriangle(6)
        println()
        drawNumberPyramid(6)
    }

    fun drawRightAlignedTriangle(n: Int) {
        val space = " "
        val star = "*"
        (1 .. n).forEach { index ->
            val curSpace = space.repeat(n - index)
            val curStar = star.repeat(index)
            println(curSpace + curStar)
        }
    }

    fun drawInvertedTriangle(n: Int) {
        val star = "*"
        (0 until n).forEach { index ->
            val curStar = star.repeat(n - index)
            println(curStar)
        }
    }

    fun drawPyramid(n: Int) {
        val space = " "
        val star = "*"
        (1 .. n).forEach { index ->
            val starCount = 2*index - 1
            val curStar = star.repeat(starCount)
            val curSpace = space.repeat((2*n - starCount) / 2)
            println(curSpace + curStar)
        }
    }

    fun drawHollowTriangle(n: Int) {
        val space = " "
        val star = "*"
        if (n > 1) {
            (1..n).forEach { starCount ->
                val internalSpaceCount = if (starCount <= 2) 0 else starCount - 2
                val internalSpace =
                    if (starCount == n) star.repeat(starCount - 2) else space.repeat(
                        internalSpaceCount
                    )
                val endStar = starCount >= 2
                println(star + internalSpace + (if (endStar) star else ""))
            }
        }
        else
            println("*")
    }

    fun drawRightAlignedHollowTriangle(n: Int) {
        val space = " "
        val star = "*"
        if (n > 1) {
            (1..n).forEach { starCount ->
                val curSpace = space.repeat(n - starCount)
                val internalSpaceCount = if (starCount <= 2) 0 else starCount - 2
                val internalSpace =
                    if (starCount == n) star.repeat(starCount - 2) else space.repeat(
                        internalSpaceCount
                    )
                val endStar = starCount >= 2
                println(curSpace + star + internalSpace + (if (endStar) star else ""))
            }
        }
        else
            println("*")
    }


    fun drawNumberTriangle(n: Int) {
        (1..n).forEach { i ->
            println((1..i).joinToString(""))
        }
    }

    fun drawNumberPyramid(n: Int) {
        (1..n).forEach { i ->
            val spaces = " ".repeat(n - i)
            println(spaces + (1..i).joinToString("") + (i - 1 downTo 1).joinToString(""))
        }
    }
}