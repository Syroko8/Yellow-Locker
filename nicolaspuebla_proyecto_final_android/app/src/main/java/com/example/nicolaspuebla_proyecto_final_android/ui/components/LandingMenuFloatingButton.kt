package com.example.nicolaspuebla_proyecto_final_android.ui.components

import android.view.Menu
import android.widget.Space
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nicolaspuebla_proyecto_final_android.R

enum class FloatingMenuState{
    Expanded,
    Colapsed
}

enum class Identifier{
    Settings,
    JoinGroup,
    CreateGroup
}

class MenuItem(
    val icon:ImageBitmap,
    val label: String,
    val identifier: String
)

@Composable
fun LandingMenuFloatingButton(
    state: FloatingMenuState,

    onFloatingStateChange: (FloatingMenuState) -> Unit,
    items: List<MenuItem>
){
    val transition = updateTransition(targetState = state, label = "transition")
    val rotate by transition.animateFloat(label = "rotate") {
        if(it == FloatingMenuState.Expanded) 315f else 0f
    }

    val itemScale by transition.animateFloat(label = "ItemScale") {
        if(it == FloatingMenuState.Expanded) 36f else 0f
    }

    val alpha by transition.animateFloat(
        label = "alpha",
        transitionSpec = { tween(durationMillis = 50) }
    ) {
        if(it == FloatingMenuState.Expanded) 1f else 0f
    }

    val textShadow by transition.animateDp(
        label = "textShadow",
        transitionSpec = { tween(durationMillis = 50) }
    ) {
        if(it == FloatingMenuState.Expanded) 2.dp else 0.dp
    }

    Column(
        horizontalAlignment = Alignment.End
    ) {

        if(state == FloatingMenuState.Expanded){
            items.forEach{
                MenuItemBuild(
                    item = it,
                    onItemClick = {
                        when(it.identifier){
                            Identifier.Settings.name -> {

                            }
                            Identifier.JoinGroup.name -> {

                            }
                            Identifier.CreateGroup.name -> {

                            }
                        }
                    },
                    alpha = alpha,
                    textShadow = textShadow,
                    itemScale = itemScale,

                )
                Spacer(Modifier.size(16.dp))
            }
        }

        FloatingActionButton(
            onClick = {
                onFloatingStateChange(
                    if (transition.currentState == FloatingMenuState.Expanded) FloatingMenuState.Colapsed else FloatingMenuState.Expanded
                )
            },
            modifier = Modifier
                .background(Color(59, 113, 254))
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = stringResource(R.string.main_menu_icon),
                modifier = Modifier.rotate(rotate)
            )
        }
    }
}

@Composable
fun MenuItemBuild(
    item: MenuItem,
    alpha: Float,
    textShadow: Dp,
    itemScale: Float,
    showLabel: Boolean = true,
    onItemClick: (MenuItem) -> Unit
){

    val buttonColor = MaterialTheme.colorScheme.secondary
    val interactionSource = remember { MutableInteractionSource() }
    val shadow = Color.Black.copy(.5f)

    Row {
        if(showLabel){
            Text(
                text = item.label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .alpha(
                        animateFloatAsState(
                            targetValue = alpha,
                            animationSpec = tween(50)
                        ).value
                    )
                    .shadow(textShadow)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(start = 6.dp, end = 6.dp, top = 4.dp)
            )
            Spacer(Modifier.size(16.dp))
        }

        Canvas(
            modifier = Modifier
                .size(32.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberRipple(
                        bounded = false,
                        radius = 20.dp,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    onClick = {
                        onItemClick.invoke(item)
                    }
                )
        ){

            drawCircle(
                color = shadow,
                radius = itemScale,
                center = Offset(
                    center.x + 2f,
                    center.y + 2f
                )
            )

            drawCircle(
                color = buttonColor,
                radius = itemScale
            )

            drawImage(
                image = item.icon,
                topLeft = Offset(
                    center.x - (item.icon.width / 2),
                    center.y - (item.icon.width / 2)
                ),
                alpha = alpha
            )
        }
    }
}