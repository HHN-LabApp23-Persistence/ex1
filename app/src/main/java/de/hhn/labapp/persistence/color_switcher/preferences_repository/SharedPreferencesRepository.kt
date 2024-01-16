package de.hhn.labapp.persistence.color_switcher.preferences_repository

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SharedPreferencesRepository(context:Context) {
    private val keyAlias = MasterKeys.getOrCreate(
        MasterKeys.AES256_GCM_SPEC
    )

    private val sharedPreferences = EncryptedSharedPreferences.create(
        "color",
        keyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun loadColor(): Color {
        val color = sharedPreferences.getInt("color", 0)
        return Color(color)
    }

    fun saveColor(color: Color) {
        sharedPreferences.edit().putInt("color", color.toArgb()).apply()
    }
}