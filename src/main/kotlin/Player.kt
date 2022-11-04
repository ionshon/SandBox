class Player(_name : String,
             override var healthPoints: Int = 100,
             var isBlessed: Boolean = false,
             private var isImmotal: Boolean): Fightable {

    val name: String = _name
    val playerName: String = initPlayerName()
    fun castFireball(numFireballs: Int = 2) =
        println("한 덩어리의 파이어볼이 나타난다. (x$numFireballs)")

    var currentPosition = Coordinate(0, 0)
    private fun initPlayerName() = name

    override val diceCount = 3
    override val diceSides = 6

    override fun attack(opponent: Fightable): Int {
        val damageDealt = if (isBlessed) {
            damageRoll * 2
        } else {
            damageRoll
        }
        opponent.healthPoints -= damageDealt

        return damageRoll
    }
}