
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.munjangzip.R
import com.example.munjanzipr.ui.theme.Basic
import com.example.munjanzipr.ui.theme.BasicYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FishInputFieldLeft(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .height(56.dp)
    ) {
        // 물고기 사진
        Image(
            painter = painterResource(id = R.drawable.fish),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterStart)
                .offset(x = (-16).dp)
                .zIndex(1f)
        )

        // 텍스트 필드
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = label,
                    color = Basic,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            shape = RoundedCornerShape(30.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = BasicYellow,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Basic
            ),
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 14.sp,
                color = Basic,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}
