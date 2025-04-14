package com.example.nicolaspuebla_proyecto_final_android.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Settings
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.ui.components.FabItemData
import com.example.nicolaspuebla_proyecto_final_android.ui.components.Identifier

object ButtonItemLists {

    val landingScreenButtonList = listOf(
        FabItemData(
            icon = Icons.Filled.Settings,
            label = R.string.settings,
            identifier = Identifier.Settings.name
        ),
        FabItemData(
            icon = Icons.Filled.GroupAdd,
            label = R.string.create_team,
            identifier = Identifier.CreateGroup.name
        ),
        FabItemData(
            icon = Icons.Filled.Groups,
            label = R.string.join_group,
            identifier = Identifier.JoinGroup.name
        )
    )
}