package com.example.nicolaspuebla_proyecto_final_android.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio_b.ui.navigation.Destinations
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.utils.LocationChoosingInfo
import com.example.nicolaspuebla_proyecto_final_android.utils.MapAction
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager

/**
 * Enum que representa los dos estados posibles del botón flotante de acción (FAB).
 */
enum class FabState{
    Expanded,
    Collapsed
}

/**
 * Enum que identifica las distintas acciones que pueden realizarse desde el menú FAB.
 */
enum class Identifier{
    Settings,
    JoinGroup,
    CreateGroup,
    Logout,
    Return,
    Members,
    Matches,
    Calendar,
    Chat,
    Edit,
    Team,
    ModifyReturn,
    MapCancel,
    MapConfirm,
    CreateEvent,
    CreateEventReturn,
    LeaveTeam,
    CreateTeamReturn
}

/**
 * Clase que almacena la información un ítem del FAB.
 *
 * @param icon El icono que representa al ítem.
 * @param label El recurso string ID que se usará como etiqueta del ítem.
 * @param identifier Un identificador único del ítem como cadena.
 */
class FabItemData(
    val icon:ImageVector,
    val label: Int,
    val identifier: String
)

/**
 * Representa un ítem del FAB ya convertido con su label.
 *
 * @param icon El icono que representa al ítem.
 * @param label El texto que se mostrará como etiqueta.
 * @param identifier Un identificador único del ítem como cadena.
 */
class FabItem(
    val icon:ImageVector,
    val label: String,
    val identifier: String
)

/**
 * Composable que construye un menú FAB expandible.
 *
 * @param state El estado actual del FAB (expandido o colapsado).
 * @param onFloatingStateChange Callback que permite cambiar el estado del FAB.
 * @param items Lista de ítems que se mostrarán cuando el FAB esté expandido.
 * @param onItemClick Callback invocado cuando se hace clic en un ítem.
 */
@Composable
fun FabMenu(
    state: FabState,
    onFloatingStateChange: (FabState) -> Unit,
    items: List<FabItemData>,
    onItemClick: (String) -> Unit
){
    val transition = updateTransition(targetState = state, label = "transition")

    val rotate by transition.animateFloat(
        label = "rotate",
        transitionSpec = {
            tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            ) }
    ) {
        if(it == FabState.Expanded) 315f else 0f
    }

    val itemScale by transition.animateFloat(
        label = "ItemScale",
        transitionSpec = { tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        ) }
    ) {
        if(it == FabState.Expanded) 1f else 0f
    }

    val alpha by transition.animateFloat(
        label = "alpha",
        transitionSpec = { tween(durationMillis = 300) }
    ) {
        if(it == FabState.Expanded) 1f else 0f
    }

    Column(
        horizontalAlignment = Alignment.End
    ) {

        if(state == FabState.Expanded){
            items.forEach{

                val item = FabItem(
                    it.icon,
                    stringResource(it.label),
                    it.identifier)

                FabItemBuild(
                    item = item,
                    onItemClick = {
                        when(item.identifier){
                            Identifier.Settings.name -> {

                            }
                            Identifier.JoinGroup.name -> {
                                onFloatingStateChange(
                                    FabState.Collapsed
                                )
                                onItemClick(Destinations.JOIN_TEAM)
                            }
                            Identifier.CreateGroup.name -> {
                                onFloatingStateChange(
                                    FabState.Collapsed
                                )
                                onItemClick(Destinations.CREATE_TEAM)
                            }
                            Identifier.CreateTeamReturn.name -> {
                                onFloatingStateChange(
                                    FabState.Collapsed
                                )
                                onItemClick(Destinations.LANDING_SCREEN)
                            }
                            Identifier.Logout.name -> {
                                onFloatingStateChange(
                                    FabState.Collapsed
                                )
                                SessionManager.setLogOut(true)
                            }
                            Identifier.Return.name -> {
                                onFloatingStateChange(
                                    FabState.Collapsed
                                )
                                onItemClick(Destinations.LANDING_SCREEN)
                            }
                            Identifier.Team.name -> {
                                onFloatingStateChange(
                                    FabState.Collapsed
                                )
                                onItemClick(Destinations.TEAM_WELCOME)
                            }
                            Identifier.LeaveTeam.name -> {
                                onFloatingStateChange(
                                    FabState.Collapsed
                                )
                                SessionManager.leaveActualTeam.value = true
                            }
                            Identifier.Members.name -> {
                                onFloatingStateChange(
                                    FabState.Collapsed
                                )
                                onItemClick(Destinations.TEAM_MEMBERS)
                            }
                            Identifier.Matches.name -> {
                                onFloatingStateChange(
                                    FabState.Collapsed
                                )
                                onItemClick(Destinations.TEAM_MATCHES)
                            }
                            Identifier.Calendar.name -> {
                                onFloatingStateChange(
                                    FabState.Collapsed
                                )
                                onItemClick(Destinations.TEAM_CALENDAR)
                            }
                            Identifier.ModifyReturn.name -> {
                                onFloatingStateChange(
                                    FabState.Collapsed
                                )
                                onItemClick(Destinations.TEAM_CALENDAR)
                            }
                            Identifier.MapCancel.name -> {
                                onFloatingStateChange(
                                    FabState.Collapsed
                                )
                                when(LocationChoosingInfo.action.value){
                                    MapAction.ChangeOpponent -> onItemClick(Destinations.MODIFY_EVENTS)
                                    MapAction.ChooseOpponent -> onItemClick(Destinations.CREATE_EVENT)
                                    else -> throw Exception("Map action unmanaged")
                                }
                            }
                            Identifier.MapConfirm.name -> {
                                onFloatingStateChange(
                                    FabState.Collapsed
                                )
                                LocationChoosingInfo.setChosen(true)
                                when(LocationChoosingInfo.action.value){
                                    MapAction.ChangeOpponent -> onItemClick(Destinations.MODIFY_EVENTS)
                                    MapAction.ChooseOpponent -> onItemClick(Destinations.CREATE_EVENT)
                                    else -> throw Exception("Map action unmanaged")
                                }
                            }
                            Identifier.CreateEvent.name -> {
                                onFloatingStateChange(
                                    FabState.Collapsed
                                )
                                onItemClick(Destinations.CREATE_EVENT)
                            }
                            Identifier.CreateEventReturn.name -> {
                                onFloatingStateChange(
                                    FabState.Collapsed
                                )
                                onItemClick(Destinations.MODIFY_EVENTS)
                            }
                        }
                    },
                    alpha = alpha,
                    itemScale = itemScale,

                )
                Spacer(Modifier.size(16.dp))
            }
        }

        FloatingActionButton(
            onClick = {
                onFloatingStateChange(
                    if (transition.currentState == FabState.Expanded) FabState.Collapsed else FabState.Expanded
                )
            },
            modifier = Modifier
                .background(Color.Transparent)
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = stringResource(R.string.main_menu_icon),
                modifier = Modifier.rotate(rotate)
            )
        }
    }
}

/**
 * Elemento para cada ítem del menú FAB.
 *
 * @param item Objeto que contiene la información del ítem.
 * @param alpha Nivel de opacidad aplicado al ítem.
 * @param itemScale Escala del ítem para animaciones.
 * @param showLabel Si se debe mostrar la etiqueta junto al icono.
 * @param onItemClick Callback que se ejecuta al hacer clic sobre el ítem.
 */
@Composable
fun FabItemBuild(
    item: FabItem,
    alpha: Float,
    itemScale: Float,
    showLabel: Boolean = true,
    onItemClick: (FabItem) -> Unit
){
    val buttonColor = Color(59, 113, 254)

    Button(
        modifier = Modifier
            .padding(start = 6.dp, end = 6.dp, top = 4.dp)
            .height(70.dp)
            .width(200.dp)
            .scale(itemScale)
            .alpha(
                animateFloatAsState(
                    targetValue = alpha,
                    animationSpec = tween(50)
                ).value
            ),
        onClick = {
            onItemClick.invoke(item)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = item.icon,
                contentDescription = stringResource(R.string.fab_item_icon_description),
                Modifier.size(30.dp)
            )

            Spacer(Modifier.width(20.dp))

            if(showLabel){
                Text(
                    text = item.label,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .alpha(
                            animateFloatAsState(
                                targetValue = alpha,
                                animationSpec = tween(50)
                            ).value
                        )
                        .background(Color.Transparent)
                        .padding(start = 6.dp, end = 6.dp, top = 4.dp)
                )
            }
        }
    }
}