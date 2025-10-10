package com.spoonofcode.poa.core.ui.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
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
            iconBarActions.forEach { spec ->
                IconButton(onClick = spec.onClick) {
                    Icon(
                        imageVector = spec.icon,
                        contentDescription = spec.description
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
    val onClick: () -> Unit
)