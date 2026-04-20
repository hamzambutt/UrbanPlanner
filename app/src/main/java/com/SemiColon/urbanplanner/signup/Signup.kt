package com.SemiColon.urbanplanner.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
// 1. Add Import
import androidx.lifecycle.viewmodel.compose.viewModel
import com.SemiColon.urbanplanner.database.AuthViewModel

@Composable
fun SignupScreen(
    onNavigateToLogin: () -> Unit = {},
    // 2. Inject ViewModel
    viewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current

    // We keep confirmPassword local because it's just for UI validation
    var confirmPassword by remember { mutableStateOf("") }

    if (viewModel.errorMessage != null) {
        Toast.makeText(context, viewModel.errorMessage, Toast.LENGTH_LONG).show()
        viewModel.errorMessage = null
    }

    Column(modifier= Modifier
        .fillMaxSize()
        .background(Color.White),
        verticalArrangement = Arrangement.Center
    ){
        Card(modifier= Modifier
            .fillMaxWidth()
            .height(450.dp)
            .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Gray)
        )
        {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // --- Email ---
                OutlinedTextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.email = it },
                    label = { Text("Email") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // --- Password ---
                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // --- Confirm Password ---
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    isError = viewModel.password.isNotEmpty() && confirmPassword.isNotEmpty() && viewModel.password != confirmPassword,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                // --- Signup Button ---
                Button(
                    onClick = {
                        // 3. UI Validation logic first
                        if (viewModel.password != confirmPassword) {
                            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                        } else {
                            // 4. Call ViewModel function
                            viewModel.onSignUp(onSuccess = {
                                Toast.makeText(context, "Account Created!", Toast.LENGTH_SHORT).show()
                                onNavigateToLogin()
                            })
                        }
                    },
                    modifier = Modifier.width(200.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    ),
                    enabled = !viewModel.isLoading
                ) {
                    if (viewModel.isLoading) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
                    } else {
                        Text(text = "Signup")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Already have an account? Login",
                    color = Color.White,
                    modifier = Modifier
                        .clickable { onNavigateToLogin() }
                        .padding(8.dp)
                )
            }
        }
    }
}