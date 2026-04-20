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

class AuthViewModel : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var loginSuccess by mutableStateOf(false)

        fun onSignUp(onSuccess: () -> Unit) {
            // ... validation ...
            viewModelScope.launch {
                isLoading = true
                errorMessage = null
                try {
                    Log.d("SupabaseAuth", "Attempting Signup for: $email") // <--- LOGGING
                    SupabaseClient.client.auth.signUpWith(Email) {
                        email = this@AuthViewModel.email
                        password = this@AuthViewModel.password
                    }
                    Log.d("SupabaseAuth", "Signup Successful!") // <--- LOGGING
                    onSuccess()
                } catch (e: Exception) {
                    Log.e("SupabaseAuth", "Signup Failed: ${e.message}") // <--- LOGGING ERROR
                    errorMessage = e.message
                } finally {
                    isLoading = false
                }
            }
        }

        fun onLogin(onSuccess: () -> Unit) {
            // ... validation ...
            viewModelScope.launch {
                isLoading = true
                errorMessage = null
                try {
                    Log.d("SupabaseAuth", "Attempting Login for: $email") // <--- LOGGING
                    SupabaseClient.client.auth.signInWith(Email) {
                        email = this@AuthViewModel.email
                        password = this@AuthViewModel.password
                    }
                    Log.d("SupabaseAuth", "Login Successful!") // <--- LOGGING
                    loginSuccess = true
                    onSuccess()
                } catch (e: Exception) {
                    Log.e("SupabaseAuth", "Login Failed: ${e.message}") // <--- LOGGING ERROR
                    errorMessage = "Login failed: ${e.message}"
                } finally {
                    isLoading = false
                }
            }
        }
}