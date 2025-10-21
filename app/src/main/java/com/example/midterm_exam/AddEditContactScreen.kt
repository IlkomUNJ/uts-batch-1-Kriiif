package com.example.midterm_exam

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditContactScreen(navController: NavHostController, isEdit: Boolean) {
    val contactIndex = ContactRepository.editingIndex
    val existing = if (isEdit && contactIndex != null)
        ContactRepository.contacts[contactIndex]
    else Contact("", "", "", "")

    var name by remember { mutableStateOf(TextFieldValue(existing.name)) }
    var address by remember { mutableStateOf(TextFieldValue(existing.address)) }
    var phone by remember { mutableStateOf(TextFieldValue(existing.phone)) }
    var email by remember { mutableStateOf(TextFieldValue(existing.email)) }
    var error by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isEdit) "Edit Contact" else "Add Contact",
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF083c5d)
                )
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address (min. 5 words)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            if (error.isNotEmpty()) {
                Text(error, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (address.text.trim().split(" ").size < 5) {
                        error = "Address must have at least 5 words."
                        return@Button
                    }

                    val newContact = Contact(
                        name.text,
                        address.text,
                        phone.text,
                        email.text
                    )

                    if (isEdit && contactIndex != null) {
                        ContactRepository.contacts[contactIndex] = newContact
                    } else {
                        ContactRepository.contacts.add(newContact)
                    }

                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1d2731),
                    contentColor = Color.White
                )
            ) {
                Text(if (isEdit) "Save Changes" else "Add Contact")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddEditContactScreen() {
    val navController = androidx.navigation.compose.rememberNavController()
    AddEditContactScreen(navController = navController, isEdit = false)
}
