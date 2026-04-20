package com.SemiColon.urbanplanner.login

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
// ViewModel tools
import androidx.lifecycle.viewmodel.compose.viewModel
import com.SemiColon.urbanplanner.database.AuthViewModel

@Composable
fun LoginScreen(
    onNavigateToSignup: () -> Unit = {},
    onNavigateToDashboard: () -> Unit = {},
    // 2. Inject the ViewModel here
    viewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current

    // Observe error messages from ViewModel
    if (viewModel.errorMessage != null) {
        Toast.makeText(context, viewModel.errorMessage, Toast.LENGTH_LONG).show()
        viewModel.errorMessage = null // Reset so it doesn't show twice
    }

    // Check if login was successful
    if (viewModel.loginSuccess) {
        // Reset state to avoid loops if user comes back
        viewModel.loginSuccess = false
        onNavigateToDashboard()
    }

    Column(modifier= Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color.White),
        verticalArrangement = Arrangement.Center
    ){
        Card(modifier= Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Gray)
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // --- Email Field ---
                OutlinedTextField(
                    // 3. Use ViewModel variable
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

                // --- Password Field ---
                OutlinedTextField(
                    // 4. Use ViewModel variable
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

                Spacer(modifier = Modifier.height(24.dp))

                // --- Login Button ---
                Button(
                    onClick = {
                        // 5. Call the ViewModel function
                        viewModel.onLogin(onSuccess = {
                            Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()
                            onNavigateToDashboard()
                        })
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.width(200.dp),
                    // 6. Disable button while loading
                    enabled = !viewModel.isLoading
                ) {
                    if (viewModel.isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Text(text = "Login")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // --- Sign Up Link ---
                Text(
                    text = "Don't have an account? Sign Up",
                    color = Color.White,
                    modifier = Modifier
                        .clickable { onNavigateToSignup() }
                        .padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}