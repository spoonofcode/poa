package com.spoonofcode.poa.feature.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.spoonofcode.poa.core.ui.theme.AppTheme

// TODO This file will be remove when Android Studio will be support previews in commonMain
@PreviewLightDark
@Composable
fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreen().InitializedProfileScreenPreview()
    }
}