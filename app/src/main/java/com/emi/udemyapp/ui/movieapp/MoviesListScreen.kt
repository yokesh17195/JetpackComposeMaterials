package com.emi.udemyapp.ui.movieapp

import android.view.Surface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.emi.udemyapp.ui.navigation.Routes

@Composable
fun MoviesListScreen(navController: NavController){
    val moviesList = listOf("Avatar",
        "Batman",
        "Curious Case of Benjamin Button",
        "Dark Knight",
        "Eragon",
        "Fast and Furious",
        "Groot",
        "Harry Potter",
        "Incredibles",
        "Joker",
        "Kite Runner",
        "Lazarus")

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        Row(modifier = Modifier.fillMaxWidth().height(30.dp).background(color = Color.Black),
            verticalAlignment = Alignment.CenterVertically) {
            Text(modifier = Modifier.padding(start = 10.dp),
                text = "Movies",
                color = Color.White)
        }
    }) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues).background(color = Color.White)){
            LazyColumn(modifier = Modifier.fillMaxSize().background(color = Color.White)) {
                items(moviesList){ movie ->
                    MovieItem(movie){  movie->
                        navController.navigate(Routes.MovieDetails.route+"/$movie")
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(movieName:String,onClick: (String)-> Unit){
    Card(onClick = { onClick(movieName) }, modifier = Modifier.fillMaxWidth().height(100.dp).padding(10.dp), elevation = CardDefaults.elevatedCardElevation(10.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(modifier = Modifier.fillMaxHeight().padding(start = 10.dp), verticalArrangement = Arrangement.Center) {
            Text(text = movieName, color = Color.Black, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MoviesListPreview(){
    MoviesListScreen(rememberNavController())
}