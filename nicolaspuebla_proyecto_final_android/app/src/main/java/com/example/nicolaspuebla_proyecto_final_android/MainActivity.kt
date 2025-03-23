package com.example.nicolaspuebla_proyecto_final_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nicolaspuebla_proyecto_final_android.ui.components.FabItem
import com.example.nicolaspuebla_proyecto_final_android.ui.components.FabLandingScreen
import com.example.nicolaspuebla_proyecto_final_android.ui.components.FabState
import com.example.nicolaspuebla_proyecto_final_android.ui.components.Identifier
import com.example.nicolaspuebla_proyecto_final_android.ui.screens.landingScreen.LandingScreen
import com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamWelcome.TeamWelcomeScreen
import com.example.nicolaspuebla_proyecto_final_android.ui.theme.Nicolaspuebla_proyecto_final_androidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Nicolaspuebla_proyecto_final_androidTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {

                        var landingScreenMenuState by remember{
                            mutableStateOf(FabState.Colapsed)
                        }

                        val items = listOf(
                            FabItem(
                                icon = Icons.Filled.Settings,
                                label = stringResource(R.string.settings),
                                identifier = Identifier.Settings.name
                            ),
                            FabItem(
                                icon = Icons.Filled.GroupAdd,
                                label = stringResource(R.string.create_group),
                                identifier = Identifier.CreateGroup.name
                            ),
                            FabItem(
                                icon = Icons.Filled.Groups,
                                label = stringResource(R.string.join_group),
                                identifier = Identifier.JoinGroup.name
                            )
                        )

                        FabLandingScreen(
                            state = landingScreenMenuState,
                            onFloatingStateChange = {
                                landingScreenMenuState = it
                            },
                            items = items
                        )
                    }
                ) { innerPadding ->
                    TeamWelcomeScreen(innerPadding)
                }
            }
        }
    }
}

@Preview
@Composable
fun MainPreview(){
    MainActivity()
}