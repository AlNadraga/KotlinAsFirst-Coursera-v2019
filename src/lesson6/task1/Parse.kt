@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import java.lang.IllegalArgumentException

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
//fun dateStrToDigit(str: String): String = TODO()
fun dateStrToDigit(str: String): String {
    //Примерно минимальное число знаков 10 (1 мая 2000)
    if (str.length < 10)
        return ""
    val local_str = str.split(" ")
    if (local_str[0].toIntOrNull() == null || local_str[2].toIntOrNull() == null)
        return ""
    val res: String = String.format("%02d", local_str[0].toInt()) + "." +
            when (local_str[1].toLowerCase()) {
                "января" -> if (local_str[0].toInt() > 31) return "" else "01"
                "февраля" -> if (local_str[0].toInt() > daysInMonth(2, local_str[2].toInt())) return "" else "02"
                "марта" -> if (local_str[0].toInt() > 31) return "" else "03"
                "апреля" -> if (local_str[0].toInt() > 30) return "" else "04"
                "мая" -> if (local_str[0].toInt() > 31) return "" else "05"
                "июня" -> if (local_str[0].toInt() > 30) return "" else "06"
                "июля" -> if (local_str[0].toInt() > 31) return "" else "07"
                "августа" -> if (local_str[0].toInt() > 31) return "" else "08"
                "сентября" -> if (local_str[0].toInt() > 30) return "" else "09"
                "октября" -> if (local_str[0].toInt() > 31) return "" else "10"
                "ноября" -> if (local_str[0].toInt() > 30) return "" else "11"
                "декабря" -> if (local_str[0].toInt() > 31) return "" else "12"
                else -> return ""
            } + "." +
            local_str[2]

    return res
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
//fun dateDigitToStr(digital: String): String = TODO()
fun dateDigitToStr(digital: String): String {
    val str = digital.split(".")
    if (str[0].toIntOrNull() == null || str[1].toIntOrNull() == null || str[2].toIntOrNull() == null || str.size > 3)
        return ""
    val day = str[0].toInt()
    val month = when (str[1]) {
        "01" -> if (str[0].toInt() > daysInMonth(1, str[2].toInt())) return "" else "января"
        "02" -> if (str[0].toInt() > daysInMonth(2, str[2].toInt())) return "" else "февраля"
        "03" -> if (str[0].toInt() > daysInMonth(3, str[2].toInt())) return "" else "марта"
        "04" -> if (str[0].toInt() > daysInMonth(4, str[2].toInt())) return "" else "апреля"
        "05" -> if (str[0].toInt() > daysInMonth(5, str[2].toInt())) return "" else "мая"
        "06" -> if (str[0].toInt() > daysInMonth(6, str[2].toInt())) return "" else "июня"
        "07" -> if (str[0].toInt() > daysInMonth(7, str[2].toInt())) return "" else "июля"
        "08" -> if (str[0].toInt() > daysInMonth(8, str[2].toInt())) return "" else "августа"
        "09" -> if (str[0].toInt() > daysInMonth(9, str[2].toInt())) return "" else "сентября"
        "10" -> if (str[0].toInt() > daysInMonth(10, str[2].toInt())) return "" else "октябрня"
        "11" -> if (str[0].toInt() > daysInMonth(11, str[2].toInt())) return "" else "ноября"
        "12" -> if (str[0].toInt() > daysInMonth(12, str[2].toInt())) return "" else "декабря"
        else -> return ""
    }
    val year = str[2].toInt()

    return "$day $month $year"
}


/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    var res = ""
    val addList = listOf('+', '-', '(', ')', ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0')
    val bracket_index = phone.indexOf('(')
    if (bracket_index != -1 && phone[bracket_index + 1].toInt() !in 48..57) return ""
    val prefics = if (phone[0] == '+') "+" else ""
    for (i in phone) {
        if (!addList.contains(i)) return ""
        if (i.toInt() !in 48..57) continue
        res += i
    }
    return "$prefics$res"
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
//fun bestLongJump(jumps: String): Int = TODO()
fun bestLongJump(jumps: String): Int {
    val addList = listOf("-", "%")
    val str = jumps.split(" ")
    var max = 0
    for (item in str) {
        if (item.toIntOrNull() == null) {
            if (!addList.contains(item)) return -1
        } else if (max < item.toInt()) max = item.toInt()
    }
    return if (max == 0) -1 else max
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */

//Проверяем список попыток на успешные
//Если есть успешные попытки возвращаем 1
//Если нет то 0
//Если строка в неверном формате возвращаем -1
fun isSuccessfulTry(attempts: String): Int {
    val addList = listOf('+', '-', '%')
    for (item in attempts) {
        if (!addList.contains(item)) return -1
        if (item == '+') return 1
    }
    return 0
}

fun bestHighJump(jumps: String): Int {
    val jumpList = jumps.split(" ")
    //Проверяем на корректность. Если список попыток составлен правильно, то количество записей - четное
    //(высота + попытки) иначе возвращаем -1.
    if (jumpList.size % 2 != 0) return -1
    var res = 0
    for (i in 0 until jumpList.size step 2) {
        //Проверка корректности записи высоты
        if (jumpList[i].toIntOrNull() == null) return -1
        //Проверка корректности записи попыток
        if (isSuccessfulTry(jumpList[i + 1]) == -1) return -1
        //Если нет успешных то идем дальше, если есть проверяем ранние результаты и если больше то изменяем
        else if (isSuccessfulTry(jumpList[i + 1]) == 0) continue
        else if (res < jumpList[i].toInt()) res = jumpList[i].toInt()
    }

    return res
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */

//Проверяем число(true) это или знак(false)
//Если есть "неправильное" написание ("+2 " "+-" "++" и т.д.) кидаем исключение
fun isDigit(str: String): Boolean {
    if (str.length == 1) return str.toIntOrNull() != null
    val addList = listOf("+", "-")
    val res = mutableSetOf<Boolean>()

    for (i in 0 until str.length) {
        if (str[i].toString().toIntOrNull() != null)
            res.add(true)
        else if (str[i].toString().toIntOrNull() == null)
            res.add(false)
        else if (str[i].toString().toIntOrNull() == null && !addList.contains(str[i].toString()))
            throw IllegalArgumentException("Expression can only contain \"+\", \"-\" or number")
    }

    if (res.size == 2) throw IllegalArgumentException("Wrong format: nums and syms must be separated by \" \"")

    return res.contains(true)
}

// Проверяем всю строку на корректность
fun isCorrect(expresssion: String): Boolean {
    var prev = true
    val splited = expresssion.split(" ")
    if (isDigit(splited[0]) != prev) throw IllegalArgumentException("The first element must be a number")
    for (i in 1 until splited.size) {
        if (prev == isDigit(splited[i])) return false
        else prev = isDigit(splited[i])
    }
    return true
}

fun plusMinus(expression: String): Int {
    if (!isCorrect(expression)) throw IllegalArgumentException("Sames chars in row i.e. \"+ +\" \"2 2\" ")
    val list = expression.split(" ")
    var plmin = true
    var res = 0
    for (item in list) {
        if (item.toIntOrNull() == null) plmin = item == "+"
        else if (item.toIntOrNull() != null) res += item.toInt() * if (plmin) 1 else -1
    }
    return res
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */

fun firstDuplicateIndex(str: String): Int {
    val splited = str.toLowerCase().split(" ")
    var prev = splited[0]
    var ind = splited[0].length + 1
    for (i in 1 until splited.size) {
        if (splited[i] == prev) return ind - prev.length - 1

        prev = splited[i]
        ind += splited[i].length + 1
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
// Проверяем корректность
fun check(str: String): Boolean {
    if (str.isEmpty()) return false
    val splited = str.split(" ")

    if (splited.size % 2 != 0) return false

    for (i in 1 until splited.size step 2) {
        if (!splited[i].contains(";") && i != splited.size - 1) return false
    }

    return true
}

fun mostExpensive(description: String): String {
    if (!check(description)) return ""
    val list = description.split("; ")
    val map = mutableMapOf<String, Double>()
    var max = Pair("", 0.0)
    for (item in list) {
        val product = item.split(" ")
        map[product[0]] = product[1].toDouble()
        if (max.second <= map[product[0]]!!) max = max.copy(product[0], product[1].toDouble())
    }

    return max.first
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */

fun fromRoman(roman: String): Int {
    val numMap = mapOf(
        "M" to 1000,
        "CM" to 900,
        "D" to 500,
        "CD" to 400,
        "C" to 100,
        "XC" to 90,
        "L" to 50,
        "XL" to 40,
        "X" to 10,
        "IX" to 9,
        "V" to 5,
        "IV" to 4,
        "I" to 1
    )
    if (!roman.all { numMap.containsKey(it.toString()) }) return -1
    var res = 0
    var local_str = roman
    for (item in numMap) {
        while (local_str.indexOf(item.key) == 0) {
            res += item.value
            local_str = local_str.removePrefix(item.key)
        }
    }

    return res
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
//fun commandCheck(commands: String): Boolean {
//    val allowed_syms = listOf("+", "-", "<", ">", "[", "]")
//    var bracket_start = null
//    var bracket_end = null
//
//    for (i in 0 until commands.length) {
//        if (!allowed_syms.contains(commands[i].toString())) return false
//        else if (commands[i].toString().equals("["))
//    }
//    return true
//}
//
//fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
//    val local_cells = mutableListOf<Int>()
//    for (i in 0 until cells) local_cells.add(0)
//    var local_limit = limit
//
//
//    return listOf(1, 2, 3)
//}

