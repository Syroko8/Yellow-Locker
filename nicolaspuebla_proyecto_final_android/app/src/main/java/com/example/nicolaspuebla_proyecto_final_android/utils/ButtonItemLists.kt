package com.example.nicolaspuebla_proyecto_final_android.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.laboratorio_b.ui.navigation.Destinations
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.ui.components.FabItemData
import com.example.nicolaspuebla_proyecto_final_android.ui.components.Identifier

object ButtonItemLists {

    private val landingScreenButtonList = listOf(
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

    private val joinTeamScreenButtonList = listOf(
        FabItemData(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            label = R.string.return_to_landing,
            identifier = Identifier.Return.name
        )
    )

    private val teamWelcomeScreenButtonList = listOf(
        FabItemData(
            icon = Icons.Filled.House,
            label = R.string.return_to_landing,
            identifier = Identifier.Return.name
        ),
        FabItemData(
            icon = Icons.Filled.List,
            label = R.string.members,
            identifier = Identifier.Members.name
        ),
        FabItemData(
            icon = Icons.Filled.EmojiEvents,
            label = R.string.matches,
            identifier = Identifier.Matches.name
        ),
        FabItemData(
            icon = Icons.Filled.CalendarMonth,
            label = R.string.calendar,
            identifier = Identifier.Calendar.name
        ),
        FabItemData(
            icon = Icons.Filled.Chat,
            label = R.string.chat,
            identifier = Identifier.Chat.name
        )
    )

    private val teamWelcomeScreenButtonListCaptain = listOf(
        FabItemData(
            icon = Icons.Filled.House,
            label = R.string.return_to_landing,
            identifier = Identifier.Return.name
        ),
        FabItemData(
            icon = Icons.Filled.List,
            label = R.string.members,
            identifier = Identifier.Members.name
        ),
        FabItemData(
            icon = Icons.Filled.EmojiEvents,
            label = R.string.matches,
            identifier = Identifier.Matches.name
        ),
        FabItemData(
            icon = Icons.Filled.CalendarMonth,
            label = R.string.calendar,
            identifier = Identifier.Calendar.name
        ),
        FabItemData(
            icon = Icons.Filled.Chat,
            label = R.string.chat,
            identifier = Identifier.Chat.name
        ),
        FabItemData(
            icon = Icons.Filled.Edit,
            label = R.string.edit,
            identifier = Identifier.Edit.name
        )
    )

    private val teamMembersScreenButtonList = listOf(
        FabItemData(
            icon = Icons.Filled.House,
            label = R.string.return_to_landing,
            identifier = Identifier.Return.name
        ),
        FabItemData(
            icon = Icons.Filled.Shield,
            label = R.string.team_screen,
            identifier = Identifier.Team.name
        ),
        FabItemData(
            icon = Icons.Filled.EmojiEvents,
            label = R.string.matches,
            identifier = Identifier.Matches.name
        ),
        FabItemData(
            icon = Icons.Filled.CalendarMonth,
            label = R.string.calendar,
            identifier = Identifier.Calendar.name
        ),
        FabItemData(
            icon = Icons.Filled.Chat,
            label = R.string.chat,
            identifier = Identifier.Chat.name
        )
    )

    private val teamMatchesScreenButtonList = listOf(
        FabItemData(
            icon = Icons.Filled.House,
            label = R.string.return_to_landing,
            identifier = Identifier.Return.name
        ),
        FabItemData(
            icon = Icons.Filled.Shield,
            label = R.string.team_screen,
            identifier = Identifier.Team.name
        ),
        FabItemData(
            icon = Icons.Filled.List,
            label = R.string.members,
            identifier = Identifier.Members.name
        ),
        FabItemData(
            icon = Icons.Filled.CalendarMonth,
            label = R.string.calendar,
            identifier = Identifier.Calendar.name
        ),
        FabItemData(
            icon = Icons.Filled.Chat,
            label = R.string.chat,
            identifier = Identifier.Chat.name
        )
    )

    fun getButtonListForView(view: String, rol: TeamRoles): List<FabItemData> {
        when(view){
            Destinations.LANDING_SCREEN -> return landingScreenButtonList
            Destinations.JOIN_TEAM -> return joinTeamScreenButtonList
            Destinations.TEAM_WELCOME ->
                return if(rol == TeamRoles.Captain ) teamWelcomeScreenButtonListCaptain
                else teamWelcomeScreenButtonList
            Destinations.TEAM_MEMBERS -> return  teamMembersScreenButtonList
            Destinations.TEAM_MATCHES -> return teamMatchesScreenButtonList
        }

        return emptyList()
    }
}