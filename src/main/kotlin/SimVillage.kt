fun main(args: Array<String>) {
    /*runSimulation("손인수", ::printConstructionCost) { playerName, numBuildings ->
        val currentYear = 2022
        println("$numBuildings 채의 건물이 추가됨")
        "simvillage 방문을 환영합니다. $playerName 님! (copyright $currentYear)"
    }*/
    runSimulation() //"손인수", greetingFunction)
}

/*inline fun runSimulation(playerName: String,
                         costPrinter: (Int) -> Unit,
                         greetingFunction: (String, Int) -> String) {
    val numBuildings = (1..3).shuffled().last()
    costPrinter(numBuildings)
    println(greetingFunction(playerName, numBuildings))
}*/

fun runSimulation() {
    val greetingFunction = configureGreetingFunction()
    println(greetingFunction("손인수"))
    println(greetingFunction("손인수"))
}
fun printConstructionCost(numBuildings: Int) {
    val cost = 500
    println("건축비용: ${cost * numBuildings}")
}

fun configureGreetingFunction() : (String) -> String {
    val structureType = "병원"
    var numBuildings = 5
    return { playerName: String ->
        val currentYear = 2022
        numBuildings += 1
        println("$numBuildings 채의 $structureType 이 추가됨")
        "simvillage 방문을 환영합니다. $playerName 님! (copyright $currentYear)"}
}