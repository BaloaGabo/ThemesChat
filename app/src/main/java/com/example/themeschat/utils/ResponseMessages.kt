package com.example.themeschat.utils

import com.example.themeschat.models.MessageData


val listOfResponses = listOf<String>(
    "Hola",
    "Quel tal?",
    "Buen Dia",
    "Adios",
    "Que bien",
    "Que mal",
    "Un gusto",
    "No me digas",
    "No me gusta",
    "Eso me encanta",
)

fun automaticResponse(): MessageData{
    val randomResponse = listOfResponses.random()

    val response = MessageData(
        isMine = false,
        text = randomResponse
    )

    return response
}