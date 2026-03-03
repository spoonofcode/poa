package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.presentation.compose.CustomIcons.IconWithBadge
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
    backNavigationEnable: Boolean,
    topAppBarTitle: StringResource,
    navigationBackAction: () -> Unit,
    iconBarActions: List<TopBarAction>,
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        title = { Texts.TLB(stringResource(resource = topAppBarTitle)) },
        navigationIcon = if (backNavigationEnable) {
            {
                IconButton(onClick = navigationBackAction) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBackIos,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            {}
        },
        actions = {
            iconBarActions.forEach { iconBarAction ->
                if (iconBarAction.badgeCount == null || iconBarAction.badgeCount == 0) {
                    IconButton(onClick = iconBarAction.onClick) {
                        Icon(
                            imageVector = iconBarAction.icon,
                            contentDescription = iconBarAction.description
                        )
                    }
                } else {
                    IconWithBadge(
                        icon = iconBarAction.icon,
                        contentDescription = iconBarAction.description,
                        count = iconBarAction.badgeCount,
                        onClick = iconBarAction.onClick
                    )
                }
            }
        }
    )
}

@Immutable
data class TopBarAction(
    val icon: ImageVector,
    val description: String,
    val badgeCount: Int? = null,
    val onClick: () -> Unit
)