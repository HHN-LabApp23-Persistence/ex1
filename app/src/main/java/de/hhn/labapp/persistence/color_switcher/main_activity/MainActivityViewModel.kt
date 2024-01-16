package de.hhn.labapp.persistence.color_switcher.main_activity

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import de.hhn.labapp.persistence.color_switcher.helper.ColorHelper
import de.hhn.labapp.persistence.color_switcher.preferences_repository.SharedPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivityViewModel(
    private val preferencesRepository: SharedPreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainActivityState(Color(0)))
    val uiState: StateFlow<MainActivityState>
        get() = _uiState

    init {
        _uiState.value = _uiState.value.copy(backgroundColor = preferencesRepository.loadColor())
    }

    fun setNewColor(){
        val color = ColorHelper.getRandomColor()
        _uiState.value = _uiState.value.copy(backgroundColor = color)
        preferencesRepository.saveColor(color)
    }

}