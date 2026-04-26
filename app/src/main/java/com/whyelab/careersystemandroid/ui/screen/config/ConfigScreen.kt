package com.whyelab.careersystemandroid.ui.screen.config

import com.whyelab.careersystemandroid.data.local.ConfigManager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ConfigScreen(onSave: () -> Unit) {
    val context = LocalContext.current
    val configManager = remember { ConfigManager(context) }

    var ip by remember { mutableStateOf("") }
    var port by remember { mutableStateOf("8080") }

    Column(modifier = Modifier.padding(16.dp)) {

        Text("Enter Backend Config", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = ip,
            onValueChange = { ip = it },
            label = { Text("IP Address") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = port,
            onValueChange = { port = it },
            label = { Text("Port") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            configManager.saveBaseUrl(ip, port)
            onSave()
        }) {
            Text("Save & Continue")
        }
    }
}