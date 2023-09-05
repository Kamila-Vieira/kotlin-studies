package br.com.studyng.alugames.main

import java.util.Scanner

import br.com.studyng.alugames.model.Game
import br.com.studyng.alugames.model.Gamer
import br.com.studyng.alugames.service.ApiSharkService
import br.com.studyng.alugames.utils.transformInAge


fun main() {
    val reader = Scanner(System.`in`)
    var game: Game? = null
    val api = ApiSharkService()

    val gamer = Gamer.createGame(reader)
    println("Cadastro concluído com sucesso.\nDados do gamer:")
    println("Idade do gamer: ${gamer.birthdate?.transformInAge()}")
    println(gamer)

    do {
        println("Digite um código de jogo para buscar: ")
        val code = reader.nextLine()

        var getGame = runCatching {
            val infoGame = api.searchGame(code)
            game = Game(infoGame.info.title, infoGame.info.cover)
        }

        getGame.onFailure {
            println("Jogo inexistente. Busque outro código")
        }

        getGame.onSuccess {
            println("Deseja inserir uma descrição personalizada? S/N")
            val option = reader.nextLine()

            if (option.equals("s", true)) {
                println("Insira a descrição personalizada para o jogo:")
                val description = reader.nextLine();
                game?.description = description
            }else{
                game?.description = game?.title
            }

            gamer.fetchedGames.add(game)
        }

        println("Deseja buscar um novo jogo? S/N")
        val response = reader.nextLine()

    }while (response.equals("s", true))
    println("Busca finalizada com sucesso!")

    println("\nJogos ordenados por título:")
    gamer.fetchedGames.sortBy {
        it?.title
    }

    gamer.fetchedGames.forEach {
        println("Título: ${it?.title}")
    }

    println("Deseja buscar algum jogo da lista original? S/N")
    val searchOption = reader.nextLine()
    if (searchOption.equals("s", true)) {
        println("Busque o jogo pelo título: ")
        val gameTitle = reader.nextLine()
        val filteredGames = gamer.fetchedGames.filter {
            it?.title?.contains(gameTitle, true) ?: false
        }
        filteredGames.forEach {
            println("Título: ${it?.title}")
        }
    }

    println("Deseja excluir algum jogo da lista original? S/N")
    val deleteOpcao = reader.nextLine()

    if (deleteOpcao.equals("s", true)) {
        println("Informe a posição do jogo que deseja excluir: ")
        val position = reader.nextInt()
        gamer.fetchedGames.removeAt(position)
        println("Lista Atualizada")
        gamer.fetchedGames.forEach {
            println("Título: ${it?.title}")
        }
    }
}
