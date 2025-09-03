package com.example.musicappui.ui.theme

import androidx.compose.foundation.background
import com.example.musicappui.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.runtime.SideEffect
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicappui.LibraryView
import com.example.musicappui.MainViewModel
import com.example.musicappui.Screen
import com.example.musicappui.screensInBottom
import com.example.musicappui.screensInDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(){

    val systemUiController = rememberSystemUiController()
    val appBarColor = MaterialTheme.colors.primarySurface
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
        systemUiController.setStatusBarColor(
            color = appBarColor,
            darkIcons = useDarkIcons // set true if your appbar color is light
        )
    }

    val viewModel: MainViewModel = viewModel()
    val isSheetFullScreen by remember {
        mutableStateOf(false)
    }
    val modifier = if(isSheetFullScreen) Modifier.fillMaxSize() else Modifier.wrapContentSize()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val dialogOpen = remember {
        mutableStateOf(false)
    }
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 16.dp

    val currentRoute = navBackStackEntry?.destination?.route
    val title = when (currentRoute) {
        Screen.BottomScreen.Home.bRoute -> Screen.BottomScreen.Home.bTitle
        Screen.BottomScreen.Library.bRoute -> Screen.BottomScreen.Library.bTitle
        Screen.BottomScreen.Browse.bRoute -> Screen.BottomScreen.Browse.bTitle
        Screen.DrawerScreen.Account.route -> Screen.DrawerScreen.Account.dTitle
        Screen.DrawerScreen.Subscription.route -> Screen.DrawerScreen.Subscription.dTitle
        else -> "" // fallback
    }

    val bottomBar: @Composable () -> Unit = {
//        if (currentScreen is Screen.BottomScreen || currentScreen == Screen.BottomScreen.Home){
        if (currentRoute in screensInBottom.map { it.bRoute }){
            BottomNavigation(modifier = Modifier.wrapContentSize().navigationBarsPadding(),
                backgroundColor = MaterialTheme.colors.primarySurface) {
                screensInBottom.forEach {
                    item ->
                    BottomNavigationItem(
                        selected = currentRoute == item.bRoute,
                        onClick = {
                            controller.navigate(item.bRoute)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.bTitle
                            )
                        },
                        label = {
                            Text(text = item.bTitle,
                                color = if (currentRoute == item.bRoute)
                                    Color.White else Color.White.copy(alpha = 0.5f))
                        },
                        selectedContentColor = MaterialTheme.colors.onSurface,
                        unselectedContentColor = MaterialTheme.colors.onSurface.copy(0.4f)
                    )
                }
            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent = {
        MoreBottomSheet(modifier = modifier)
    }) {Scaffold(
        bottomBar = bottomBar,
        topBar = {
            TopAppBar(
                title = { Text(title) },
                modifier = Modifier.statusBarsPadding(),
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            if (modalSheetState.isVisible) {
                                modalSheetState.hide()
                            } else {
                                modalSheetState.show()
                            }
                        }
                    }){
                        Icon(Icons.Default.MoreVert, "more")
                    }
                },
                navigationIcon = { IconButton({
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }){
                    Icon(Icons.Default.AccountCircle,"Menu")
                }},
                backgroundColor = Purple40, // 🔥 use theme color
                contentColor = MaterialTheme.colors.onPrimary

            )
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            LazyColumn(
                modifier = Modifier.statusBarsPadding()
            ) {
                items(screensInDrawer) { drawerItem ->

                    DrawerItem(
                        currentRoute == drawerItem.dRoute,
                        item = drawerItem
                    ) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        if (drawerItem.dRoute == "add_account") {
                            dialogOpen.value = true
                        } else {
                            controller.navigate(drawerItem.dRoute)
                        }
                    }
                }
            }

        }
    ) {
        Navigation(navController = controller,viewModel = viewModel, pd = it)

        AccountDialog(dialogOpen)
    }
    }



}

@Composable
fun MoreBottomSheet(modifier: Modifier) {
    Box(
        Modifier.fillMaxWidth().height(300.dp).background(
            MaterialTheme.colors.primarySurface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = Modifier.padding(16.dp)) {
                Icon(modifier = Modifier.padding(8.dp),
                    painter = painterResource(R.drawable.baseline_settings_24),
                    contentDescription = "Settings")
                Text("Settings", fontSize = 20.sp, color = MaterialTheme.colors.onSurface)
            }
            Row(modifier = Modifier.padding(16.dp)) {
                Icon(modifier = Modifier.padding(8.dp),
                    painter = painterResource(R.drawable.baseline_share_24),    
                    contentDescription = "share")
                Text("Share", fontSize = 20.sp, color = MaterialTheme.colors.onSurface)
            }
            Row(modifier = Modifier.padding(16.dp)) {
                Icon(modifier = Modifier.padding(8.dp),
                    painter = painterResource(R.drawable.baseline_help_24),
                    contentDescription = "help")
                Text("Help", fontSize = 20.sp, color = MaterialTheme.colors.onSurface)
            }
        }
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked:()->Unit
){
//    val background = if(selected) Color.DarkGray else Color.Gray
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
//            .background(background)
            .clickable{
                onDrawerItemClicked()
            }
        )
    {
        Icon(
            painter = painterResource(id = item.icon),
            item.dTitle,
            modifier = Modifier.padding(end = 8.dp, start = 4.dp)
        )
        Text(
            item.dTitle,
            style = MaterialTheme.typography.h5)
    }
}

@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues){
    NavHost(navController as NavHostController,
        Screen.BottomScreen.Home.bRoute,
        modifier = Modifier.padding(pd)
        ) {

        composable(Screen.BottomScreen.Home.bRoute) {
            HomeView()
        }

        composable(Screen.BottomScreen.Library.bRoute) {
            LibraryView()
        }

        composable(Screen.BottomScreen.Browse.bRoute) {
            BrowseView()
        }

        composable(Screen.DrawerScreen.Account.route) {
            AccountView()
        }
        composable(Screen.DrawerScreen.Subscription.route) {
            Subscription()
        }
    }
}