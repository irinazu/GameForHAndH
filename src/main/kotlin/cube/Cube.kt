package cube

import java.util.*


class Cube {

    private var random = Random()

    // бросок кубика
    fun throwTheCube(): Int {
        return random.nextInt(MAX_CUBE_SIDE) + MIN_CUBE_SIDE
    }

    // мин и макс сторона кубика
    companion object {
        private const val MIN_CUBE_SIDE:Int = 1
        private const val MAX_CUBE_SIDE:Int = 6
    }
}

