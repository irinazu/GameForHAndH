package parameters

public class Damage(minDamage:Long, maxDamage: Long) {
    // диапазон урона
    var minDamage:Long=0
        private set
    var maxDamage:Long=0
        private set

    init {
        setSegmentDamage(minDamage,maxDamage)
    }
    // установка диапазона урона
    @Throws(Exception::class)
    private fun setSegmentDamage(minDamage: Long, maxDamage: Long) {
        if (minDamage < 1 || maxDamage < 1) {
            println("Значение параметров \"minDamage\" и \"maxDamage\" должны быть указаны как натуральные числа. Текущие значения: $minDamage и $maxDamage")
            throw Exception("Ошибка присваивания параметров \"minDamage\" и \"maxDamage\": ненатуральные числа")
        }
        if (minDamage >= maxDamage) {
            println("Значение параметра \"minDamage\" должно быть больше значения параметра \"maxDamage\". Текущие значения: $minDamage и $maxDamage")
            throw Exception("Ошибка присваивания параметров \"minDamage\" и \"maxDamage\": \"minDamage\" больше \"maxDamage\"")
        }
        this.minDamage = minDamage
        this.maxDamage = maxDamage
    }

    // возврат рандомного урона из диапазона миниммального и максимального
    fun getRandomDamage(): Long {
        return (Math.random() * (maxDamage - minDamage) + minDamage).toLong()
    }
}
