package com.example.themeschat.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.themeschat.models.MessageData

@Composable
fun MessageBox (modifier: Modifier,
                listOfMessageData: List<MessageData>,
                scrollState: ScrollState
){
    Column  (
        modifier = modifier.fillMaxWidth().verticalScroll(scrollState)

    ){

        listOfMessageData.forEach{

            //Mensajes
            MessageCard (message = it)

        }

    }
}
