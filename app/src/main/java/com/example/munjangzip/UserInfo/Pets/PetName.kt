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
import androidx.navigation.NavBackStackEntry
import com.example.munjangzip.R
import com.example.munjangzip.data.petList
import com.example.munjangzip.model.RegisterRequest
import com.example.munjangzip.model.RegisterResponse
import com.example.munjangzip.network.RetrofitClient
import com.example.munjangzip.ui.components.BasicButtonYellow
import com.example.munjangzip.ui.components.FishInputFieldCenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun PetName(
    navController: NavController,
    backStackEntry: NavBackStackEntry
) {
    val petParam = backStackEntry.arguments?.getString("pet")
    val nickname = backStackEntry.arguments?.getString("nickname") ?: ""
    val libraryName = backStackEntry.arguments?.getString("libraryName") ?: ""

    val selectedPet = petList.find { it.name == petParam }

    var petName by remember { mutableStateOf("") }

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

                    FishInputFieldCenter(
                        value = petName,
                        onValueChange = { petName = it },
                        placeholder = "눌러서 이름을 지어주세요!"
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    selectedPet?.let { pet ->
                        Image(
                            painter = painterResource(id = pet.imageRes),
                            contentDescription = pet.name,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp)
                                .aspectRatio(1f)
                        )
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 48.dp)
                ) {
                    BasicButtonYellow(
                        text = "이렇게 결정!",
                        onClick = {
                            Log.d("REGISTER", "😊nickname=$nickname, libraryName=$libraryName, pet=$petParam, petName=$petName")

                            val request = RegisterRequest(
                                nickname = nickname,
                                libraryName = libraryName,
                                pet = petParam ?: "",
                                petName = petName
                            )

                            RetrofitClient.api.registerUser(request)
                                .enqueue(object : Callback<RegisterResponse> {
                                    override fun onResponse(
                                        call: Call<RegisterResponse>,
                                        response: Response<RegisterResponse>
                                    ) {
                                        if (response.isSuccessful && response.body()?.isSuccess == true) {
                                            Log.d("REGISTER", "회원 등록 성공")
                                            navController.navigate("mainCategoryPage")
                                        } else {
                                            Log.e("REGISTER", "등록 실패: ${response.errorBody()?.string()}")
                                        }
                                    }

                                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                                        Log.e("REGISTER", "서버 요청 실패", t)
                                    }
                                })
                        }
                    )
                }
            }
        }
    }
}
