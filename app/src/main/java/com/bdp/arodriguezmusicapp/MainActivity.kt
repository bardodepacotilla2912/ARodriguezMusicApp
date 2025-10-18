  package com.bdp.arodriguezmusicapp

  import android.os.Bundle
  import androidx.activity.ComponentActivity
  import androidx.activity.compose.setContent
  import androidx.compose.material3.MaterialTheme
  import androidx.compose.material3.darkColorScheme
  import androidx.compose.runtime.Composable
  import androidx.compose.ui.graphics.Color

  class MainActivity : ComponentActivity() {
      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContent {
              AppTheme {
                  AppNavHost()
              }
          }
      }
  }

  @Composable
  fun AppTheme(content: @Composable () -> Unit) {
      MaterialTheme(
          colorScheme = darkColorScheme(
              primary = Color(0xFF7E57C2),
              secondary = Color(0xFFB388FF),
              background = Color(0xFFF6F2FF),
              surface = Color.White
          ),
          content = content
      )
  }