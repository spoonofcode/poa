package com.spoonofcode.core.test.base

//import com.spoonofcode.core.presentation.navigation.ViewModelNavigator
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.core.presentation.navigation.ViewModelNavigator
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolver
import org.koin.test.get

//import kotlin.test.BeforeTest

abstract class BaseViewModelTest : BaseTest() {
    protected lateinit var viewModelNavigator: ViewModelNavigator
    protected lateinit var routeResolver: VoyagerRouteResolver

    //    @BeforeTest
    override fun setup() {
        super.setup()
        viewModelNavigator = get()
        routeResolver = get()
    }

//    private fun testModule() = module {
//        single { mock<ViewModelNavigator>(mode = MockMode.autofill) }
//    }
}