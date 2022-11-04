import java.lang.Exception
import java.lang.IllegalStateException
import java.lang.System.exit

fun main() {

    val townSquare = TownSquare()
    val abandonedTownSquare = object : Room("버려진스붸어") {
        override fun load() = "환영받을 것을 예상했지만 여기는 아무도 없군요"
    }
    println(abandonedTownSquare.load())
    /*
        println("\r\n")
        println(townSquare.description())
        println(townSquare.load())

        var className = when (townSquare) {
            is Room -> "Room"
            is TownSquare -> "TownSquare"
            else -> throw IllegalArgumentException()
        }
        print(className)*/

    Game.play()
}

open class Room(val name: String) {
    protected open val dangerLevel = 5
    var monster: Monster? = Goblin()

    fun description() = "Room: $name\r\n" +
            "위험 수준: $dangerLevel\r\n" +
            "Creature: ${monster?.description ?: "none."}"

    open fun load() = "아무도 여기에 오지 않았습니다..."
}

open class TownSquare: Room("Town Square") {
    override val dangerLevel: Int
        get() = super.dangerLevel - 3
    private var bellSound = "댕댕"

    final override fun load() = "당신의 참여를 주민들이 다 함께 환영합니다\r\n${ringBell()}"
    private fun ringBell() = "당신의 도착을 종탑에서 알립니다. $bellSound"
}