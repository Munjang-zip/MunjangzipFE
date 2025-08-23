package com.example.munjangzip.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

private val CategoryViewModel.categoryState: Any
private val Any?.libraryName: String
private val Any?.nickName: String
private val ERROR.result: Any

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCategoryPage(
    navController: NavController,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    val categoryState by viewModel.categoryState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Surface(
                modifier = Modifier.height(130.dp),
                shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp),
                shadowElevation = 8.dp
            ) {
                TopAppBar(
                    modifier = Modifier.height(100.dp),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Yellow
                    ),
                    title = {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "${categoryState?.result?.nickName ?: "사용자"} 님의",
                                fontSize = 14.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "'${categoryState?.result?.libraryName ?: ""}' 도서관",
                                fontSize = 20.sp,
                                color = Color.DarkGray,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    },
                )
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            //BackGround()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            Box(contentAlignment = Alignment.Center) {
                ElevatedButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(65.dp)
                        .width(200.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrightYellow,
                        contentColor = Color.Gray
                    ),
                    onClick = { navController.navigate("addCategory") },
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "카테고리 추가하기",
                        modifier = Modifier.padding(start = 8.dp),
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    )
                }
                Image(
                    painter = painterResource(R.drawable.fish),
                    contentDescription = null,
                    modifier = Modifier
                        .zIndex(1f)
                        .offset(x = (-105).dp)
                        .size(100.dp)
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))

            // 카테고리 리스트 -> BookCategoryPager에 전달
            //categoryState?.result?.categoryList?.let { categories ->
                //BookCategoryPager(navController, categories)
            //}
            Spacer(modifier = Modifier.padding(13.dp))
        }
    }
}
