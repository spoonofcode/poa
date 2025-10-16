package com.spoonofcode.poa.core.ui.compose

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun IconWithBadge(
    icon: ImageVector,
    contentDescription: String? = null,
    count: Int,
    onClick: () -> Unit
) {
    BadgedBox(
        badge = {
            if (count > 0) {
                Badge {
                    Text(if (count > 99) "99+" else count.toString())
                }
            }
        }
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription
            )
        }
    }
}