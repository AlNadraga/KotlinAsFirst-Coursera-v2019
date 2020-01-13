@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
//fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> = TODO()
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val res = mutableMapOf<Int, MutableList<String>>()
    for (item in grades) {
        if (res.contains(item.value)) res[item.value]?.add(item.key)
        else res.put(item.value, mutableListOf(item.key))
    }

    return res.mapValues { it.value.toList() }
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
//fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean = TODO()
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    for (entry in a) {
        if (!b.containsKey(entry.key)) return false
        else if (a[entry.key] != b[entry.key]) return false
    }
    return true
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
//fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): Unit = TODO()
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>) {
    for (item in b) {
        if (a.get(item.key) != null && a[item.key] == b[item.key]) a.remove(item.key)
    }
}

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
//fun whoAreInBoth(a: List<String>, b: List<String>): List<String> = TODO()
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    val res = mutableListOf<String>()
    for (item in a) {
        if (b.contains(item) && !res.contains(item)) res.add(item)
    }
    return res
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
//fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> = TODO()
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val res = mutableMapOf<String, String>()

    for (item in mapA) {
        if (!res.contains(item.key)) res.put(item.key, item.value)
        else if (res[item.key] != mapA[item.key]) res[item.key] += ", " + item.value
    }

    for (item in mapB) {
        if (!res.contains(item.key)) res.put(item.key, item.value)
        else if (res[item.key] != mapB[item.key]) res[item.key] += ", " + item.value
    }

    return res.toMap()
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
//fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> = TODO()
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val res = mutableMapOf<String, Double>()

    for ((key, value) in stockPrices) {
        if (!res.contains(key)) res[key] = value
        else if (res[key] != value) res[key] = (res[key]!! + value)
    }

    for ((key, _) in res)
        res[key] = res[key]!! / stockPrices.count { it.first == key }

    return res.toMap()

}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
//fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? = TODO()
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var res: String? = null
    var minimal = -1.0
    for (item in stuff) {
        if (item.value.first == kind && (minimal < 0.0 || minimal > item.value.second)) {
            minimal = item.value.second
            res = item.key
        }
    }
    return res

}

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
//fun canBuildFrom(chars: List<Char>, word: String): Boolean = TODO()
//fun canBuildFrom(chars: List<Char>, word: String): Boolean {
//    for (i in word) {
//        if (!chars.contains(i)) return false
//    }
//    return true
//}

fun canBuildFrom(chars: List<Char>, word: String): Boolean = chars.containsAll(word.toList())

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val res = mutableMapOf<String, Int>()

    for (i in list) {
        if (list.count { it == i } > 1 && !res.contains(i))
            res[i] = list.count { it == i }
    }

    return res.toMap()
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    for (item in words) {
        for (i in words) {
            //Считаем, что в списке не присутствует двух одинаковых слов
            // например ("рот" "тор" "дверь" "тор" "кот") -> повторяется слово "тор"
            // то есть такого списка в данной реализации не ожидается
            //Также считаем, что пустые слова считаются анаграммами
            if(i.isEmpty() && item.isEmpty()) return true
            if (item.toList().containsAll(i.toList()) && !item.equals(i)) return true
        }
    }
    return false
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
//fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> = TODO()
//fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
//    val res = friends.mapValues { it.value.toMutableSet() }.toMutableMap()
//    for(item in friends){
//        for(i in item.value){
//            val tmp = if(friends[i] == null) continue else friends[i]
//            for(j in tmp!!){
//                if(!item.value.contains(j) && item.key != j ) res[item.key]?.add(j)
//            }
//        }
//    }
//
//
//    return res.mapValues { it.value.toSet() }
//}

fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    val res = mutableMapOf<String, MutableSet<String>>()
    for (item in friends) {
        res.put(item.key, mutableSetOf())
        println(item)
        for (i in item.value) {
            if (!res.contains(i)) res.put(i, mutableSetOf())
            val tmp = if (friends[i] == null) mutableSetOf() else friends[i]
            for (j in tmp!!) {
                if (!item.value.contains(j) && item.key != j) res[item.key]?.add(j)
            }
            res[item.key]?.add(i)
        }
    }

    return res.mapValues { it.value.toSet() }.toMap()
}


/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
//fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> = TODO()
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    for (i in list) {
        for (j in list) {
            if (i == j) continue
            if (i + j == number) return Pair(list.indexOf(i), list.indexOf(j))
        }
    }
    return Pair(-1, -1)
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> = TODO()
