package parameters

class Defense{


    // ряд констант связанных с защитой, атакой и здоровьем
    companion object {
        private const val MIN_DEFENSE:Int = 1
        private const val MAX_DEFENSE:Int = 30
    }

    constructor(defense:Int) {
        this.defense=defense
    }

    // параметр защита, проверка на разрешенный диапазон
    var defense:Int=0
        @Throws(Exception::class)
        private set(value) {
            if (value < MIN_DEFENSE || value > MAX_DEFENSE) {
                println("Значение параметра \"defense\" должно быть указано как целое число от $MIN_DEFENSE до $MAX_DEFENSE. Текущее значение: $value.")
                throw Exception("Ошибка присваивания параметра \"defense\"")
            }
            field = value
        }
}