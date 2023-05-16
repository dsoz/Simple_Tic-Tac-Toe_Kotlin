package tictactoe
private var gameList: MutableList<MutableList<Char>> = mutableListOf()
var endGame = false
var isXPlaer = true

fun main() {
    val a = "_________"

    gameList = mutableListOf(
        mutableListOf(a[0], a[1], a[2]),
        mutableListOf(a[3], a[4], a[5]),
        mutableListOf(a[6], a[7], a[8]),
    )

    printGameField()
    while (!endGame){
        checkUserInput()
        printGameField()
        checkStatus()
    }
}

fun checkUserInput(){
    var isTrue = false
    val coordinates = mutableListOf<Char>('_', '_')
    val playerChar = if (isXPlaer) 'X' else 'O'

    do {
        print("Input coordinates: ")
        val str = readln().split(" ")

        coordinates[0] = (str[0].toCharArray()[0])
        coordinates[1] = (str[1].toCharArray()[0])

        if (!coordinates[0].isDigit() || !coordinates[1].isDigit()){
            println("You should enter numbers!")
        } else if (coordinates[0].digitToInt() !in 1..3 || coordinates[1].digitToInt() !in 1 .. 3){
            println("Coordinates should be from 1 to 3!")
        } else if (gameList[coordinates[0].digitToInt() - 1][coordinates[1].digitToInt() - 1] != '_'){
            println("This cell is occupied! Choose another one!")
        } else{
            isTrue = true
        }
    } while (!isTrue)

    gameList[coordinates[0].digitToInt() - 1][coordinates[1].digitToInt() - 1] = playerChar
    isXPlaer = !isXPlaer

}

fun printGameField(){
    println("---------")
    println("| ${gameList[0][0]} ${gameList[0][1]} ${gameList[0][2]} |")
    println("| ${gameList[1][0]} ${gameList[1][1]} ${gameList[1][2]} |")
    println("| ${gameList[2][0]} ${gameList[2][1]} ${gameList[2][2]} |")
    println("---------")
}

fun isWinLine(): Boolean{
    val winCharsList = mutableListOf('X', 'O')
    var result = false
    var winChar = '+'
    var cntWinRow = 0
    var cntX = 0
    var cntO = 0

    for (i in 0..2){
        for (k in 0..2){
            if (gameList[i][k] == 'O')
                cntO++
            if (gameList[i][k] == 'X')
                cntX++
        }
        for (j in winCharsList){
            if (gameList[0][i] == j && gameList[1][i] == j && gameList[2][i] == j){
                result = true
                winChar = j
                cntWinRow++
            }
            if (gameList[i][0] == j && gameList[i][1] == j && gameList[i][2] == j){
                result = true
                winChar = j
                cntWinRow++
            }
            if (gameList[0][0] == j && gameList[1][1] == j && gameList[2][2] == j){
                result = true
                winChar = j
            }
            if (gameList[0][2] == j && gameList[1][1] == j && gameList[2][0] == j){
                result = true
                winChar = j
            }
        }
    }
    if (cntWinRow > 1 || (cntO - cntX) !in -1..1){
        println("Impossible")
        return true
    }
    if (result){
        println("$winChar wins")
        endGame = true
    }
    return result
}

fun isEmptyCells(): Boolean{
    var result = false

    for (i in gameList){
        if (i.contains('_'))
            result = true
    }
    return result
}

fun checkStatus(){
    if (!isWinLine()){
        if (isEmptyCells())
            println("Game not finished")
        else {
            println("Draw")
            endGame = true
        }
    }
}
