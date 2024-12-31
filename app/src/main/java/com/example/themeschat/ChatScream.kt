package com.example.themeschat


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.themeschat.components.ChatTextField
import com.example.themeschat.components.MessageBox
import com.example.themeschat.models.MessageData
import com.example.themeschat.utils.automaticResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ChatScreem(){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                title = {
                    Text(text = "Themes Chat")
                })
        },
        modifier = Modifier.fillMaxSize()) { innerPadding ->


        var listOfMessageData by rememberSaveable {
            mutableStateOf( listOf<MessageData>() )
        }

        var textInputMessage by rememberSaveable {
            mutableStateOf("")
        }

        val scrollState = rememberScrollState()

        val scope = rememberCoroutineScope()

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {

            MessageBox(
                listOfMessageData = listOfMessageData,
                modifier = Modifier.weight(1f),
                scrollState = scrollState)


            ChatTextField(
                textInputMessage = textInputMessage,
                onChangeValueTextInput = { textInputMessage = it},
                onSendMessage = {

                    val newMessage = MessageData(
                        isMine = true,
                        text = textInputMessage
                    )

                    listOfMessageData = listOfMessageData + newMessage

                    textInputMessage = ""

                    val responseMessage = automaticResponse()



                    scope.launch {
                        delay(100)
                        scrollState.animateScrollTo(scrollState.maxValue)

                        delay(1500)
                        listOfMessageData = listOfMessageData + responseMessage

                        delay(100)
                        scrollState.animateScrollTo(scrollState.maxValue)

                    }

                }
            )





        }
    }
}

