import cube.CubePlatform
import parameters.Attack
import parameters.Damage
import parameters.Defense
import parameters.TypeName

abstract class Creature(name: String,
                        typeName: String,
                        attack: Int,
                        defense: Int,
                        health: Long,
                        minDamage: Long,
                        maxDamage: Long) {

    val REPEAT_SIGN: Int=50
    private val cubePlatform = CubePlatform()
    private lateinit var defense: Defense;
    private lateinit var damage: Damage;
    private lateinit var attack: Attack;

    // здоровье, может быть равно 0, только в том случае, если персонаж умирает
    // тогда проверка проходит alive==true (так как мы еще не обновили статус жизни) и здоровье равно 0
    // в случаях когда персонаж мертв, тогда принимаются значения больше 0
    var health: Long=0
        @Throws(Exception::class)
        private set(value) {
            if (value < MIN_HEALTH && !alive) {
                println("${repeatSign("!")} Значение параметра \"health\" должно быть указано как натуральное число от $MIN_HEALTH до N. Текущее значение: $value.")
                throw Exception("Ошибка присваивания параметра \"health\": значение должно быть натуральным числом")
            }
            field = value
        }

    // максимальное здоровье, которое приходит при первом создании персонажа
    var maxHealth:Long=0
        private set

    // имя существа
    var name: String="Unknown"
        @Throws(Exception::class)
        set(value){
            if (value.isEmpty()) {
                println("${repeatSign("!")} Введено некорректное имя, имя должно содержать хотя бы один знак.")
                throw Exception("Некорректное имя: имя не должно быть пустым")
            }
            field = value
        }

    // тип существа
    var typeName:String=""
        private set

    // статус жив/мертв (не существует)
    var alive = false
        private set

    // ряд констант связанных с защитой, атакой и здоровьем
    companion object {
        private const val MIN_HEALTH:Int = 1
    }

    init {
        this.typeName=typeName
        try {
            this.name=name
            this.attack= Attack(attack)
            this.defense= Defense(defense)
            this.health=health
            this.maxHealth=health
            this.damage= Damage(minDamage, maxDamage)
            alive=true
        } catch (e: Exception) {
            println("\u001b[41mОшибка создания ${typeName}а с именем $name : ${e.message} \u001b[0m")
        }
    }

    // повышение здоровья игроку
    fun increaseHealth(health: Long) {
        if (typeName == TypeName.Player.typeName) {
            if (health > maxHealth) {
                this.health = maxHealth
            } else {
                this.health = health
            }
        } else {
            println("${repeatSign("!")} Нельзя повышать уровень здоровья являясь монстром.")
        }
    }

    // потеря очков здоровья или смерть при удачном броске кубика
    private fun lossOfHitPoints(creatureThatAttacks: Creature) {
        val damage = creatureThatAttacks.damage.getRandomDamage()
        val restOfLife = health - damage
        if (restOfLife < 1) {
            health=0
            alive=false
            println("$typeName $name был атакован ${creatureThatAttacks.typeName}ом ${creatureThatAttacks.name}. Нанесенный урон: $damage. Текущее значение жизни: $health.")
            println("${repeatSign("F")} $typeName $name убит ${creatureThatAttacks.typeName}ом ${creatureThatAttacks.name}.")
        } else {
            try {
                health=restOfLife
            } catch (e: Exception) {
                println(e.message)
            }
            println("$typeName $name был атакован ${creatureThatAttacks.typeName}ом ${creatureThatAttacks.name}. Нанесенный урон: $damage. Текущее значение жизни: $health.")
        }
    }

    // атака направленная на какое-либо существо кроме себя самого
    fun attack(creatureThatIsAttacked: Creature) {
        if (!alive) {
            println("${repeatSign("!")} $typeName не может атаковать, так как он не создан или был убит.")
            return
        }
        if (!creatureThatIsAttacked.alive) {
            println("${repeatSign("!")} $typeName $name не может атаковать несуществующего ${creatureThatIsAttacked.typeName}а.")
            return
        }
        if (creatureThatIsAttacked === this) {
            println("${repeatSign("!")} $typeName $name не может атаковать сам себя.")
            return
        }
        println("\n${repeatSign("|")}АТАКА ${typeName.uppercase()}А${repeatSign("|")}")
        println("$typeName $name атакует ${creatureThatIsAttacked.typeName}а ${creatureThatIsAttacked.name}.")
        cubePlatform.countOfCube=attack.countAttackModifier(creatureThatIsAttacked.defense.defense)
        if (cubePlatform.throwAllCubes()) {
            creatureThatIsAttacked.lossOfHitPoints(this)
        }
        println("${repeatSign("|")}АТАКА ОКОНЧЕНА${repeatSign("|")} \n")
    }

    //полная информация о существе
    fun getInformationAboutCreature() {
        if(!alive){
            println("${repeatSign("!")} Вы не можете получить информацию по несозданному существу")
            return
        }

        println("\n${repeatSign("?")}${repeatSign("?")}")
        println("Информация об ${typeName}е под ником ${name}. ${name} ${ if (alive) "жив" else "мертв"}:\n\tЗащита=${defense.defense}, Минимальный урон=${damage.minDamage},Максимальный урон=${damage.maxDamage},\n\tАтака=${attack.attack}, Здоровье=$health, Максимально возможное здоровье=$maxHealth")
        println("${repeatSign("?")}${repeatSign("?")}\n")
    }

    fun repeatSign(sign:String):String{
        val colour=when(sign){
            "!"->"\u001b[33;1m"
            "F"->"\u001b[31;1m"
            "*"->"\u001b[32;1m"
            "#"->"\u001b[32m"
            "?"->"\u001b[34m"
            "|"-> {
                if(typeName==TypeName.Player.typeName){
                    "\u001b[45;1m"
                }else{
                    "\u001b[35;1m"
                }
            }
            "+"->"\u001b[42;1m"
            else -> ""
        }

        return "$colour${sign.repeat(REPEAT_SIGN)}\u001b[0m"
    }
}
