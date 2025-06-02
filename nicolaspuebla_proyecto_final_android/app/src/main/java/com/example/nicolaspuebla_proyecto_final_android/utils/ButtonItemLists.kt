package com.example.nicolaspuebla_proyecto_final_android.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.rounded.DoorFront
import com.example.laboratorio_b.ui.navigation.Destinations
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.ui.components.FabItemData
import com.example.nicolaspuebla_proyecto_final_android.ui.components.Identifier

object ButtonItemLists {

    private val landingScreenButtonList = listOf(
        FabItemData(
            icon = Icons.Filled.GroupAdd,
            label = R.string.create_team,
            identifier = Identifier.CreateGroup.name
        ),
        FabItemData(
            icon = Icons.Filled.Groups,
            label = R.string.join_group,
            identifier = Identifier.JoinGroup.name
        ),
        FabItemData(
            icon = Icons.Filled.Settings,
            label = R.string.settings,
            identifier = Identifier.Settings.name
        ),
        FabItemData(
            icon = Icons.AutoMirrored.Filled.Logout,
            label = R.string.logout,
            identifier = Identifier.Logout.name
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
            icon = Icons.AutoMirrored.Filled.List,
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
            icon = Icons.AutoMirrored.Filled.Chat,
            label = R.string.chat,
            identifier = Identifier.Chat.name
        ),
        FabItemData(
            icon = Icons.Rounded.DoorFront,
            label = R.string.leave_team,
            identifier = Identifier.LeaveTeam.name
        ),
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
            icon = Icons.AutoMirrored.Filled.Chat,
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
            icon = Icons.AutoMirrored.Filled.List,
            label = R.string.members,
            identifier = Identifier.Members.name
        ),
        FabItemData(
            icon = Icons.Filled.CalendarMonth,
            label = R.string.calendar,
            identifier = Identifier.Calendar.name
        ),
        FabItemData(
            icon = Icons.AutoMirrored.Filled.Chat,
            label = R.string.chat,
            identifier = Identifier.Chat.name
        )
    )

    private val teamCalendarButtonList = listOf(
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
            icon = Icons.AutoMirrored.Filled.List,
            label = R.string.members,
            identifier = Identifier.Members.name
        ),
        FabItemData(
            icon = Icons.Filled.EmojiEvents,
            label = R.string.matches,
            identifier = Identifier.Matches.name
        ),
        FabItemData(
            icon = Icons.AutoMirrored.Filled.Chat,
            label = R.string.chat,
            identifier = Identifier.Chat.name
        )
    )

    private val teamEventModifyButtonList = listOf(
        FabItemData(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            label = R.string.return_str,
            identifier = Identifier.ModifyReturn.name
        ),
        FabItemData(
            icon = Icons.Filled.Add,
            label = R.string.create_event,
            identifier = Identifier.CreateEvent.name
        )
    )

    private val mapButtonList = listOf(
        FabItemData(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            label = R.string.cancel,
            identifier = Identifier.MapCancel.name
        ),
        FabItemData(
            icon = Icons.Filled.Check,
            label = R.string.confirm,
            identifier = Identifier.MapConfirm.name
        ),
    )

    private val createEventButtonList = listOf(
        FabItemData(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            label = R.string.return_str,
            identifier = Identifier.CreateEventReturn.name
        )
    )


    fun getButtonListForView(view: String): List<FabItemData> {
        when(view){
            Destinations.LANDING_SCREEN -> return landingScreenButtonList
            Destinations.JOIN_TEAM -> return joinTeamScreenButtonList
            Destinations.TEAM_WELCOME -> return teamWelcomeScreenButtonList
            Destinations.TEAM_MEMBERS -> return  teamMembersScreenButtonList
            Destinations.TEAM_MATCHES -> return teamMatchesScreenButtonList
            Destinations.TEAM_CALENDAR -> return teamCalendarButtonList
            Destinations.MODIFY_EVENTS -> return teamEventModifyButtonList
            Destinations.MAP -> return mapButtonList
            Destinations.CREATE_EVENT -> return createEventButtonList
        }

        return emptyList()
    }
}