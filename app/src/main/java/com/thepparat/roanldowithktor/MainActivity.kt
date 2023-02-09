package com.thepparat.roanldowithktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.thepparat.roanldowithktor.getRonaldo.MainViewModel
import com.thepparat.roanldowithktor.ui.theme.RoanldowithKtorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoanldowithKtorTheme {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    val viewModel: MainViewModel by viewModels()
                    val ronaldoState = viewModel.state.value.ronaldo
                    val isLoading = viewModel.state.value.isLoading

                    ronaldoState?.let { ronaldo ->
                        val painter =
                            rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current)
                                .data(ronaldo.imageUrl).crossfade(true).build())

                        Card(shape = RoundedCornerShape(4.dp), elevation = 4.dp) {
                            Image(
                                painter = painter,
                                contentDescription = ronaldo.description,
                                modifier = Modifier
                                    .size(250.dp)
                                    .aspectRatio(2f)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = ronaldo.name, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = ronaldo.description)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = viewModel::getRandomRonaldo,
                            modifier = Modifier.align(Alignment.End)) {
                            Text(text = "Next !")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        if (isLoading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}