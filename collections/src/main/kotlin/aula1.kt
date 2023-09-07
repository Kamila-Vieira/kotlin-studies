
fun aula1(){
    println("Operações com arrays")
    loopForEForEach()
    operacoesDentroDeIteracoes()
    println("\n------------------------------------------------------\n")
}

fun loopForEForEach(){
    val idades: IntArray = intArrayOf(25, 19, 33, 20) // IntArray(4)

    var maiorIdade = Int.MIN_VALUE
    for (idade in idades){
        if(idade > maiorIdade){
            maiorIdade = idade
        }
    }

    var menorIdade = Int.MAX_VALUE
    idades.forEach {idade ->
        if(idade < menorIdade){
            menorIdade = idade
        }
    }

    println("Maior idade: $maiorIdade")
    println("Menor idade: $menorIdade")
}

fun operacoesDentroDeIteracoes(){
    val aumento = 1.1
    val salarios: DoubleArray = doubleArrayOf(1500.30, 12300.00, 45000.0, 4000.50) // DoubleArray(4)
    for((indice, salario) in salarios.withIndex()){
        salarios[indice] = salario * aumento
    }
    println("Salários com o aumento de $aumento%: ${salarios.contentToString()}")

    for(indice in salarios.indices){
        salarios[indice] = salarios[indice] * aumento
    }
    println("Salários com o aumento de $aumento%: ${salarios.contentToString()}")

    salarios.forEachIndexed{ indice, salario ->
        salarios[indice] = salario * aumento
    }
    println("Salários com o aumento de $aumento%: ${salarios.contentToString()}")
}