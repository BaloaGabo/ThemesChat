package com.example.themeschat


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.themeschat.components.ChatTextField
import com.example.themeschat.components.MessageBox
import com.example.themeschat.enums.Themes
import com.example.themeschat.models.MessageData
import com.example.themeschat.utils.automaticResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ChatScream(changeThemes: ( Themes ) -> Unit ){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

            ModalDrawerSheet {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Elige un tema",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                HorizontalDivider(color = Color.Gray)

                Box(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable {
                        scope.launch {
                            changeThemes(Themes.ORIGINAL)
                            drawerState.close()
                        }
                    }
                ){
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Box(modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFCDE5FF))

                        )
                        Text(text = "Original Theme", fontSize = 20.sp,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }

                HorizontalDivider(color = Color.LightGray)

                Box(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable {
                        scope.launch {
                            changeThemes(Themes.NATURAL)
                            drawerState.close()
                        }
                    }
                ){
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Box(modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFDAD5))

                        )
                        Text(text = "Natural Theme", fontSize = 20.sp,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
                HorizontalDivider(color = Color.LightGray)

                Box(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable {
                        scope.launch {
                            changeThemes(Themes.SUNSET)
                            drawerState.close()
                        }
                    }
                ){
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Box(modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFECDCFF))

                        )
                        Text(text = "Sunset Theme", fontSize = 20.sp,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }

            }

        }) {

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
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
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->


            var listOfMessageData by rememberSaveable {
                mutableStateOf( listOf<MessageData>() )
            }

            var textInputMessage by rememberSaveable {
                mutableStateOf("")
            }

            val scrollState = rememberScrollState()

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
}

