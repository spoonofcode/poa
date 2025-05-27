package com.spoonofcode.poa.feature.nft.edit

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.spoonofcode.poa.core.ui.BaseScreen
import com.spoonofcode.poa.core.ui.ext.koinViewModel
import com.spoonofcode.poa.resources.Res
import com.spoonofcode.poa.resources.edit_nft_info
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.StringResource

internal class NFTInfoEditScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.edit_nft_info
) : BaseScreen<NFTInfoEditViewModel, NFTInfoEditViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<NFTInfoEditViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: NFTInfoEditViewModel,
        viewState: NFTInfoEditViewState
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
            viewModel.initView()
        }

        super.reloadScreen = { viewModel.initView() }

        return ContentView(
            viewState = viewState,
            changeTitle = { viewModel.changeTitle(it) },
            changeDescription = { viewModel.changeDescription(it) },
            changeClub = { viewModel.changeClub(it) },
            changeRoom = { viewModel.changeRoom(it) },
            changeLevel = { viewModel.changeLevel(it) },
            changeType = { viewModel.changeType(it) },
            changeMinNumberOfPeople = { viewModel.changeMinNumberOfPeople(it) },
            changeMaxNumberOfPeople = { viewModel.changeMaxNumberOfPeople(it) },
            changeCost = { viewModel.changeCost(it) },
            changeStartDateTime = { viewModel.changeStartDateTime(it) },
            changeEndDateTime = { viewModel.changeEndDateTime(it) },
            submitSportEvent = { viewModel.submitSportEvent() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: NFTInfoEditViewState,
        changeTitle: (String) -> Unit,
        changeDescription: (String) -> Unit,
        changeClub: (Int) -> Unit,
        changeRoom: (Int) -> Unit,
        changeLevel: (Int) -> Unit,
        changeType: (Int) -> Unit,
        changeMinNumberOfPeople: (Int) -> Unit,
        changeMaxNumberOfPeople: (Int) -> Unit,
        changeCost: (Int) -> Unit,
        changeStartDateTime: (LocalDateTime) -> Unit,
        changeEndDateTime: (LocalDateTime) -> Unit,
        submitSportEvent: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
//            TextFields.Outlined(
//                value = viewState.title,
//                onValueChange = { changeTitle(it) },
//                label = stringResource(resource = Res.string.title),
//            )
//            TextFields.Outlined(
//                value = viewState.description,
//                onValueChange = { changeDescription(it) },
//                label = stringResource(resource = Res.string.description),
//            )
//            DropDownMenus.DropdownMenu(
//                enabled = true,
//                label = stringResource(resource = Res.string.club),
//                value = viewState.clubs.getValue(viewState.selectedClubId!!),
//                values = viewState.clubs,
//                onValueChange = { changeClub(it) }
//            )
//            DropDownMenus.DropdownMenu(
//                enabled = true,
//                label = stringResource(resource = Res.string.room),
//                value = viewState.rooms.getValue(viewState.selectedRoomId!!),
//                values = viewState.rooms,
//                onValueChange = { changeRoom(it) }
//            )
//            DropDownMenus.DropdownMenu(
//                enabled = true,
//                label = stringResource(resource = Res.string.level),
//                value = viewState.levels.getValue(viewState.selectedLevelId!!),
//                values = viewState.levels,
//                onValueChange = { changeLevel(it) }
//            )
//            DropDownMenus.DropdownMenu(
//                enabled = true,
//                label = stringResource(resource = Res.string.type),
//                value = viewState.types.getValue(viewState.selectedTypeId!!),
//                values = viewState.types,
//                onValueChange = { changeType(it) }
//            )
//
//            Sliders.RangeSlider(
//                label = stringResource(resource = Res.string.number_of_people),
//                minValue = PEOPLE_RANGE_MIN_VALUE,
//                maxValue = PEOPLE_RANGE_MAX_VALUE,
//                steps = PEOPLE_RANGE_NUMBER_OF_STEPS,
//                selectedStartPosition = viewState.selectedMinNumberOfPeople.toFloat(),
//                selectedEndPosition = viewState.selectedMaxNumberOfPeople.toFloat(),
//                onStartPositionChange = { changeMinNumberOfPeople(it) },
//                onEndPositionChange = { changeMaxNumberOfPeople(it) },
//            )
//
//            Sliders.Slider(
//                labelPrefix = stringResource(resource = Res.string.cost),
//                labelPostfix = stringResource(resource = Res.string.pln),
//                minValue = COST_MIN_VALUE,
//                maxValue = COST_MAX_VALUE,
//                steps = COST_NUMBER_OF_STEPS,
//                selectedPosition = viewState.selectedCost.toFloat(),
//                onPositionChange = { changeCost(it) }
//            )
//
//            DatePickers.DatePickerWithTimer(
//                label = stringResource(resource = Res.string.start),
//                value = viewState.startDateTime,
//                onValueChange = {
//                    changeStartDateTime(it)
//                },
//            )
//
//            DatePickers.DatePickerWithTimer(
//                label = stringResource(resource = Res.string.end),
//                value = viewState.endDateTime,
//                onValueChange = {
//                    changeEndDateTime(it)
//                },
//            )
//
//            Spacers.Weight1(this)
//
//            Spacers.VerticalBetweenFields()
//
//            Buttons.PrimaryButton(
//                text = stringResource(resource = Res.string.submit),
//                onClick = submitSportEvent
//            )
        }
    }

    companion object {
        private const val PEOPLE_RANGE_MIN_VALUE = 1f
        private const val PEOPLE_RANGE_MAX_VALUE = 20f
        private const val PEOPLE_RANGE_NUMBER_OF_STEPS = 18
        private const val COST_MIN_VALUE = 100f
        private const val COST_MAX_VALUE = 300f
        private const val COST_NUMBER_OF_STEPS = 7
    }
}