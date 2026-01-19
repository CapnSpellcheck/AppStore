package com.example.qcells.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp

@Composable fun AppIcon(color: Color, size: Dp) {
   val iconPainter = remember {
      object : Painter() {
         override val intrinsicSize: Size
            get() = Size(1.0f, 1.0f)

         override fun DrawScope.onDraw() {
            drawRect(color = color)
         }

      }
   }
   Image(painter = iconPainter, contentDescription = "app icon", Modifier
      .size(size)
      .clip(RoundedCornerShape(size / 5))
   )
}
