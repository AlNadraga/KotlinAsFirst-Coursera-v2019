@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var res = 0
    var tmp = n
    do {
        ++res
        tmp /= 10
    } while (tmp > 0)
    return res
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int =
    if (n == 1 || n == 2) 1
    else fib(n - 2) + fib(n - 1)


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
// Наибольший общий делитель (НОД), нужен для нахождения наименьшего общего кратного (НОК)
fun nod(max: Int, min: Int): Int = if (min == 0) max else nod(min, max % min)

fun lcm(m: Int, n: Int): Int = abs(m * n) / nod(max(m, n), min(m, n))

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var counter = 2
    while (true) {
        if (n % counter == 0) break
        else ++counter
    }
    return counter
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n - 1 downTo 1) {
        if (n % i == 0) return i
        else continue
    }
    return -1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = if (nod(max(m, n), min(n, m)) == 1) true else false

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */

fun squareBetweenExists(m: Int, n: Int): Boolean {
    val range = ceil(sqrt(m.toDouble())).toInt()..round(sqrt(n.toDouble())).toInt()
    for (i in range) {
        if (i * i in m..n) return true
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var counter = 0
    var next: Int = x
    while (next != 1) {
        if (next % 2 == 0)
            next /= 2
        else
            next = next * 3 + 1
        ++counter
    }
    return counter
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var tmp:Double = x
    var res = 0.0
    var scaler = 1
    var flag = true
    var xx = tmp
    while (xx > 2 * PI)
        xx -= 2 * PI
    while (abs(tmp) > eps) {
        tmp = (xx.pow(scaler) / factorial(scaler))
        res += tmp * if (flag) 1 else -1
        scaler += 2
        flag = !flag
    }
    return res
}



/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var tmp:Double = x
    var res = 0.0
    var scaler = 0
    var flag = true
    var xx = tmp
    while (xx >2*PI)
        xx -= 2 * PI
    do  {
        tmp = (xx.pow(scaler) / factorial(scaler))
        res += tmp * if (flag) 1 else -1
        scaler += 2
        flag = !flag
    }while (abs(tmp) > eps)
    return res
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var res = 0
    var local_n = n

    while(local_n > 0){
        res = res * 10 + local_n % 10
        local_n /= 10
    }
    return res
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var local_n = n
    var first:Int
    var last:Int
    val ten = 10.0
    for(i in 1..digitNumber(n)/2){
        first = local_n/ten.pow(digitNumber(local_n)-1).toInt()
        last = local_n % 10
        println("loc_N = $local_n  first = $first  last = $last")
        if(first!=last) return false
        local_n /= 10
        local_n %= ten.pow(digitNumber(local_n)-1).toInt()
    }
    return true
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    if(digitNumber(n) == 1) return false
    val digit = n / (10.toDouble().pow (digitNumber(n) - 1)).toInt()
    for(i in 1..digitNumber(n)){
        if(digit != (n/(10.toDouble().pow((i-1).toDouble())) % 10.0).toInt()){
            return true
        }

    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var local_n = n
    var sqr_res:Int
    var iteration = 1
    while(local_n > 0){
        sqr_res = sqr(iteration)
        local_n -= digitNumber(sqr_res)
        if(local_n == 0) return sqr_res % 10
        else if(local_n < 0) return (sqr_res/10.toDouble().pow(abs(local_n)) % 10).toInt()
        iteration++
    }
    return -1
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var local_n = n
    var fib_res:Int
    var iteration = 1
    while(local_n > 0){
        fib_res = fib(iteration)
        local_n -= digitNumber(fib_res)
        if(local_n == 0) return fib_res % 10
        else if(local_n < 0) return (fib_res/10.toDouble().pow(abs(local_n)) % 10).toInt()
        iteration++
    }
    return -1
}

