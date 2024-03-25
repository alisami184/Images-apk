package com.example.galle

import com.example.galle.ImageData

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.galle.ui.theme.GalleTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.navArgument
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val imageViewModel: ImageViewModel = viewModel()
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "screen1") {
                        composable("screen1") { Screen1(navController,imageViewModel) }
                        composable(
                            route = "screen2/{Description}/{Titre}",
                            arguments = listOf(navArgument("Description") { type = NavType.StringType })
                        ) { backStackEntry ->
                            Screen2(
                                navController,
                                Description = backStackEntry.arguments?.getString("Description") ?: "",
                                Titre = backStackEntry.arguments?.getString("Titre") ?: ""
                            )
                        }
                    }
                }
            }
        }
    }
}

class ImageViewModel : ViewModel() {
    var imageIndex = mutableStateOf(0)
}

@Composable
fun Screen1(navController: NavHostController,imageViewModel: ImageViewModel) {

    val currentImage = ImageData.images[imageViewModel.imageIndex.value]

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    )
    {
        Spacer(modifier = Modifier.height(70.dp))

        Box(
            modifier = Modifier
                .width(450.dp)
                .height(500.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(width = 3.dp, color = Color.White)
                .background(Color.White)
                .padding(16.dp)
                .shadow(10.dp, RoundedCornerShape(15.dp)) // Ajouter une ombre pour l'effet 3D
        ) {

            Image(
                painter = painterResource(id = currentImage.resourceId),
                contentDescription = "Image",
                modifier = Modifier.fillMaxSize()
            )

        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .width(350.dp)
                .background(Color(0xFF6495ED).copy(alpha = 0.5f)) // Couleur bleue claire avec transparence
                .padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center, // Centrer verticalement le contenu
                horizontalAlignment = Alignment.CenterHorizontally, // Centrer horizontalement le contenu
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(
                    text = currentImage.author,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = currentImage.date,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = { imageViewModel.imageIndex.value = (imageViewModel.imageIndex.value - 1 + ImageData.images.size) % ImageData.images.size },
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text(
                    "Previous",
                    color = Color.Blue,
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            Button(
                onClick = { navController.navigate("screen2/${currentImage.description}/${currentImage.author}") },
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text(
                    "Details",
                    color = Color.Blue,
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            Button(
                onClick = { imageViewModel.imageIndex.value = (imageViewModel.imageIndex.value + 1) % ImageData.images.size },
                colors = ButtonDefaults.buttonColors(Color.Transparent)

            ) {
                Text(
                    "Next",
                    color = Color.Blue,
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

        }
    }
}

@Composable
fun Screen2(navController: NavHostController, Description: String, Titre: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text (
            text = Titre,
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )

        Spacer(modifier = Modifier.height(25.dp))

        Box(
            modifier = Modifier
                .height(250.dp)
                .width(350.dp)
                .background(Color(0xFF6495ED).copy(alpha = 0.5f)) // Couleur bleue claire avec transparence
                .padding(16.dp)
        )
        {
            Text(
                text = Description,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = { navController.navigate("screen1") }) {
            Text(
                "Go Back",
                color = Color.Blue,
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}
