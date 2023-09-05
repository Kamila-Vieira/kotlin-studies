package br.com.studyng.alugames.model

import java.util.Scanner
import kotlin.random.Random

data class Gamer(var name: String, var email: String) {
    var birthdate: String? = null
    var nickname: String? = null
        set(value) {
            field = validateNickname(value)
            if(internalId.isNullOrBlank()){
                generateInternalId()
            }
        }
    var internalId: String? = null
        private set
    var fetchedGames = mutableListOf<Game?>()

    constructor(name: String, email: String, birthdate: String, nickname: String) :
            this(name, email){
                this.birthdate = birthdate
                this.nickname = nickname
            }

    init {
        this.name = validateName()
        this.email = validateEmail()
    }

    override fun toString(): String {
        return "Gamer(name='$name', email='$email', birthdate=$birthdate, nickname=$nickname, internalId=$internalId, fetchedGames=$fetchedGames)"
    }

    private fun generateInternalId(){
        val randomNumber = Random.nextInt(10000)
        val tag = String.format("%04d", randomNumber)
        internalId = "$nickname#$tag"
    }

    private fun validateName(): String{
        if (name.isNullOrBlank()){
            throw IllegalArgumentException("O nome não pode ser nulo ou estar em branco")
        }else{
            return name
        }
    }

    private fun validateEmail(): String{
        val pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
        val regex = Regex(pattern)
        if (regex.matches(email)){
            return email
        }else{
            throw IllegalArgumentException("O formato e-mail é inválido, deve seguir o pattern: $pattern")
        }
    }

    private fun validateNickname(nickname: String?): String {
        val pattern = "^(?![_\\.])[\\w\\.]{3,16}$"
        val regex = Regex(pattern)
        if (!nickname.isNullOrBlank() && regex.matches(nickname!!)){
            return nickname
        }else{
            throw IllegalArgumentException("O formato usuário é inválido, deve seguir o pattern: $pattern")
        }
    }

    companion object{
        fun createGame(reader: Scanner): Gamer{
            print("Boas vindas ao AluGames! Vamos fazer seu cadastro!!\nDigite seu nome: ")
            val name = reader.nextLine()
            print("Digite seu e-mail: ")
            val email = reader.nextLine()
            print("Deseja completar seu cadastro com usuário e data de nascimento? (S/N) ")
            val option = reader.nextLine()

            if(option.equals("s", true)){
                print("Então vamos continuar!!\nDigite seu nome de usuário: ")
                val nickname = reader.nextLine()
                print("Digite sua data de nascimento (DD/MM/AAAA): ")
                val birthdate = reader.nextLine()
                return Gamer(name, email, birthdate, nickname)
            }

            return Gamer(name, email)
        }
    }
}
