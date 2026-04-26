package com.whyelab.careersystemandroid.ui.screen.config

import com.whyelab.careersystemandroid.data.local.ConfigManager
import com.whyelab.careersystemandroid.util.isValidIp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ConfigScreen(navController: NavController, onSave: () -> Unit) {
    val context = LocalContext.current
    val configManager = remember { ConfigManager(context) }

    var ip by remember { mutableStateOf("") }
    var port by remember { mutableStateOf("8080") }
    var ipError by remember { mutableStateOf<String?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(16.dp).statusBarsPadding()) {

            Text(
                "Enter Backend Config",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = ip,
                onValueChange = {
                    ip = it
                    ipError = null
                },
                label = { Text("IP Address") },
                isError = ipError != null,
                supportingText = {
                    if (ipError != null) {
                        Text(
                            text = ipError!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = port,
                onValueChange = { input ->
                    port = input.filter { it.isDigit() }
                },
                label = { Text("Port") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Text("Back")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    val resultIp = ip.trim()
                    val resultPort = port.trim()

                    if (!isValidIp(resultIp)) {
                        ipError = "Invalid IP address"
                        return@Button
                    }

                    configManager.saveBaseUrl(resultIp, resultPort)
                    onSave()
                }) {
                    Text("Save & Continue")
                }
            }
        }
    }
}