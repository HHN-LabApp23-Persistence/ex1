package de.hhn.labapp.persistence.color_switcher.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.hhn.labapp.persistence.color_switcher.preferences_repository.SharedPreferencesRepository
import de.hhn.labapp.persistence.color_switcher.ui.theme.ColorPickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val preferencesRepository = remember {
                SharedPreferencesRepository(context = context)
            }

            ColorPickerTheme {
                ColorScreen(MainActivityViewModel(preferencesRepository))
            }
        }
    }
}

@Composable
fun ColorScreen(
    viewModel: MainActivityViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(modifier = Modifier.fillMaxSize(), color = uiState.backgroundColor) {
        Box(modifier = Modifier.clickable {
            viewModel.setNewColor()
        }, contentAlignment = Alignment.Center) {
            Text("Farbe: R:${uiState.backgroundColor.red} G:${uiState.backgroundColor.green} B:${uiState.backgroundColor.blue}")
        }
    }
}
