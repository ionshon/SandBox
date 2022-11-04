fun main(args: Array<String>) {
    var name = "Madrigal"
    var healthPoints = 89
    val isBleed = true
    val isMortal = false

    val player = Player("Foyer", isImmotal = false)
    player.castFireball(4)

    val numLetters = "Mississippi".count({ letter -> letter == 's' })
//    print(numLetters)

    val sword = Sword("Excaliver")
    println(sword.name)
    sword.name = "Gleipnir"
    println(sword.name)

}

class Sword(_name: String) {
    var name = _name
        get() = "The Legendary $field"
        set(value) {
            field = value.lowercase().reversed().replaceFirstChar { it.uppercase()  }
        }

    init {
        name = _name
    }
}