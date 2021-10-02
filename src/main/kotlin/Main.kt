//java does not support top level functions but kotlin does
fun main() {
    println("hello worlds")
    val read = "immutable"
    var write = "mutable"
    write = "mutated"
    println("$read and $write")
    val a: Int = 10 // Int, Float, Double, Long (whole)
    println(a)
//    println(true is Boolean)
//    println(false is Boolean)
    val letter: Char = 'A'
    println("$letter => ${letter.code}") // 65
    val str: String = "I am a string"
    println(str)
    val multiString = """
        hello
            ok
        """
    println(multiString)
    println("${1+1}")
    val intel = "do you know gods of death love apples"
//    println(intel.get(1))
    println(intel[0])
    println(intel.subSequence(1,3))
    println(intel.contains("apples"))

    // === => checks by reference
    val str2 = "hello"
    val str3 = str2
    println(str2 === str3)
    println("hello worlds".contains("hello"))
    println("ok".length)

//    arrays => fixed size
    var nums = arrayOf(1,2,3)
    nums[0] = 10
    println(nums)
    println(nums.size)
    nums += 12
    println(nums.toList())
    val arr = Array(5) { i -> i * i }
    println(arr.toList())

//    lists => var number of elements
    val fruits = mutableListOf<String>("apple", "mango")
    fruits.add("some fruit")
    fruits.removeAt(1)

    val immutableList = listOf<String>("pure", "fp")

//    sets => every elem unique
    val mySet = mutableSetOf<Char>('A','B','A')
    println(mySet)
    mySet.add('B')
    mySet.remove('A')
//    indexes wont work

//    hashmaps
    val map: HashMap<Int, String> = hashMapOf(1 to "a", 2 to "b", 3 to "c")
    println(map)
    println(map[2])
    map[1] = "ok"
    map.remove(2)
    map[3] = "new kv"

    println(max(3,4))

//    when -> can also be assigned to a variable just like if
    val myArray = arrayOf<Any>(1, 2.0, "hello", true)
    for (i in myArray){
        when(i) {
            is String -> println("$i is string")
            is Int -> println("$i is int")
            is Float -> println("$i is float")
            !is Boolean -> println("$i is not a boolean")
            else -> println("none matched")
        }
    }

    val qualifiedNums = arrayOf(1,2,3)

    for (i in nums) {
        when(i) {
            in 1..10 -> println("$i is between 1 to 10")
            in qualifiedNums -> println("$i is qualified")
            else -> println("idk")
        }
    }

    for (i in nums.indices){
        println(i)
        println(nums[i])
    }

    for ((value,i) in nums.withIndex()){
        println("$i -> $value")
    }

    val myIterator = nums.iterator()

    var count = 0
    while (myIterator.hasNext()){
        count++
        myIterator.next()
    }
    println("${nums.toList()} has $count elements")

    Continue@ for (i in 1..100) {
        for (j in 1..100) {
            if (j == 50) {
                println("$i and $j wins")
                break@Continue // breaks out from both the loops
//                break statements break out from the closest loop unless they hav a specific label
            }
        }
    }

    listOf<Int>(1,2,3,4,5).forEach lit@ {
        if(it == 3){
            println("returning")
            return@lit
        }
        println(it)
    }

    avg(2,5)
    greet()
    ok(message = "nah")
    println(sumUs())
    println(sumUs(1,2,3))
    println(sumUs(3,4,4,4,4,4,4,4,4))
    println(sumUs(*intArrayOf(1,2,3))) // type of array needs to be explicit => spread
    println(sumMulti(3,4))
    val (sumResponse, multiResponse) = sumMulti(4,5)
    println("$sumResponse, $multiResponse")

//    lambdas => anonymous, no fun keyword, no explicit return type (compile infers it itself)
    val message = {
        println("I am lambda")
    }
    message()
    val lambdaSum = {
        x: Int, y: Int -> x+y
    }
    println(lambdaSum(3,5))

    val myList = listOf<String>("hello", "worlds", "kotlin", "jvm")
    println(myList.last())
    println(myList.last{s -> s.length == 5}) // last string whose length is 5
    println(myList.last{it.length == 5}) // it is like the "this" keyword

//    anonymous functions
    myList.forEach(fun (n) {
        if(n == "hello"){
            println(n)
        }
    } )
    myList.forEach {
        if(it === "hello"){
            println(it)
        }
    }

    println("hello".customUpperCase())
    println(1.ad(1,2))
    println(2 sub 3) // infix
    println(multiplyAfterAdd(1,2, ::addFunc))
    println(factorial(5))
}

fun addFunc(a: Int, b: Int): Int = a + b

fun max(a: Int, b: Int): Int{
    val res = if (a > b){
        a
    }else{
        b
    }
    return res
}

fun avg(a:Int, b: Int):Int = (a+b)/2

//multi line functions cannot infer the return types
fun greet():Unit {
   println("hello")
}

//named parameters
fun ok(name: String = "none", message: String) = "hello $name $message"

// vararg param is internally represented by an array
fun sumUs(vararg nums: Int): Int {
    var sum = 0
    for (n in nums) {
        sum += n
    }
    return sum
}

//no tuples but Pair and Triple (but not above that)
fun sumMulti(a: Int, b: Int): Pair<Int, Int> {
   return Pair(a+b, a*b)
}

fun String.customUpperCase(): String {
    return this.uppercase()
}

fun Int.ad(a: Int, y: Int): Int = a + y + this

infix fun Int.sub(x: Int): Int = x - this

fun multiplyAfterAdd(a: Int, b: Int, addFunc: (Int, Int) -> Int): Int {
    return addFunc(a, b)
}

// recursion => tail recursion => compiler can rewrite recursive function in an imperative manner
fun factorial(num: Long, accum: Long = 1): Long {
    val soFar = num * accum
    return if (num <= 1) {
        soFar
    }else{
        factorial(num-1, soFar)
    }
}