package br.com.studyng.alugames.service;

import br.com.studyng.alugames.model.InfoGame

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import com.google.gson.Gson

public class ApiSharkService {
    fun searchGame(id: String): InfoGame {
        val urlAddress = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(urlAddress)) // https://apidocs.cheapshark.com/
            .build()

        val response = client
            .send(request, BodyHandlers.ofString())

        val json = response.body()
        val gson = Gson()

        val infoGame = gson.fromJson(json, InfoGame::class.java)
        return infoGame
    }
}
