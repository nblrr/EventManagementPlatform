package com.eventmanagement

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri

data class MemberData(
    val nim: String,
    val nama: String,
    val jurusan: String,
    val angkatan: String,
    val deskripsi: String,
    val github: String
)

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nim = intent.getStringArrayExtra("NIM_LIST") ?: arrayOf()
        val nama = intent.getStringArrayExtra("NAMA_LIST") ?: arrayOf()
        val jurusan = intent.getStringArrayExtra("JURUSAN_LIST") ?: arrayOf()
        val angkatan = intent.getStringArrayExtra("ANGKATAN_LIST") ?: arrayOf()
        val deskripsi = intent.getStringArrayExtra("DESKRIPSI_LIST") ?: arrayOf()
        val github = intent.getStringArrayExtra("GITHUB_LIST") ?: arrayOf()

        val members = nim.indices.map {
            MemberData(nim[it], nama[it], jurusan[it], angkatan[it], deskripsi[it], github[it])
        }

        setContent {
            ProfileScreen(members) { finish() }
        }
    }
}

@Composable
fun ProfileScreen(members: List<MemberData>, onBack: () -> Unit) {

    var selected by remember { mutableIntStateOf(0) }
    val member = members[selected]
    val context = androidx.compose.ui.platform.LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Halaman Profile", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(member.nama.take(1), fontSize = 30.sp)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("NIM: ${member.nim}")
        Text("Nama: ${member.nama}")
        Text("Jurusan: ${member.jurusan}")
        Text("Angkatan: ${member.angkatan}")

        Spacer(modifier = Modifier.height(10.dp))

        Text(member.deskripsi)

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Lihat GitHub",
            color = Color.Blue,
            modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW, member.github.toUri())
                context.startActivity(intent)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Pilih Anggota:")

        Spacer(modifier = Modifier.height(10.dp))

        members.forEachIndexed { index, m ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { selected = index },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (selected == index) Color(0xFFBBDEFB) else Color.White
                )
            ) {
                Text(
                    m.nama,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "${member.nama} (${member.nim})")
                context.startActivity(Intent.createChooser(intent, "Share"))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Share")
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = { onBack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}