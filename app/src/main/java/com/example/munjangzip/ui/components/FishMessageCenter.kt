
/*물고기 카드 메세지 : 사용시 아래처럼
import com.example.munjangzip.ui.components.FishMessageCard

...

FishMessageCard(
    messageTop = "책의 ",
    boldMiddle = "바코드를 촬영",
    messageBottom = "하면\n자동으로 책이 등록 돼요!"
)
*/
package com.example.munjangzip.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.munjangzip.R
import com.example.munjanzipr.ui.theme.Basic
import com.example.munjanzipr.ui.theme.LightYellow

@Composable
fun FishMessageCard(
    messageTop: String,
    boldMiddle: String,
    messageBottom: String
) {
    Box(contentAlignment = Alignment.Center) {
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
                Text(
                    text = buildAnnotatedString {
                        append(messageTop)
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(boldMiddle)
                        }
                        append(messageBottom)
                    },
                    fontSize = 13.sp,
                    color = Basic,
                    textAlign = TextAlign.Center
                )
            }
        }

        Image(
            painter = painterResource(R.drawable.fish),
            contentDescription = null,
            modifier = Modifier
                .zIndex(1f)
                .offset(y = (-45).dp)
        )
    }
}
