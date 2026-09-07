package com.SemiColon.urbanplanner.database

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.SemiColon.urbanplanner.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch
import android.util.Log
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class AuthViewModel : ViewModel() {

    var fullName by mutableStateOf("")
    var organizationName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var loginSuccess by mutableStateOf(false)

    fun onSignUp(onSuccess: () -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Please enter both email and password."
            return
        }
        if (password.length < 6) {
            errorMessage = "Password must be at least 6 characters long."
            return
        }
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                Log.d("SupabaseAuth", "Attempting Signup for: $email")
                val metadata = buildJsonObject {
                    if (fullName.isNotBlank()) put("full_name", fullName.trim())
                    if (organizationName.isNotBlank()) put("organization_name", organizationName.trim())
                    put("role", "b2c")
                }
                SupabaseClient.client.auth.signUpWith(Email) {
                    email = this@AuthViewModel.email.trim()
                    password = this@AuthViewModel.password
                    data = metadata
                }
                Log.d("SupabaseAuth", "Signup Successful!")
                onSuccess()
            } catch (e: Exception) {
                Log.e("SupabaseAuth", "Signup Failed: ${e.message}")
                errorMessage = e.message ?: "An unexpected error occurred during signup."
            } finally {
                isLoading = false
            }
        }
    }

    fun onLogin(onSuccess: () -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Please enter both email and password."
            return
        }
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                Log.d("SupabaseAuth", "Attempting Login for: $email")
                SupabaseClient.client.auth.signInWith(Email) {
                    email = this@AuthViewModel.email.trim()
                    password = this@AuthViewModel.password
                }
                Log.d("SupabaseAuth", "Login Successful!")
                loginSuccess = true
                onSuccess()
            } catch (e: Exception) {
                Log.e("SupabaseAuth", "Login Failed: ${e.message}")
                errorMessage = "Login failed: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}
