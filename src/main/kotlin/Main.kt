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
}
