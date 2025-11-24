package com.spoonofcode.poa.feature.tac

import com.spoonofcode.poa.core.ui.BaseViewAction

internal sealed interface TACViewAction: BaseViewAction {
    data object InitView : TACViewAction
    data object SaveChanges : TACViewAction
}