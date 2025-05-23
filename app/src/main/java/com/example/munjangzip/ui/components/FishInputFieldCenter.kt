
/*물고기 입력 카드 : 사용시 아래처럼
import com.example.munjangzip.ui.components.FishInputField

var email by remember { mutableStateOf("") }

FishInputField(
    label = "이메일을 입력해주세요",
    value = email,
    onValueChange = { email = it }
)

*/
package com.example.munjangzip.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.munjangzip.R
import com.example.munjanzipr.ui.theme.Basic
import com.example.munjanzipr.ui.theme.BasicYellow
import com.example.munjanzipr.ui.theme.LightYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FishInputFieldCenter(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Box(contentAlignment = Alignment.Center) {
        // 배경 카드
        Card(
            modifier = Modifier
                .size(width = 250.dp, height = 85.dp)
                .clip(RoundedCornerShape(40.dp))
                .zIndex(0f),
            colors = CardDefaults.cardColors(
                containerColor = LightYellow
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    placeholder = {
                        Text(
                            text = placeholder,
                            color = Basic,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    shape = RoundedCornerShape(30.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = LightYellow,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        cursorColor = Basic
                    ),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Basic,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }

        // 물고기 이미지
        Image(
            painter = painterResource(R.drawable.fish),
            contentDescription = null,
            modifier = Modifier
                .zIndex(1f)
                .offset(y = (-45).dp)
        )
    }
}
