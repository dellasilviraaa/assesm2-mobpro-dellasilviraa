package com.dellas0095.assesmobpro2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dellas0095.assesmobpro2.screen.MainScreen
import com.dellas0095.assesmobpro2.ui.theme.Assesmobpro2Theme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assesmobpro2Theme {
                MainScreen()
            }
        }
    }
}
