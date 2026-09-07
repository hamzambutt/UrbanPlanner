package com.SemiColon.urbanplanner.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.SemiColon.urbanplanner.SupabaseClient
import com.SemiColon.urbanplanner.network.ProfileRepository
import com.SemiColon.urbanplanner.network.models.ProfileResponse
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val repository: ProfileRepository = ProfileRepository()
) : ViewModel() {

    private val _profile = MutableStateFlow<ProfileResponse?>(null)
    val profile: StateFlow<ProfileResponse?> = _profile.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    val userEmail: String
        get() = SupabaseClient.client.auth.currentUserOrNull()?.email ?: "user@example.com"

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = withContext(Dispatchers.IO) {
                    repository.getMyProfile()
                }
                _profile.value = result
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun signOut(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    SupabaseClient.client.auth.signOut()
                }
            } catch (e: Exception) {
                // Ignore logout exceptions
            }
            onSuccess()
        }
    }
}
