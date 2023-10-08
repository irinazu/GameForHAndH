fun main(args: Array<String>) {

    val player =Player.getInstancePlayer("Joy", 10, 15, 40, 5, 25)
    player.getInformationAboutCreature()

    val jeff=Monster("JeffMonster",25,2,100,2,15)
    jeff.getInformationAboutCreature()

    val kolin = Monster("KolinMonster", 20, 10, 8, 1, 7)
    kolin.getInformationAboutCreature()

    //атака игрока
    player.attack(jeff)
    //атака монстром игрока
    kolin.attack(player)
    //атака другим монстром игрока
    jeff.attack(player)
    //атака монстра другим монстром
    jeff.attack(kolin)
    player.attack(jeff)
    jeff.attack(kolin)
    jeff.attack(kolin)
    //повышение здоровья игрока
    player.increaseHealth()

    jeff.attack(player)
    player.increaseHealth()
    jeff.attack(player)
    player.increaseHealth()
    jeff.attack(player)
    player.increaseHealth()
    jeff.attack(player)
    player.increaseHealth()
    jeff.attack(player)
    jeff.attack(player)
    jeff.attack(player)
    jeff.attack(player)
    jeff.attack(player)
    jeff.attack(player)
    jeff.attack(player)

}