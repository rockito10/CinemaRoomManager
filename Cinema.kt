package cinema

import kotlin.system.exitProcess

fun main() {

    var pastchosen = mutableListOf<MutableList<Int>>()

    var totalprices = 0

    println("Enter the number of rows:")

    val rows = readln().toInt()

    println("Enter the number of seats in each row:")

    val seats = readln().toInt()

    var fullrow = mutableListOf<String>()

    var fullrowS = mutableListOf<String>()

    for (num in 1..seats) {


        if (num >= 0 && num < 10) {
            fullrow.add("$num" + " ")
        } else
        {
            fullrow.add("$num")
        }

//        fullrow.add("$num")
    }

    for (num in 1..seats) {
        fullrowS.add("S ")
    }


    var mutList2D = mutableListOf(fullrow)

    for (num in 1..rows) {
        mutList2D.add(fullrowS)
    }

    menu(totalprices, pastchosen, rows, seats, mutList2D, fullrowS, fullrow)

}

fun menu(totalprices:Int, pastchosen:List<MutableList<Int>>, rows:Int, seats:Int, mutList2D: MutableList<MutableList<String>>, fullrowS: MutableList<String>, fullrow:MutableList<String>) {
    println("")
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
    when(readln().toInt()) {
        1 -> showseats(totalprices, pastchosen, rows, seats, mutList2D, fullrowS, fullrow)
        2 -> buy(totalprices, pastchosen, rows, seats, mutList2D, fullrowS, fullrow)
        3 -> statistics(totalprices, pastchosen, rows, seats, mutList2D, fullrowS, fullrow)
        0 -> return
    }
}

fun statistics(totalprices:Int, pastchosen:List<MutableList<Int>>, rows:Int, seats:Int, mutList2D: MutableList<MutableList<String>>, fullrowS: MutableList<String>, fullrow:MutableList<String>) {
    println("")
    var alreadybought = pastchosen.count()
    println("Number of purchased tickets: ${alreadybought}")
    var percentage = (alreadybought.toDouble() / (rows * seats).toDouble())*100
    var formatPercentage = "%.2f".format(percentage.toDouble())
    println("Percentage: ${formatPercentage}%")
    println("Current income: $${totalprices}")
    print("Total income: $")
    var totalseats = rows*seats

        if (rows*seats < 60) {
             print(10*totalseats)
        } else {
             print("${
                 10*(totalseats/2).toInt() + 8*((totalseats/2.toInt()))
             }")
        }
    println("")
    menu(totalprices, pastchosen, rows, seats, mutList2D, fullrowS, fullrow)

}


fun showseats (totalprices:Int, pastchosen:List<MutableList<Int>>, rows:Int, seats:Int, mutList2D: MutableList<MutableList<String>>, fullrowS: MutableList<String>, fullrow:MutableList<String>) {
    println("")
    println("Cinema:")

    println("  ${fullrow.joinToString().filter { it !in "," }}")
    for (num in 1..rows) {
        println("${num} " + mutList2D[num].joinToString().filter { it !in "," })
    }
    menu(totalprices, pastchosen, rows, seats, mutList2D, fullrowS, fullrow)
}

fun buy(totalprices:Int, pastchosen:List<MutableList<Int>>, rows:Int, seats:Int, mutList2D: MutableList<MutableList<String>>, fullrowS: MutableList<String>, fullrow:MutableList<String>) {
    println("")
    println("Enter a row number:")

     //

    var rownumb = readln().toInt()

    println("Enter a seat number in that row:")

    var seatnumb = readln().toInt()

    var chosen = mutableListOf<Int>(rownumb, seatnumb)

    if (chosen in pastchosen) {
        println("")
        println("That ticket has already been purchased!")
        buy(totalprices, pastchosen, rows, seats, mutList2D, fullrowS, fullrow)
    }

    var ticketprice:Int = 0

    try {

        var totalseats = rows * seats

        if (totalseats < 60) {
            ticketprice = 10
        } else if (rownumb > rows / 2) {
            ticketprice = 8
        } else {
            ticketprice = 10
        }

        var fullrowwithb = mutableListOf<String>()

        fullrowwithb.addAll(fullrowS)


        fullrowwithb.removeAt(seatnumb-1)


        fullrowwithb.add(seatnumb - 1, "B")

        mutList2D.removeAt(rownumb)

        mutList2D.add(rownumb, fullrowwithb)

        var newpast:List<MutableList<Int>> = mutableListOf<MutableList<Int>>(chosen) + pastchosen

        var pricestogo = totalprices + ticketprice

        println("Ticket price: $$ticketprice")

        menu(pricestogo, newpast, rows, seats, mutList2D, fullrowS, fullrow)

    }

    catch (e: IndexOutOfBoundsException) {
        println("")
        println("Wrong input!")
        buy(totalprices, pastchosen, rows, seats, mutList2D, fullrowS, fullrow)
    }


    /*
    println("Cinema:")
    println("  ${fullrow.joinToString().filter { it !in "," }}")
    for (num in 1..rows) {
        println("${num} " + mutList2D[num].joinToString().filter { it !in "," })
    }

     */
}

fun exit() {
    exitProcess(0)
}