package com.SemiColon.urbanplanner

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

// Data class for Dashboard Buttons (Central Grid)
data class DashboardAction(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

// Data class for Bottom Navigation Items
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToMap: () -> Unit,
    onNavigateToSaved: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    // State for Drawer and Coroutines
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // State for Notifications Dropdown
    var showNotificationMenu by remember { mutableStateOf(false) }

    // State for Bottom Navigation (0 = Home, 1 = Map, 2 = Saved)
    var selectedItemIndex by remember { mutableIntStateOf(0) }

    // Bottom Navigation Items
    val bottomNavItems = listOf(
        BottomNavItem("Home", Icons.Outlined.Home, "home"),
        BottomNavItem("Map", Icons.Outlined.LocationOn, "map"),
        BottomNavItem("Saved", Icons.Outlined.Place, "saved")
    )

    // Central Grid Actions (Quick access buttons)
    val dashboardActions = listOf(
        DashboardAction("Start Mapping", Icons.Default.AddLocationAlt, onNavigateToMap),
        DashboardAction("Project History", Icons.Default.History) { /* Handle History */ },
        DashboardAction("Analytics", Icons.Default.Analytics) { /* Handle Analytics */ },
        DashboardAction("Community", Icons.Default.Groups) { /* Handle Community */ }
    )

    // Main Layout Structure
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                // Drawer Header
                Text(
                    "User Profile",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Divider() 

                // Drawer Items
                NavigationDrawerItem(
                    label = { Text("My Profile") },
                    selected = false,
                    onClick = { /* Navigate Profile */ },
                    icon = { Icon(Icons.Default.Person, contentDescription = null) }
                )
                NavigationDrawerItem(
                    label = { Text("Settings") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        onNavigateToSettings()
                    },
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) }
                )
                NavigationDrawerItem(
                    label = { Text("Help & Support") },
                    selected = false,
                    onClick = { /* Handle Help */ },
                    icon = { Icon(Icons.Default.Help, contentDescription = null) }
                )
                Spacer(modifier = Modifier.weight(1f))
                NavigationDrawerItem(
                    label = { Text("Logout") },
                    selected = false,
                    onClick = { /* Handle Logout */ },
                    icon = { Icon(Icons.Default.ExitToApp, contentDescription = null) }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Urban Planner",
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    navigationIcon = {
                        // Profile Icon (Top Left) -> Opens Drawer
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Profile",
                                modifier = Modifier.size(32.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    actions = {
                        // Notification Icon (Top Right) -> Dropdown
                        Box {
                            IconButton(onClick = { showNotificationMenu = true }) {
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = "Notifications"
                                )
                            }
                            DropdownMenu(
                                expanded = showNotificationMenu,
                                onDismissRequest = { showNotificationMenu = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("New Location Found") },
                                    onClick = { showNotificationMenu = false }
                                )
                                DropdownMenuItem(
                                    text = { Text("System Update") },
                                    onClick = { showNotificationMenu = false }
                                )
                                Divider()
                                DropdownMenuItem(
                                    text = { Text("Clear All") },
                                    onClick = { showNotificationMenu = false }
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
            },
            bottomBar = {
                NavigationBar {
                    bottomNavItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                // Logic to swap screens would go here
                                if (index == 1) onNavigateToMap()
                                if (index == 2) onNavigateToSaved()
                            },
                            label = { Text(item.label) },
                            icon = { Icon(item.icon, contentDescription = item.label) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            // Dashboard Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                // Greeting / Banner
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Good Afternoon!",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Where shall we plan today?",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                        )
                    }
                }

                Text(
                    text = "Quick Actions",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Central Grid for specific actions
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(dashboardActions) { item ->
                        DashboardActionButton(item)
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardActionButton(item: DashboardAction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { item.onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    DashboardScreen({}, {}, {})
}