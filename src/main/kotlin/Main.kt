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
}
