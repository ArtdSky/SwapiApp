package com.example.swapiapp.presentation.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.random.Random


@Composable
fun StarryBackground(color: Color) {
    val random = Random(System.currentTimeMillis())

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(color = color)

        repeat(100) {
            val x: Float = random.nextInt(size.width.toInt()).toFloat()
            val y: Float = random.nextInt(size.height.toInt()).toFloat()
            val radius: Float = random.nextFloat() + 1f
            val color = Color.White

            drawCircle(
                color = color,
                center = Offset(x, y),
                radius = radius.dp.toPx(),
                style = Stroke(width = 2.dp.toPx())
            )
        }
    }
}