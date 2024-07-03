package com.mridhop.easppb.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mridhop.easppb.R
import com.mridhop.easppb.model.UserProfile
import kotlinx.serialization.json.Json

@Composable
fun RegisterScreen(navController: NavController, phoneNumber: String) {
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var job by rememberSaveable { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    var isChecked by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF3E9EED))
    ) {
        Image(
            painter = painterResource(id = R.drawable.isaku_logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 56.dp)
                .size(100.dp)
        )

        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 552.dp, max = 700.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            .align(Alignment.BottomCenter)
        ) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .padding(bottom = 12.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Daftar", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(48.dp))
                Text(text = "Nama", fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text(text = "Masukkan nama lengkap Anda")},
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusRequester.requestFocus() }
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(36.dp))
                Text(text = "Nomor Telepon", fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = {  },
                    label = { Text(text = "Masukkan nomor telepon")},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(36.dp))
                Text(text = "Email", fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Masukkan email Anda")},
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                )
                Spacer(modifier = Modifier.height(36.dp))
                Text(text = "Pekerjaan", fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = job,
                    onValueChange = { job = it },
                    label = { Text(text = "Masukkan pekerjaan Anda")},
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(36.dp))
                Row {
                    CustomCheckbox(
                        checked = isChecked,
                        onCheckedChange = { newValue -> isChecked = newValue },
                    )
                    Text(text = "Saya menyetujui peraturan kebijakan aplikasi i.saku yang berlaku",
                        modifier = Modifier.padding(start = 12.dp))
                }
                Spacer(modifier = Modifier.height(36.dp))
                Button(
                    onClick = {
                        val userProfile = UserProfile(
                            phoneNumber = phoneNumber,
                            fullName = fullName,
                            email = email,
                            job = job,
                            password = ""
                        )
                        val userProfileJson = Json.encodeToString(
                            UserProfile.serializer(),
                            userProfile
                        )
                        navController.navigate("register_password/$userProfileJson")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3E9EED)),
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Lanjutkan", color = Color.White)
                }
            }
        }
    }

}

@Composable
fun CustomCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: CheckboxColors = CheckboxDefaults.colors()
) {
    TriStateCheckbox(
        state = ToggleableState(checked),
        onClick = null,
        interactionSource = interactionSource,
        enabled = enabled,
        colors = colors,
        modifier = modifier.triStateToggleable(
            state = ToggleableState(checked),
            onClick = {
                if (onCheckedChange != null) {
                    run { onCheckedChange(!checked) }
                }
            },
            enabled = enabled,
            role = Role.Checkbox,
            interactionSource = interactionSource,
            indication = null
        )
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController(), "")
}