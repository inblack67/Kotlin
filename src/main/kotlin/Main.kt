class Bob {}

class Traditional {
    var title: String
//    secondary ctor
    constructor(title: String){
        this.title = title
    }
}
// getters and setters are built in
class Modern (title: String = "Kotlin"){

}

//no constructors
//can extend other classes and interfaces
//singleton => only one instance => multiple calls => same instance will be given
object Constants {
    val URL: String = "https://inblack67.vercel.app"
    fun sayMyName() {
        println("ok")
    }
}

class Custom(var title: String) {
    var length: Int = 0

//        custom getters and setters
    set(value){
        if(value == 0){
            throw IllegalArgumentException("Length must not b empty")
        }
        field = value // field & value are reserved keywords
    }
    get(){
        return field.inc()
    }
}

class Article(var title: String, var length: Int) {
    var published: Boolean = true
    var author: String = "John Doe"
    constructor(title: String, length: Int, author: String, published: Boolean): this(title,length){
        this.author = author
        this.published = published
    }
}

// Any type => every class will inherit from it => and it has 3 methods => toString, equals, hashCode

class Visibility {
    fun a() {
        println("I am public")
    }
    private fun b(){
//        can be accessed from within the same file it was declared in
        println("I am private")
    }
    protected  fun c(){
//        accessible in the class or any subclass of that class
        println("I am protected")
    }
    internal fun d(){
//        limits the scope to the module itself
        println("I am internal")
    }
}

//no static classes methods or properties
class Person private constructor (name: String) {
//    lazy => instantiated only when needed for the first time
    companion object {
        fun create(name: String): Person = Person(name)
    }
}

class Peeps private constructor(var count: Int)  {
//    this block runs whenever Peeps object gets created
    init {
        count++
    }

//    singleton => even though class Peeps can be instantiated as many times
//    can't access class vars
    companion object {
        var count = 0
        init {
            //    this block runs whenever companion object gets created
            println("created object")
        }
    }
}

//class A {
//    private var b: B? = null
//    fun init0(b: B) {
//        this.b = b
//    }
//}

class A {
//    must be mutable (var), no primitive type and no custom getter and setters
    private lateinit var b: B // later initialized => so can avoid explicit null checks
    fun init0(b: B) {
        this.b = b
    }
}

class B {
    val String.customUpperCase: String
        get() = this.uppercase()
    val name: String = "jack".customUpperCase
}

data class User (
    var name: String,
    var email: String,
    var password: String,
)

class OuterClass {

    var name: String = "hello"

//    nested class => just like static nested class in java => by default
//    do not store ref to the outer class
//    can be private => can be instantiated within the scope of the outer class
   class InnerClass {
       fun inner() {
           println("I am inner")
       }
   }

    inner class referencedInner {
        fun anotherInner(){
            val outer = this@OuterClass
            println("I have ref to the outer class with the help of inner keyword ${outer.name}")
        }
    }
}

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

enum class Direction2 (val code: Int) {
    UP(1), DOWN(2), LEFT(3), RIGHT(4)
}


//sealed classes => abstract which can't create objects
sealed class Animal
class Tiger: Animal()
class Dog: Animal()
open class Wolf: Animal()

class Beast: Wolf()

fun animalType(a: Animal) {
    when(a){
        is Tiger -> println("tiger")
        is Dog -> println("dog")
        is Beast -> println("beast")
        else -> println("who are you?")
    }
}

abstract class Company(val companyName: String){
    abstract fun getEarnings(): Double
    fun companyName(): String = "$companyName inc." // this method can be overridden via concrete/subclasses only if it has the "open" modifier
}

abstract class Keys {
    val KEY_ONE = "KEY_ONE"
}

//class Employee(companyName: String) : Company(companyName) {
//
//}

//interfaces => naturally abstract
//do not maintain state
//can have default implementations
//property init not allowed
interface IntA {
    fun funA(a: Int): Boolean
    fun funB(b: Int): Boolean {
        return true
    }
}

//class can implement as many interfaces as desired but it can only inherit one class
class C : IntA {
    override fun funA(a: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun funB(b: Int): Boolean {
        TODO("Not yet implemented")
    }
}

//by default => classes and methods are final => can not be inherited

open class A1 {
    open fun a() {
        println("hello")
    }
}

class B1 : A1() {
    override fun a() {
        println("overidden hello")
    }
}

open class Games(val name: String) {
    open fun intro (): String = name
}

class Football(name: String): Games(name) {
    override fun intro(): String {
        return super.intro()
    }
}

class Boxing : Games {
    constructor(name: String) : super(name){

    }
}

//type alias

typealias Name = String // alias for these types
typealias Age = Int

data class NewPerson(val name: Name, val age: Age)


class MyStack<S>(vararg  items: S) {
    private val elements = items.toMutableList()
    fun push(element: S) = elements.add(element)
    fun pop(): S = elements.removeAt(elements.size - 1)
    fun peek(): S = elements.last()
    fun isEmpty() = elements.isEmpty()
    fun size() = elements.size
    override fun toString(): String {
        return "MyStack => ${elements.joinToString()}"
    }
}

fun <S> mutableStackOf(vararg elements: S) = MyStack(*elements) // * => spreading

//java does not support top level functions but kotlin does
fun main() {

    val stack1 = MyStack<Int>()
    stack1.push(1)
    stack1.push(2)
    stack1.push(3)
    stack1.push(4)
    println(stack1.toString())


    val stack2 = MyStack<Boolean>()
    stack2.push(true)
    stack2.push(false)
    stack2.push(true)
    stack2.push(false)
    println(stack2.toString())

    val stack3 = mutableStackOf<Int>(1,2,3)
    println("stack3 => $stack3")

    val up: Direction = Direction.UP
//    or
    enumValueOf<Direction>("UP")
    enumValues<Direction>()
    Direction.values()

    val downCode = Direction2.DOWN
    println(downCode.code)

    val inner = OuterClass.InnerClass()
    inner.inner()

    val user = User(name = "some name", email = "some@mail.com", password = "123")
    user.component1() // first field => name
    val (_, email, password) = user
    println("$email and $password")
    println("${user.name}, ${user.email}, ${user.password}")
    println(user.toString())
    println(user.hashCode()) // can be used in storing and retrieving via hashmaps
    val userCopy = user.copy()
    val userCopy2 = user.copy(name = "modified name")
    println(userCopy.toString())

    val b = B()
    println(b.name)

    val person = Person.create("Some Name") // cant access the constructor as it is private

    val article = Article(title = "kotlin Article", length = 10, author = "me", published = false)
    println("${article.author} ${article.published} ${article.title} ${article.length}")

    val custom = Custom(title = "hello")
    custom.length = 50
    println("custom length => ${custom.length}") // 51 => as getter was invoked right here

    val bob = Bob()

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

    val name: String? = null
    println(name?.length) // null or Int
//    println(name!!.length) // bypass nullability check
    val pureName = name ?: "Null Name" // if name is null, RHS is assigned to pureName var
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