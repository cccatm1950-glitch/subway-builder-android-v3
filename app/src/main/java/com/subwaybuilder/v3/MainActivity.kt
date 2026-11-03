package com.subwaybuilder.v3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SubwayBuilderApp() }
    }
}

@Composable
fun SubwayBuilderApp() {
    var trackTiles by remember { mutableStateOf(setOf<Pair<Int, Int>>()) }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF101820))
                .padding(16.dp)
        ) {
            Text(
                "SUBWAY BUILDER",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Tap a tile to lay track",
                color = Color(0xFFB8C7D1),
                modifier = Modifier.padding(top = 6.dp, bottom = 16.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (row in 0 until 8) {
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        for (col in 0 until 8) {
                            val hasTrack = (row to col) in trackTiles
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(
                                        if (hasTrack) Color(0xFF00C2A8)
                                        else Color(0xFF24323D),
                                        RoundedCornerShape(6.dp)
                                    )
                                    .clickable {
                                        trackTiles = if (hasTrack) {
                                            trackTiles - (row to col)
                                        } else {
                                            trackTiles + (row to col)
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (hasTrack) {
                                    Text("━", color = Color(0xFF101820), fontSize = 22.sp)
                                }
                            }
                        }
                    }
                }
            }

            Text(
                "Track tiles: ${trackTiles.size}",
                color = Color.White,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}
