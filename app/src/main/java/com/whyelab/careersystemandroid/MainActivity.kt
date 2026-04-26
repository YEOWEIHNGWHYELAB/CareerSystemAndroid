package com.whyelab.careersystemandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.whyelab.careersystemandroid.navigation.NavGraph
import com.whyelab.careersystemandroid.ui.theme.CareerSystemAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CareerSystemAndroidTheme {
                NavGraph()
            }
        }
    }
}