import java.math.BigDecimal
import java.math.RoundingMode

fun aula2() {
    println("Funções de agregação")
    trabalhandoComRanges()
    println()
    outrasAbordagensComMetodosDeArray()
    println()
    utilizandoBigDecimal()
    println("------------------------------------------------------")
}

fun trabalhandoComRanges(){
    val serieDeNumeros = 1..10 // 1.rangeTo(10)
    println("Série de números: ${serieDeNumeros.joinToString()}")

    val numerosPares = 0..20 step 2 // 0.until(20) -> Não inclui o 20
    println("Números pares: ${numerosPares.joinToString()}")

    val numerosParesDesc = 20 downTo 0 step 2
    println("Números pares decrescentes: ${numerosParesDesc.joinToString()}")

    val intervaloDeSalarios = 1500.0..5000.0
    val salario = 4500.00
    println(verificaNoIntervalo(salario, intervaloDeSalarios))

    val intervaloAlfabeto = 'a'..'z'
    val letra = 'K'
    println(verificaNoIntervalo(letra, intervaloAlfabeto))
}

fun <T : Comparable<T>>verificaNoIntervalo(item: T, intervalo: ClosedRange<T>): String{
    return if (item in intervalo){
        "$item está dentro do intervalo $intervalo"
    }else{
        "$item não está dentro do intervalo $intervalo"
    }
}

fun outrasAbordagensComMetodosDeArray(){
    val idades: IntArray = intArrayOf(5, 25, 19, 33, 20, 10, 35, 17)
    val maiorIdade = idades.max()
    println("Maior idade: $maiorIdade")

    val menorIdade = idades.min()
    println("Menor idade: $menorIdade")

    val mediaIdades = idades.average()
    println("Média das idades: $mediaIdades")

    val todosMaioresDeIdade = idades.all { it >= 18 }
    println("São todos maiores de idade? $todosMaioresDeIdade")

    val algumMaiorDeIdade = idades.any { it >= 18 }
    println("Existe algum maior de idade? $algumMaiorDeIdade")

    val maioresDeIdade = idades.filter { it >= 18 }
    println("Maiores de idade: ${maioresDeIdade.joinToString()}")

    val buscaMaior = idades.find { it >= 18 }
    println("Primeiro maior de idade: $buscaMaior")
}

fun utilizandoBigDecimal(){
    val salarios = Array<BigDecimal>(5){ BigDecimal.ZERO }
    salarios[0] = "1500.30".toBigDecimal()
    salarios[1] = "12300.00".toBigDecimal()
    salarios[2] = "45000.0".toBigDecimal()
    salarios[3] = "4000.50".toBigDecimal()
    println(salarios.contentToString())

    val salariosToBigDecimal = bigDecimalArrayOf("1500.30", "12300.00", "45000.0", "4000.50")
    println(salariosToBigDecimal.contentToString())

    val aumento = "1.1".toBigDecimal()
    val salariosComAumento: Array<BigDecimal> = salariosToBigDecimal
        .map { salario ->
            if(salario < "5000".toBigDecimal()) {
                salario + "500".toBigDecimal()
            }
            else {
                (salario * aumento).setScale(2, RoundingMode.UP)
            }
        }
        .toTypedArray()
    println(salariosComAumento.contentToString())
}

fun bigDecimalArrayOf(vararg valores: String): Array<BigDecimal>{
    return Array<BigDecimal>(valores.size){ i -> valores[i].toBigDecimal() }
}

