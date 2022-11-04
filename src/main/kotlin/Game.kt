import java.lang.Exception
import java.lang.IllegalStateException
import kotlin.system.exitProcess

object Game {
    private val player = Player("Madrigal", isImmotal = false)
    private var currentRoom: Room = TownSquare()

    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corrider"), Room("Generic Room"))
    )

    private fun move(directionInput: String) =
        try {
            val direction = Direction.valueOf(directionInput.uppercase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            println("newPosition" + newPosition)
            println("currentPosition" + player.currentPosition)
            if (!newPosition.isInBounds) {
                throw IllegalStateException("$direction 쪽 방향이 범위를 벗어납니다")
            }

            println(newPosition.isInBounds)
            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "OK, $direction 방향의 ${newRoom.name}로 이동"
        } catch (e: Exception) {
            "잘못된 방향임: $directionInput"
        }

    init {
        println("방문을 환영합니다.")
        player.castFireball()
    }
    fun play() {
        while (true) {
            println("current${currentRoom.description()}")
            println(currentRoom.load())

            print("> 명령을 입력하세요: ")
//            println("최근 명령 : ${readLine()}")
            println(GameInput(readLine()).processCommand())
            println("\n********************\n")
        }
    }

    private class GameInput(args: String?) {
        private val input = args ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, {""})

        fun processCommand() = when (command.lowercase()) {
            "fight" -> fight()
            "move" -> move(argument)
            "quit" -> System.exit(0)
            "map" -> showMap()

            else -> println("\n" + commandNotFound())
        }

        private fun commandNotFound() = " 적합하지 않은 명령입니다!"

        private fun showMap() {
            println("${currentRoom.name}")
            when(currentRoom.name.lowercase()) {
                "town square" -> println("x o o\no o")
                "tavern" -> println("o x o\no o")
                "back room" -> println("o o x\no o")

                else -> println("에러")
            }
        }
    }

    private fun fight() = currentRoom.monster?.let {
        while (player.healthPoints > 0 && it.healthPoints > 0) {
            slay(it)
            Thread.sleep(1000)
        }
        "전투가 끝났으"
    } ?: "여기에는 싸울 괴물이 없습니다."

    private fun slay(monster: Monster) {
        println("${monster.name} -- ${monster.attack(player)} 손상을 입힘!")
        println("${player.name} -- ${player.attack(monster)} 손상을 입힘!")

        if (player.healthPoints <= 0) {
            println(">>>>> 당신은 졌습니다 게임을 종료합니다")
            exitProcess(0)
        }

        if (monster.healthPoints <= 0) {
            println(">>>> ${monster.name} -- 격퇴됨 <<<<")
            currentRoom.monster = null
        }
    }
}

enum class Direction(private val coordinate: Coordinate) {
    NORTH(Coordinate(0, -1)),
    EAST(Coordinate(1, 0)),
    SOUTH(Coordinate(0, 1)),
    WEST(Coordinate(-1, 0));

    fun updateCoordinate(playerCoordinate: Coordinate) =
//        Coordinate(playerCoordinate.x + coordinate.x, playerCoordinate.y + coordinate.y)
        coordinate + playerCoordinate // EAST + currentPosition
}

data class Coordinate(val x: Int, val y: Int) {
    val isInBounds = x >= 0 && y >= 0

    operator fun plus(other: Coordinate) = Coordinate(x + other.x, y + other.y)
}


