package com.spoonofcode.poa.feature.demo

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface DemoViewAction: BaseViewAction {
    data object InitView : DemoViewAction
}