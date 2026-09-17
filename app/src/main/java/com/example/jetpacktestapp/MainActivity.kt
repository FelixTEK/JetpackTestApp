package com.example.jetpacktestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpacktestapp.ui.theme.JetpackTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTestAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    StudentApp()
                }
            }
        }
    }
}

@Composable
fun StudentApp() {
    val navController = rememberNavController()

    // 2) EditText -> TextField
    var studentName by remember { mutableStateOf("") }
    var studentId by remember { mutableStateOf("") }

    // 7-8) RadioButton + RadioGroup (Degree)
    var selectedDegree by remember { mutableStateOf("Bachelor") }

    // 5) CheckBox (Skills)
    var usePython by remember { mutableStateOf(false) }
    var useKotlin by remember { mutableStateOf(false) }
    var useJava by remember { mutableStateOf(false) }

    // 6) ToggleButton (Favorite)
    var isFavorite by remember { mutableStateOf(false) }

    // 9) Switch (Notifications)
    var notificationEnabled by remember { mutableStateOf(true) }

    // 10) SeekBar -> Slider (Interest Level)
    var interestLevel by remember { mutableStateOf(50f) }

    // 11) ProgressBar -> Progress Indicator (Profile Completion)
    val profileProgress = 0.75f

    // 12) Button + Event -> State -> Navigate
    var registered by remember { mutableStateOf(false) }

    NavHost(navController = navController, startDestination = "register") {
        composable("register") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // 1) TextView -> Text
                Text(text = "Student Registration")

                // 3) ImageView -> Image
                Image(
                    painter = painterResource(id = R.drawable.student_profile),
                    contentDescription = "Student Photo",
                    modifier = Modifier.size(120.dp)
                )

                // 4) ImageButton -> IconButton
                IconButton(onClick = { println("Edit profile") }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                }

                // 2) EditText -> TextField
                TextField(
                    value = studentName,
                    onValueChange = { studentName = it },
                    label = { Text("Enter your name") }
                )
                Text(text = "Hello $studentName")

                TextField(
                    value = studentId,
                    onValueChange = { studentId = it },
                    label = { Text("Student ID") }
                )

                // 7-8) RadioButton + RadioGroup
                Text(text = "Degree")
                Row {
                    RadioButton(
                        selected = selectedDegree == "Bachelor",
                        onClick = { selectedDegree = "Bachelor" }
                    )
                    Text("Bachelor")
                    RadioButton(
                        selected = selectedDegree == "Master",
                        onClick = { selectedDegree = "Master" }
                    )
                    Text("Master")
                }

                // 5) CheckBox
                Row {
                    Checkbox(checked = usePython, onCheckedChange = { usePython = it })
                    Text("Python")
                }
                Row {
                    Checkbox(checked = useKotlin, onCheckedChange = { useKotlin = it })
                    Text("Kotlin")
                }
                Row {
                    Checkbox(checked = useJava, onCheckedChange = { useJava = it })
                    Text("Java")
                }

                // 6) ToggleButton
                IconToggleButton(checked = isFavorite, onCheckedChange = { isFavorite = it }) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite")
                }

                // 9) Switch
                Text(text = "Notifications")
                Switch(checked = notificationEnabled, onCheckedChange = { notificationEnabled = it })

                // 10) SeekBar -> Slider
                Slider(
                    value = interestLevel,
                    onValueChange = { interestLevel = it },
                    valueRange = 0f..100f
                )
                Text(text = "Interest Level: ${interestLevel.toInt()}%")

                // 11) ProgressBar -> Progress Indicator
                LinearProgressIndicator(
                    progress = { profileProgress },
                    modifier = Modifier.fillMaxWidth()
                )
                Text("Profile Completion: 75%")

                // 12) Button + Event
                Button(onClick = {
                    registered = true
                    navController.navigate("profile")
                }) {
                    Text("REGISTER")
                }
            }
        }

        composable("profile") {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(text = "My Student Profile")
                Text(text = "Hello $studentName")
                Text(text = "ID: $studentId")
                Text(text = "Degree: $selectedDegree")
                Text(text = "Notifications: ${if (notificationEnabled) "ON" else "OFF"}")
            }
        }
    }
}
