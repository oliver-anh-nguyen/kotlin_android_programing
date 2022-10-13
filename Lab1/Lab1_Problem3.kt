fun main(args: Array<String>) {
    printFirstLast(9)
    println("---------")
    var sum = sumOddSquared(arrayOf(1, 2, 3, 4, 6, 5))
    println("3b. sum odd squared in array: {1, 2, 3, 4, 6, 5} is $sum")
    println("---------")
    println("3c. choose planet: 1: venus, 2: mars, 3: jupiter, 4: saturn, 5: uranus")
    var enterChoice = Integer.valueOf(readLine())
    var w = calculateWeight(null, enterChoice)
    println("your weight is: $w")
}

fun printFirstLast(number:Int) {
    println("3a. given number: $number")
    if (number < 10) {
        println("first and last digit: $number")
        return
    }
    var last = number % 10
    println("last digit: $last")
    var first = number / 10
    while (first >= 10) {
        first /= 10
    }
    println("first digit: $first")
}

fun sumOddSquared(arr: Array<Int>) : Int {
    var sum = 0
    for (number in arr) {
        if (number % 2 == 1) {
            sum += (number*number)
        }
    }
    return sum
}

fun calculateWeight(weight: Int?, planet: Int = 0): Number {
    weight?:return 0
    var w =  when(planet) {
        1 -> 0.78 * weight
        2 -> 0.39 * weight
        3 -> 2.65 * weight
        4 -> 1.17 * weight
        5 -> 1.05 * weight
        else -> weight
    }
    return w
}
