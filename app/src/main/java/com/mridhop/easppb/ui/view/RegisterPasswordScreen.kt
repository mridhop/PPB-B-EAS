package com.mridhop.easppb.ui.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mridhop.easppb.R
import com.mridhop.easppb.model.UserProfile
import com.mridhop.easppb.ui.viewmodel.UserViewModel
import com.mridhop.easppb.util.ViewModelUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

@Composable
fun RegisterPasswordScreen(navController: NavController, userProfileJson: String) {
    var password by remember {
        mutableStateOf("")
    }
    val userProfile = Json.decodeFromString<UserProfile>(userProfileJson)
    var isProcessing by remember { mutableStateOf(false) }
    val phoneNumber: String = userProfile.phoneNumber
    val context = LocalContext.current
    val userViewModel: UserViewModel = ViewModelUtil.getUserViewModel(context = context)

    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(552.dp)
                .background(
                    color = Color(0xFF3E9EED),
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                )
        )
        Text(
            text = "Selamat datang di",
            fontSize = 28.sp,
            fontWeight = FontWeight.Light,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 240.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.isaku_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp)
                .padding(top = 260.dp)
                .size(100.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 200.dp)
                .height(300.dp)
                .shadow(8.dp, RoundedCornerShape(16.dp))
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Buat password untuk akun Anda",
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Text(text = "Password", fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Masukkan password") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (password.isEmpty()) {
                            Toast.makeText(
                                context,
                                "Password tidak boleh kosong",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            isProcessing = true
                            // Launch a coroutine to perform database operations and UI updates
                            userViewModel.viewModelScope.launch {
                                try {
                                    val completeUserProfile = userProfile.copy(password = password)
                                    userViewModel.insertUserProfile(completeUserProfile)
                                    userViewModel.loginUser(phoneNumber, password, navController, context)
                                } catch (e: Exception) {
                                    withContext(Dispatchers.Main) {
                                        Toast.makeText(
                                            context,
                                            "Failed to register or login: ${e.localizedMessage}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } finally {
                                    isProcessing = false
                                }
                            }
                        }
                    },
                    enabled = !isProcessing,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3E9EED)),
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Daftar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPasswordScreenPreview() {
    RegisterPasswordScreen(
        rememberNavController(),
        ""
    )
}