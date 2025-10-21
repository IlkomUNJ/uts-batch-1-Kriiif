package com.example.midterm_exam

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

data class Contact(
    var name: String,
    var address: String,
    var phone: String,
    var email: String
)

class ContactRepository {
    companion object {
        val contacts = mutableStateListOf<Contact>()
        var editingIndex: Int? = null
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListContactScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF083c5d))
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    ContactRepository.editingIndex = null
                    navController.navigate("add")
                },
                containerColor = Color(0xFF1d2731),
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Contact")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(ContactRepository.contacts) { contact ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            ContactRepository.editingIndex =
                                ContactRepository.contacts.indexOf(contact)
                            navController.navigate("edit")
                        }
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text("Name: ${contact.name}")
                        Text("Address: ${contact.address}")
                        Text("Phone: ${contact.phone}")
                        Text("Email: ${contact.email}")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListContactScreen() {
    val navController = androidx.navigation.compose.rememberNavController()
    ListContactScreen(navController = navController)
}