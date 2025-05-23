package com.example.munjangzip.ui.components
/* 기본 버튼 디자인
*

BasicButton(
    text = "등록 완료",
    onClick = { navController.navigate("nextScreen") }
)
*
* */
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.munjanzipr.ui.theme.Basic

@Composable
fun BasicButton(
    onClick: () -> Unit,
    text: String = "다음"
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth(0.6f) // 화면의 60정도..?
            .height(56.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(30.dp),
            shadowElevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Basic
                )
            }
        }
    }
}
