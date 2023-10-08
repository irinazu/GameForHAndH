import parameters.TypeName

class Monster(name:String, attack:Int, defense:Int, health:Long, minDamage:Long, maxDamage:Long)
    :Creature(name, TypeName.Monster.typeName,attack,defense,health,minDamage,maxDamage) {

    init{
        if (super.alive) {
            println("${repeatSign("#")} Создан монстр с ником ${name}.")
        } else {
            println("${repeatSign("!")} Монстр с ником ${name} не был создан.")
        }
    }
}
