package com.example.munjangzip.feature.auth

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.munjangzip.R
import com.example.munjangzip.ui.components.FishMessageCard


@Composable
fun StartScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as Activity

    val googleLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        handleGoogleLoginResult(result)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg_basic),
            contentDescription = "배경 이미지",
            contentScale = ContentScale.Crop, //  화면 비율에 따라 꽉 채우기 (비율 유지하면서 확대)
            modifier = Modifier.fillMaxSize()
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = androidx.compose.ui.graphics.Color.Transparent
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(24.dp))

                    Image(
                        painter = painterResource(R.drawable.apptitle),
                        contentDescription = "로고"
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    FishMessageCard(
                        messageTop = "",
                        boldMiddle = "문장집에 오신걸 환영해요!",
                        messageBottom = ""
                    )
                }

                // 하단 버튼 영역
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 70.dp) // 하단 패딩
                ) {
                    Image(
                        painter = painterResource(R.drawable.kakao_login_large),
                        contentDescription = "카카오 로그인",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(width = 280.dp, height = 56.dp)
                            .clickable { performKakaoLogin(context) }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Image(
                        painter = painterResource(R.drawable.google_login),
                        contentDescription = "구글 로그인",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(width = 280.dp, height = 56.dp)
                            .clickable { launchGoogleLogin(context, googleLauncher) }
                    )
                }
            }
        }}}