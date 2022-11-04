import java.io.File

const val TAVERN_NAME = "Taenryl's Folly"

var playerGold =10
var playerSilver = 10
val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")

//val file = File("data/tavern-menu-items.txt")
val menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split("\r\n")


val uniquePatrons = mutableSetOf<String>()
val patronGold = mutableMapOf<String, Double>()

private fun <T> Iterable<T>.random(): T = this.shuffled().first()

fun main(args: Array<String>) {

    (0..9).forEach {
        val first = patronList.random()
        val last = lastName.random()
        val name = "$first $last"
//        uniquePatrons += name
    }

    uniquePatrons.forEach {
        println("it: $it")
        patronGold[it] = 6.0
    }


    var orderCount = 0

    while (orderCount <= 9) {
//        placeOrder(uniquePatrons.shuffled().first(), menuList.shuffled().first())
        orderCount++
    }
    /*patronList.forEachIndexed { index, patron ->
        println("좋은 바입니다. $patron 님 - 당신은 ${index +1} 번째입니다")
        placeOrder(patron, menuList.shuffled().first())
    }*/

    println("*** Welcome to $TAVERN_NAME ***")
    menuList.forEach { data ->
        val (type, name, price) = data.split(',')
        print("$name")
        for (i in 0 .. (32 - (name.length + price.length))) {
            print(".")
        }
        println(" $price")
    }

    uniquePatrons.forEach {
    }
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName 은 $tavernMaster 에게 주문한다. ==== $indexOfApostrophe")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName 은 금화 $price 로 $name ($type)를 구입한다"
    println(message)

    val phrase = if (name == "Dragon's Breath") {
        "$patronName 이 감탄한다: 와, $name 진짜 좋구나 \n"
    } else {
        "$patronName 이 말한다: 감사합니다 $type \n"
    }
    println(phrase)
}