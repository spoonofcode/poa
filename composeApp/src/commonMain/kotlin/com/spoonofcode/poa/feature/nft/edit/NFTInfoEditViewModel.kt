package com.spoonofcode.poa.feature.nft.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.ui.BaseViewModel
import com.spoonofcode.poa.core.ui.SnackbarEvent
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlin.coroutines.cancellation.CancellationException

internal class NFTInfoEditViewModel(
//    private val createSportEventUseCase: CreateSportEventUseCase,
//    private val editSportEventUseCase: EditSportEventUseCase,
//    private val loadSportEventFormDataUseCase: LoadSportEventFormDataUseCase,
) : BaseViewModel<NFTInfoEditViewState>(NFTInfoEditViewState()) {

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
//                val sportEventFormData = loadSportEventFormDataUseCase()
//                updateState {
//                    copy(
//                        isLoadingView = false,
//                        screenMode = screenMode,
//                        clubs = sportEventFormData.clubs.associate { it.id to it.name },
//                        rooms = sportEventFormData.rooms.associate { it.id to it.name },
//                        levels = sportEventFormData.levels.associate { it.id to it.name },
//                        types = sportEventFormData.types.associate { it.id to it.name },
//                        selectedClubId = sportEventFormData.sportEvent.club.id,
//                        selectedRoomId = sportEventFormData.sportEvent.room.id,
//                        selectedLevelId = sportEventFormData.sportEvent.level.id,
//                        selectedTypeId = sportEventFormData.sportEvent.type.id,
//                        title = sportEventFormData.sportEvent.title,
//                        description = sportEventFormData.sportEvent.description,
//                        selectedMinNumberOfPeople = sportEventFormData.sportEvent.minNumberOfPeople,
//                        selectedMaxNumberOfPeople = sportEventFormData.sportEvent.maxNumberOfPeople,
//                        cost = sportEventFormData.sportEvent.cost.toInt(),
//                        startDateTime = sportEventFormData.sportEvent.startDateTime,
//                        endDateTime = sportEventFormData.sportEvent.endDateTime,
//                    )
//                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun changeTitle(title: String) {
        viewModelScope.launch {
            updateState {
                copy(title = title)
            }
        }
    }

    fun changeDescription(description: String) {
        viewModelScope.launch {
            updateState {
                copy(description = description)
            }
        }
    }

    fun changeClub(selectedClubId: Int) {
        viewModelScope.launch {
            updateState {
                copy(selectedClubId = selectedClubId)
            }
        }
    }

    fun changeRoom(selectedRoomId: Int) {
        viewModelScope.launch {
            updateState {
                copy(selectedRoomId = selectedRoomId)
            }
        }
    }

    fun changeLevel(selectedLevelId: Int) {
        viewModelScope.launch {
            updateState {
                copy(selectedLevelId = selectedLevelId)
            }
        }
    }

    fun changeType(selectedTypeId: Int) {
        viewModelScope.launch {
            updateState {
                copy(selectedTypeId = selectedTypeId)
            }
        }
    }

    fun changeMinNumberOfPeople(selectedMinNumberOfPeople: Int) {
        viewModelScope.launch {
            updateState {
                copy(selectedMinNumberOfPeople = selectedMinNumberOfPeople)
            }
        }
    }

    fun changeMaxNumberOfPeople(selectedMaxNumberOfPeople: Int) {
        viewModelScope.launch {
            updateState {
                copy(selectedMaxNumberOfPeople = selectedMaxNumberOfPeople)
            }
        }
    }

    fun changeCost(cost: Int) {
        viewModelScope.launch {
            updateState {
                copy(cost = cost)
            }
        }
    }

    fun changeStartDateTime(startDateTime: LocalDateTime) {
        viewModelScope.launch {
            updateState {
                copy(startDateTime = startDateTime)
            }
        }
    }

    fun changeEndDateTime(endDateTime: LocalDateTime) {
        viewModelScope.launch {
            updateState {
                copy(endDateTime = endDateTime)
            }
        }
    }

    fun submitSportEvent() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val currentState = currentState()
//                editSportEventUseCase(
//                    sportEventId = currentState.screenMode.sportEventId,
//                    title = currentState.title,
//                    description = currentState.description,
//                    minNumberOfPeople = currentState.selectedMinNumberOfPeople,
//                    maxNumberOfPeople = currentState.selectedMaxNumberOfPeople,
//                    cost = currentState.cost,
//                    startDateTime = currentState.startDateTime,
//                    endDateTime = currentState.endDateTime,
//                    selectedClubId = currentState.selectedClubId!!,
//                    selectedRoomId = currentState.selectedRoomId!!,
//                    selectedLevelId = currentState.selectedLevelId!!,
//                    selectedTypeId = currentState.selectedTypeId!!,
//                )
                viewModelNavigator.pop()
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorSnackbar(e)
            }
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(
                isLoadingView = true,
                isErrorView = false,
            )
        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                isLoadingView = false,
                isErrorView = true,
            )
        }
    }

    private fun showErrorSnackbar(e: Exception) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        updateState {
            copy(
                isLoadingView = false
            )
        }
    }
}