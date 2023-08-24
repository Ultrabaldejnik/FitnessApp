package com.example.fitnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.fitnessapp.data.FitTips

import com.example.fitnessapp.ui.theme.FitnessAppTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessAppTheme {
                FitnessApp()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnessApp(vm: FitnessViewModel = viewModel()) {

    val fitTips by vm.uiState.collectAsState()

    Scaffold(topBar = { FitnessBar() }) { it ->
        LazyColumn(contentPadding = it) {
            items(fitTips) { tip ->

                DayItemList(
                    fitTips = tip,
                    onclick = { vm.updateStateItemUI(tip, it) }
                )
            }
        }
    }
}

@Composable
fun FitnessBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(id = R.string.app_name), fontSize = 30.sp)
            }
        },
        modifier = modifier.height(50.dp)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayItemList(
    modifier: Modifier = Modifier,
    fitTips: FitnessTipUI,
    onclick: (Boolean) -> Unit
) {

    var expand by remember {
        mutableStateOf(fitTips.expand)
    }

    val density = LocalDensity.current
    Card(
        modifier = modifier
            .clickable {
                expand = !expand
                onclick(expand)
            }
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "День ${fitTips.day}", fontSize = 20.sp)
            Text(
                text = stringResource(id = fitTips.title),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                Image(
                    painter = painterResource(id = fitTips.imageRes),
                    contentDescription = stringResource(
                        id = fitTips.title
                    ),
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                )
            }
            AnimatedVisibility(
                visible = expand, enter = expandVertically(
                    // Expand from the top.
                    expandFrom = Alignment.Top
                ) + fadeIn(
                    // Fade in with the initial alpha of 0.3f.
                    initialAlpha = 0.3f
                ),
                exit = shrinkVertically() + fadeOut()
            ) {
                Text(
                    text = stringResource(id = fitTips.description),
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}







