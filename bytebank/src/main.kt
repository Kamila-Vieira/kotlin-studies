import kotlin.math.abs

fun main(){
    println("Bem vindo ao Bytebank")
    val contaAlex = Conta("Alex", 1000)
    val contaFran = Conta("Fran", 1001)
    println(contaAlex.toString())
    println(contaFran.toString())

    if(contaAlex.deposita(500.00)){
        println("\nDepósito bem sucedido:")
        println(contaAlex.toString())
        println(contaFran.toString())
    }

    if(contaAlex.saca(88.00)){
        println("\nSaque bem sucedido:")
        println(contaAlex.toString())
        println(contaFran.toString())
    }

    if(contaAlex.transfere(contaFran, 150.23)){
        println("\nTranferência bem sucedida:")
        println(contaAlex.toString())
        println(contaFran.toString())
    }

    // Convenções em Kotlin: https://kotlinlang.org/docs/coding-conventions.html

}

class Conta(
    var titular: String,
    val numero: Int
){
    var saldo = 0.0
        private set

    fun deposita(valor: Double): Boolean{
        if(valor > 0){
            saldo += valor
            return true
        }
        return false
    }

    fun saca(valor: Double): Boolean{
        if(saldo >= valor){
            saldo -= valor
            return true
        }
        return false
    }

    fun transfere(contaDestino: Conta, valor: Double): Boolean{
        if(saldo >= valor){
            saldo -= valor
            contaDestino.deposita(valor)
            return true
        }
        return false
    }

    override fun toString(): String {
        return "Conta(titular='$titular', numero=$numero, saldo=$saldo)"
    }
}

fun testaCopiasEReferencias(){
    // Exemplo de cópia (tipos primitivos)
    val numeroX = 10
    var numeroY = numeroX
    numeroY++
    println("numeroX: $numeroX")
    println("numeroY: $numeroY")

    // Exemplo de referência (Objetos)
    val contaJoao = Conta("João", 1000)
    val contaMaria = contaJoao
    contaMaria.titular = "Maria"
    println(contaJoao.toString())
    println(contaMaria.toString())
}

fun testaLoops(){
    //  for(i in 4 downTo -4 step 2)
    //  for(i in -4 until 4 step 2)
    for(i in -4..4 step 2){
        val titular: String = "Alex ${abs(i)}"
        val numeroConta: Int = 1010 + i
        var saldo = 0.0
        saldo = (100 + 2.0) * i
        saldo += 200

        println("titular $titular")
        println("número da conta $numeroConta")
        println("saldo da conta $saldo")
        testaCondicoes(saldo)
        println()
    }
}

fun testaCondicoes(saldo: Double){
//    if(saldo > 0.0) {
//        println("conta é positiva")
//    }else if(saldo == 0.0){
//        println("conta é neutra")
//    }else{
//        println("conta é negativa")
//    }
    when {
        saldo > 0.0 -> println("conta é positiva")
        saldo == 0.0 -> println("conta é neutra")
        else -> println("conta é negativa")
    }
}