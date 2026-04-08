package com.eventmanagement

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { HomeScreen() }
    }
}

@Composable
fun HomeScreen() {
    val context = androidx.compose.ui.platform.LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Halaman Utama", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "event gathering",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)) {

                Text("Community Event Management Platform")

                Spacer(modifier = Modifier.height(10.dp))

                Text("Platform untuk mengelola event komunitas secara terpusat.")

                Spacer(modifier = Modifier.height(12.dp))

                Text("Fitur:")
                Text("• Event Management")
                Text("• Join Community")
                Text("• Trusted Organizer")
                Text("• Share Profile")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                val intent = Intent(context, ProfileActivity::class.java)

                intent.putExtra("NIM_LIST", arrayOf(
                    "L0124065",
                    "L0124066",
                    "L0124070",
                    "L0124073"))
                intent.putExtra("NAMA_LIST", arrayOf(
                    "Muhammad Rafi Raihan Sonjaya",
                    "Nabil Rizki Wardana",
                    "Raditya Adi Pradana",
                    "Reyhan Mufid Abiyyu"))
                intent.putExtra("JURUSAN_LIST", arrayOf("Informatika",
                    "Informatika",
                    "Informatika",
                    "Informatika"))
                intent.putExtra("ANGKATAN_LIST", arrayOf("2024",
                    "2024",
                    "2024",
                    "2024"))
                intent.putExtra("DESKRIPSI_LIST", arrayOf(
                    ".",
                    ".",
                    ".",
                    "."
                ))

                intent.putExtra("GITHUB_LIST", arrayOf(
                    "https://github.com/rafiras",
                    "https://github.com/nblrr",
                    "https://github.com/reyhanmufiid",
                    "https://github.com/"
                ))

                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Group, contentDescription = "")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Go To Profile")
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    "https://github.com/nblrr/EventManagementPlatform".toUri()
                )
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Link, contentDescription = "")
            Spacer(modifier = Modifier.width(8.dp))
            Text("GitHub")
        }
    }
}