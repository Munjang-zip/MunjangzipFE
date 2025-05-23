package com.example.munjangzip.feature.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.ui.components.BasicButton
import com.example.munjangzip.ui.components.FishInputFieldLeft
import com.example.munjanzipr.ui.theme.BGyellow

@Composable
fun UserInfoScreen(navController: NavController) {
    var libraryName by remember { mutableStateOf("") }

    Scaffold(
        containerColor = BGyellow
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp, vertical = 48.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.apptitle),
                    contentDescription = "앱 로고",
                    modifier = Modifier
                        .width(400.dp)
                        .padding(top = 16.dp, bottom = 48.dp)
                )
                // 물고기 입력 필드: 로고 아래
                FishInputFieldLeft(
                    label = "도서관 이름을 적어주세요 : )",
                    value = libraryName,
                    onValueChange = { libraryName = it }
                )
                Spacer(modifier = Modifier.weight(1f))

                if (libraryName.isNotBlank()) {
                    BasicButton(
                        text = "다음",
                        onClick = { navController.navigate("userInfo2") }
                    )
                }

            }
        }
    }
}
