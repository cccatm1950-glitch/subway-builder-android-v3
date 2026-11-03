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

private enum class BuildTool {
    TRACK, STATION
}

@Composable
fun SubwayBuilderApp() {
    var selectedTool by remember { mutableStateOf(BuildTool.TRACK) }
    var trackTiles by remember { mutableStateOf(setOf<Pair<Int, Int>>()) }
    var stationTiles by remember { mutableStateOf(setOf<Pair<Int, Int>>()) }

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
                "Choose a tool, then tap a tile",
                color = Color(0xFFB8C7D1),
                modifier = Modifier.padding(top = 6.dp, bottom = 12.dp)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    Button(onClick = { selectedTool = BuildTool.TRACK }) {
        Text(if (selectedTool == BuildTool.TRACK) "✓ Track" else "Track")
    }
    Button(onClick = { selectedTool = BuildTool.STATION }) {
        Text(if (selectedTool == BuildTool.STATION) "✓ Station" else "Station")
    }
    OutlinedButton(
        onClick = {
            trackTiles = emptySet()
            stationTiles = emptySet()
        }
    ) {
        Text("Clear")
    }
}
            Spacer(Modifier.height(12.dp))

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
                            val cell = row to col
                            val isStation = cell in stationTiles
                            val isTrack = cell in trackTiles

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(
                                        when {
                                            isStation -> Color(0xFFFFC857)
                                            isTrack -> Color(0xFF00C2A8)
                                            else -> Color(0xFF24323D)
                                        },
                                        RoundedCornerShape(6.dp)
                                    )
                                    .clickable {
                                        if (selectedTool == BuildTool.TRACK) {
                                            trackTiles =
                                                if (isTrack) trackTiles - cell
                                                else trackTiles + cell
                                            stationTiles = stationTiles - cell
                                        } else {
                                            stationTiles =
                                                if (isStation) stationTiles - cell
                                                else stationTiles + cell
                                            trackTiles = trackTiles - cell
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (isStation) {
                                    Text("●", color = Color(0xFF101820), fontSize = 22.sp)
                                } else if (isTrack) {
                                    Text("━", color = Color(0xFF101820), fontSize = 22.sp)
                                }
                            }
                        }
                    }
                }
            }

            Text(
                "Track: ${trackTiles.size}   Stations: ${stationTiles.size}",
                color = Color.White,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}
