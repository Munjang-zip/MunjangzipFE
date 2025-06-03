package com.example.munjangzip.UserInfo.Pets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.munjanzipr.ui.theme.BGyellow
import com.example.munjangzip.R
import com.example.munjangzip.data.Pet
import com.example.munjangzip.data.petList
import com.example.munjanzipr.ui.theme.BasicYellow
import com.example.munjanzipr.ui.theme.LightYellow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PetGridBox(
    onPetSelected: (Pet) -> Unit
) {
    val petList = listOf(
        Pet("rabbit", R.drawable.rabbit),
        Pet("cat", R.drawable.cat),
        Pet("fish", R.drawable.fish),
        Pet("fish_blue", R.drawable.fish_blue),
        Pet("fish", R.drawable.fish),
        Pet("fish_blue", R.drawable.fish_blue),
        Pet("fish", R.drawable.fish),
        Pet("fish_blue", R.drawable.fish_blue),
        Pet("fish", R.drawable.fish),
        Pet("fish_blue", R.drawable.fish_blue),
        Pet("fish", R.drawable.fish),
        Pet("fish_blue", R.drawable.fish_blue),
        Pet("fish", R.drawable.fish),
        Pet("fish_blue", R.drawable.fish_blue),
        Pet("fish", R.drawable.fish),
        Pet("fish_blue", R.drawable.fish_blue),
    )

    var selectedPet by remember { mutableStateOf<Pet?>(null) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 24.dp)
            .shadow(8.dp, RoundedCornerShape(30.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(BGyellow)
            .padding(16.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(petList) { pet ->
                val isSelected = selectedPet == pet

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (isSelected) Color.White.copy(alpha = 0.5f)
                            else Color.Transparent
                        )
                        .clickable {
                            selectedPet = pet
                            onPetSelected(pet)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = pet.imageRes),
                        contentDescription = pet.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
