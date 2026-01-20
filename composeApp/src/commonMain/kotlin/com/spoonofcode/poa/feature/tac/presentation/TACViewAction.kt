package com.spoonofcode.poa.feature.tac.presentation

import com.spoonofcode.poa.core.ui.base.BaseViewAction

internal sealed interface TACViewAction: BaseViewAction {
    data object InitView : TACViewAction
    data object SaveChanges : TACViewAction
}