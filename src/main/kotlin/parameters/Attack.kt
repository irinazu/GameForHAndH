package parameters

class Attack {

    companion object {
        private const val MIN_ATTACK:Int = 1
        private const val MAX_ATTACK:Int = 30
    }

    constructor(attack: Int){
        this.attack=attack
    }

    // параметр атака, проверка на разрешенный диапазон
    var attack:Int = 0
        @Throws(Exception::class)
        private set(value) {
            if (value < MIN_ATTACK || value > MAX_ATTACK) {
                println("Значение параметра \"attack\" должно быть указано как целое число от $MIN_ATTACK до $MAX_ATTACK. Текущее значение: ${value}")
                throw Exception("Ошибка присваивания параметров attack: число должно быть целым")
            }
            field = value
        }

    // подсчет модификатора атаки
    fun countAttackModifier(defenseOfDefendingCreature: Int): Int {
        var attackModifier = attack - defenseOfDefendingCreature + 1
        if (attackModifier < 1) {
            attackModifier = 1
        }
        println("\t Количество кубиков, которые будут брошены: $attackModifier")
        return attackModifier
    }

}