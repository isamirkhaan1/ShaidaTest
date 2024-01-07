package com.example.shaidatest

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toFile
import androidx.lifecycle.viewmodel.compose.viewModel
import java.io.File


@Composable
fun HomeScreen(
    vm: HomeViewModel = viewModel()
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = stringResource(id = R.string.welcome))

        Spacer(modifier = Modifier.height(32.dp))

        // CSV file
        val csvFile = remember {
            mutableStateOf(File(""))
        }
        val csvFileSelectorLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) {
            if (it != null)
                csvFile.value = it.toFile()
        }
        TextButton(
            onClick = {
                csvFileSelectorLauncher.launch(emptyArray())
            },
            Modifier
                .padding(horizontal = 64.dp)
                .background(Color.LightGray)
        ) {
            Text(text = stringResource(id = R.string.choose_csv))
        }
        Text(text = csvFile.value.absolutePath, fontSize = 10.sp)


        Spacer(modifier = Modifier.height(8.dp))

        // Model file
        val modelFile = remember {
            mutableStateOf(File(""))
        }
        val modelFileSelectorLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) {
            if (it != null)
                csvFile.value = it.toFile()
        }
        TextButton(
            onClick = {
                modelFileSelectorLauncher.launch(emptyArray())
            },
            Modifier
                .padding(horizontal = 96.dp)
                .background(Color.LightGray)
        ) {
            Text(text = stringResource(id = R.string.choose_model))
        }
        Text(text = modelFile.value.absolutePath, fontSize = 10.sp)

        Spacer(modifier = Modifier.height(32.dp))


        val trainingResult = remember {
            mutableStateOf("")
        }

        // Submit button
        Button(modifier = Modifier.padding(horizontal = 96.dp), onClick = {
            trainingResult.value = "Took X seconds for Y samples"
        }) {
            Text(text = stringResource(id = R.string.test))
        }
        Text(text = trainingResult.value, fontSize = 10.sp)
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        MaterialTheme {
            HomeScreen()
        }
    }
}