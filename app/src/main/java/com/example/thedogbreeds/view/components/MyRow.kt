package com.example.thedogbreeds.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thedogbreeds.R
import com.example.thedogbreeds.ui.theme.TheDogBreedsTheme

@Composable
fun MyRow(
    painter: Painter,
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painter,
            contentDescription = title,
            modifier = Modifier
                .height(90.dp)
                .width(90.dp)
                .padding(start = 10.dp)
        )
        Text(
            text = title,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Start
        )
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(imageVector = Icons.Outlined.KeyboardArrowRight, contentDescription = "")
        }
    }
}

@Preview
@Composable
private fun MyRowPreview() {
    MyRow(painterResource(id = R.drawable.dogbreed), title = "Dog Breed")
}