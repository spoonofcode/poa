package com.spoonofcode.poa.feature.tac

sealed interface TACViewAction {
    data object InitView : TACViewAction
    data object SaveChanges : TACViewAction
}