package com.example.munjangzip.UserInfo.Pets


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.data.Pet
import com.example.munjangzip.ui.components.BasicButton
import com.example.munjangzip.ui.components.BasicButtonYellow
import com.example.munjangzip.ui.components.FishMessageCard

@Composable
fun PetSelect(navController: NavController, nickname: String?, libraryName: String?) {
    Log.d("🐰PETSELECT", "nickname = $nickname, libraryName = $libraryName")
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg_basic),
            contentDescription = "배경 이미지",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(20.dp))

                    FishMessageCard(
                        messageTop = "",
                        boldMiddle = "펫을 정할 시간이에요",
                        messageBottom = ""
                    )

                    Spacer(modifier = Modifier.height(30.dp))
                    var selectedPet by remember { mutableStateOf<Pet?>(null) }
                    PetGridBox(onPetSelected = { selectedPet = it })
                    Spacer(modifier = Modifier.height(32.dp))
                    selectedPet?.let { pet ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            //선택한 펫 보여주기
                            Image(
                                painter = painterResource(id = pet.imageRes),
                                contentDescription = pet.name,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(240.dp)
                                    .padding(16.dp)
                            )
                            BasicButton(
                                text = "입양하기",
                                onClick = {
                                    navController.navigate("PetName/${pet.name}?nickname=$nickname&libraryName=$libraryName")
                                }
                            )

                        }
                    }

                }
            }
        }
    }
}
