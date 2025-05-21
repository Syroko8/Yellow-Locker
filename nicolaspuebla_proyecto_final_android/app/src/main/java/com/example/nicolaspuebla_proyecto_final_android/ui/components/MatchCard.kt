package com.example.nicolaspuebla_proyecto_final_android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Match

@Composable
fun MatchCard(match: Match, onClick: (Match) -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = { onClick(match) }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = match.team.name,
                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                color = Color.Black,
                fontSize = 16.sp
            )
            Goals(match)
            Text(
                text = match.opponent.name,
                fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun Goals(match: Match){
    println(">>>>>>>>>${match}")
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .widthIn(min = 70.dp)
            .height(30.dp)
            .background(color = Color(217,217,217))
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = "${match.ownGoals} -",
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            color = Color(61,147,247),
            fontSize = 14.sp
        )
        Text(
            text = match.opponentGoals.toString(),
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            color = Color(61,147,247),
            fontSize = 14.sp
        )
    }
}