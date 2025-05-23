package com.example.munjangzip.UserInfo.Pets

import com.example.munjangzip.data.petList
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.munjangzip.ui.components.FishInputFieldCenter
import com.example.munjangzip.ui.components.FishMessageCard
@Composable
fun PetName(petName: String?) {
    val selectedPet = petList.find { it.name == petName }
        //내부에서 선택한 펫 가져오기
        //petList를 임포트하고, 전달받은 이름으로 Pet 객체 찾아옴

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
                    Spacer(modifier = Modifier.height(50.dp))

                    var petName by remember { mutableStateOf("") }
                    FishInputFieldCenter(
                        value = petName,
                        onValueChange = { petName = it },
                        placeholder = "눌러서 이름을 지어주세요!"
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    //선택한 펫 이미지
                    selectedPet?.let { pet ->
                        Image(
                            painter = painterResource(id = pet.imageRes),
                            contentDescription = pet.name,
                            contentScale = ContentScale.Fit, // Fit
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp)
                                .aspectRatio(1f) // 정사각형 비율 유지
                        )
                    }

                }
                // 하단 버튼
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 48.dp)
                ) {
                    BasicButtonYellow(
                        text = "이렇게 결정!",
                        onClick = {
                            // TODO: 다음 페이지 이동 처리
                        }
                    )
                }
            }
        }
    }
}
