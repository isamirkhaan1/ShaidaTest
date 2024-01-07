package com.example.shaidatest

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                MaterialTheme {
                    val permission = rememberMultiplePermissionsState(
                        permissions = listOf(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                        )
                    )

                    val showHomeScreen = remember { mutableStateOf(false) }
                    if (showHomeScreen.value) {
                        HomeScreen()
                    }

                    LaunchedEffect(true) {
                        if (!permission.allPermissionsGranted) {
                            permission.launchMultiplePermissionRequest()
                        } else {
                            showHomeScreen.value = true
                        }
                    }
                }
            }
        }
    }
}