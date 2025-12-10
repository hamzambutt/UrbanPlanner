package com.SemiColon.urbanplanner.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier= Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color.White),
        verticalArrangement = Arrangement.Center
    ){
        Card(modifier= Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp),
            elevation = CardDefaults.cardElevation(10.dp), // (Uncomment for Material 3)
            colors = CardDefaults.cardColors(containerColor = Color.Red)
        )
        {
            // 2. Use a Column here to stack items vertically
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp), // Add padding inside the card
                horizontalAlignment = Alignment.CenterHorizontally, // Center items horizontally
                verticalArrangement = Arrangement.Center // Center items vertically
            ) {
                // --- Email Field ---
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp)) // Gap between fields

                // --- Password Field ---
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(), // Hides the password characters
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp)) // Gap before button
            Box(
                modifier = Modifier.fillMaxSize(), // Fixed typo here
                contentAlignment = Alignment.Center
            )
            {
                Button(
                    onClick = { Unit },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue, // Background color
                        contentColor = Color.White   // <--- Text Color changes here
                    ),
                    modifier = Modifier.width(200.dp)
                ) {
                    Text(text = "Login")
                }
            }
        }
            Text(text = "Forgot Password?",
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
    }
}

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}