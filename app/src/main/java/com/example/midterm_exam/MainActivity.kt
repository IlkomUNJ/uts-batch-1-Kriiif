package com.example.midterm_exam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ContactApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "list") {
        composable("list") { ListContactScreen(navController) }
        composable("add") { AddEditContactScreen(navController, isEdit = false) }
        composable("edit") { AddEditContactScreen(navController, isEdit = true) }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContactApp() {
    ContactApp()
}