package com.example.jetpacktestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktestapp.ui.theme.JetpackTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTestAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    StudentProfileScreen()
                }
            }
        }
    }
}

@Composable
fun StudentProfileScreen() {

    val studentName = "FelixTEK"
    val studentId = "67054227"
    val major = "Computer Science"
    val faculty = "School of Science"
    val email = "67054227@kmitl.ac.th"
    val phone = "098-765-4321"
    val address = "1 Chalong Krung Road, Khwaeng Lat Krabang, Khet Lat Krabang, Bangkok 10520"
    var showInfo by remember { mutableStateOf(false) }
    var isFavorite by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "My Student Profile",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Image(
                    painter = painterResource(id = R.drawable.student_profile),
                    contentDescription = "Student Profile",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = studentName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = major,
                    fontSize = 15.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))
                Divider()
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Student ID: ",
                        fontWeight = FontWeight.Medium
                    )
                    Text(text = studentId)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        showInfo = !showInfo
                        println("Button clicked, showInfo = $showInfo")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (showInfo) "HIDE MY INFO" else "SHOW MY INFO"
                    )
                }

                if (showInfo) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "About Me",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        InfoLine(label = "Faculty", value = faculty)
                        InfoLine(label = "Email", value = email)
                        InfoLine(label = "Phone", value = phone)
                        InfoLine(label = "Address", value = address)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (isFavorite) "★ Favorite" else "☆ Add Favorite"
                    )
                }
            }
        }
    }
}

@Composable
fun InfoLine(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(
            text = "$label: ",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StudentProfilePreview() {
    JetpackTestAppTheme {
        StudentProfileScreen()
    }
}