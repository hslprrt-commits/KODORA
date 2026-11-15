package com.kodora.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF09090F)
private val SurfaceDark = Color(0xFF15131F)
private val Purple = Color(0xFF9B5CFF)
private val TextWhite = Color(0xFFF5F2FA)
private val TextGray = Color(0xFFAAA4B8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                KodoraApp()
            }
        }
    }
}

@Composable
fun KodoraApp() {
    var currentPage by remember { mutableStateOf("Home") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        when (currentPage) {
            "Home" -> HomeScreen(
                onNavigate = { currentPage = it }
            )

            "Projects" -> SimplePage(
                title = "My Projects",
                description = "Your Android projects will appear here.",
                onBack = { currentPage = "Home" }
            )

            "Templates" -> SimplePage(
                title = "Templates",
                description = "Choose a starting point for your next app.",
                onBack = { currentPage = "Home" }
            )

            "Designer" -> SimplePage(
                title = "Visual Designer",
                description = "Visual interface editing will be added in a later stage.",
                onBack = { currentPage = "Home" }
            )

            "Editor" -> SimplePage(
                title = "Code Editor",
                description = "Kotlin code editing will be added in a later stage.",
                onBack = { currentPage = "Home" }
            )

            "Builder" -> SimplePage(
                title = "APK Builder",
                description = "APK generation is not implemented yet.",
                onBack = { currentPage = "Home" }
            )

            "Settings" -> SimplePage(
                title = "Settings",
                description = "KODORA application settings.",
                onBack = { currentPage = "Home" }
            )
        }
    }
}

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "KODORA",
                color = Purple,
                fontSize = 30.sp,
                fontWeight = FontWeight.Black
            )

            Text(
                text = "CREATE. DESIGN. BUILD.",
                color = TextGray,
                fontSize = 11.sp,
                letterSpacing = 3.sp
            )
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        SurfaceDark,
                        RoundedCornerShape(22.dp)
                    )
                    .padding(22.dp)
            ) {
                Text(
                    text = "Build your next idea.",
                    color = TextWhite,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Create Android applications directly from your phone.",
                    color = TextGray,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { onNavigate("Templates") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Purple
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Create New Project")
                }
            }
        }

        item {
            Text(
                text = "WORKSPACE",
                color = TextGray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        }

        item {
            FeatureCard(
                title = "My Projects",
                subtitle = "Manage your applications",
                onClick = { onNavigate("Projects") }
            )
        }

        item {
            FeatureCard(
                title = "Templates",
                subtitle = "Start with a project template",
                onClick = { onNavigate("Templates") }
            )
        }

        item {
            FeatureCard(
                title = "Visual Designer",
                subtitle = "Design application interfaces",
                onClick = { onNavigate("Designer") }
            )
        }

        item {
            FeatureCard(
                title = "Code Editor",
                subtitle = "Edit Kotlin source code",
                onClick = { onNavigate("Editor") }
            )
        }

        item {
            FeatureCard(
                title = "APK Builder",
                subtitle = "Build your Android application",
                onClick = { onNavigate("Builder") }
            )
        }

        item {
            FeatureCard(
                title = "Settings",
                subtitle = "Configure KODORA",
                onClick = { onNavigate("Settings") }
            )
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))

            // تم استبدال modifier.align بـ Box لضمان توسط النص بدون أخطاء تجميع
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "KODORA • Version 1.0",
                    color = TextGray,
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Composable
fun FeatureCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                SurfaceDark,
                RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = TextWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = subtitle,
                color = TextGray,
                fontSize = 12.sp
            )
        }

        Text(
            text = "›",
            color = Purple,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SimplePage(
    title: String,
    description: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(22.dp)
    ) {
        TextButton(onClick = onBack) {
            Text(
                text = "← Back",
                color = Purple
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = title,
            color = TextWhite,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = description,
            color = TextGray,
            fontSize = 15.sp
        )
    }
}
