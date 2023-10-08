package cube

class CubePlatform {

    private var cube= Cube();

    //количество кубиков для хода
    var countOfCube:Int=1
        set(value) {
            if (value<1){
                println("Количество бросаемых кубиков не может быть меньше 1. Текущее значение: ${value}")
                throw Exception("Неправильные данные для количества кубиков")
            }
            field=value
        }

    // граница удачного броска кубика
    companion object {
        private const val SUCCESSFUL_COUNT_OF_CUBE_SIDE:Int = 4
    }

    // бросок всех кубиков, вплоть до удачной попытки или ее отсутсвия
    public fun throwAllCubes():Boolean{
        for (i in 0 until countOfCube) {
            val valueOfCube = cube.throwTheCube()
            println("\t \t Значение брошенного кубика: ${valueOfCube}")
            if (valueOfCube > SUCCESSFUL_COUNT_OF_CUBE_SIDE) {
                return true
            }
        }
        return false
    }
}
