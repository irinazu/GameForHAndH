import parameters.TypeName

class Player private constructor(
    name: String,
    typeName: String,
    attack: Int,
    defense: Int,
    health: Long,
    minDamage: Long,
    maxDamage: Long
) : Creature(name, typeName, attack, defense, health, minDamage, maxDamage) {

    // количество попыткок повышения жизни
    var increaseHealthCount = 4
        private set

    companion object{

        private var singltone: Player? = null

        @Synchronized fun getInstancePlayer(
            name: String,
            attack: Int,
            defense: Int,
            health: Long,
            minDamage: Long,
            maxDamage: Long
        ): Player {
            if (singltone == null) {
                singltone = Player(name, TypeName.Player.typeName, attack, defense, health, minDamage, maxDamage)
            }
            return singltone!!
        }
    }

    init {
        if (super.alive) {
            println("${repeatSign("*")} Создан игрок с ником ${name}.")
        } else {
            println("${repeatSign("!")} Игрок с ником ${name} не был создан.")
        }
    }

    // повышение жизни, если игрок жив и если остались попытки повышения
    fun increaseHealth() {
        if (!super.alive) {
            println("${repeatSign("!")} Умерший или несуществущий игрок не может повышать свое здоровье.")
            return
        }
        if (increaseHealthCount < 1) {
            println("${repeatSign("!")} Игрок ${super.name} У Вас закончились попытки повысить здоровье.")
            return
        }
        --increaseHealthCount
        val generalHealth = Math.round(super.maxHealth * 0.3 + super.health)
        super.increaseHealth(generalHealth)
        println("${repeatSign("+")} Игрок ${super.name} применил повышение здоровья. \n${repeatSign("+")} Количество оставшихся попыток: ${increaseHealthCount}. Текущий уровень здоровья ${super.health}.")
    }
}
