package com.dvt.weatherapp.presentation.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dvt.weatherapp.R
import com.dvt.weatherapp.domain.DayDataDTO
import com.dvt.weatherapp.util.DateUtils
import com.dvt.weatherapp.util.ResourceProvider

@Preview
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.value.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (uiState.value.error != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Error: ${uiState.value.error}",
            )
        }
    } else if (uiState.value.weatherData != null) {
        Box {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(
                    ResourceProvider.getTodayWeatherConditionIcon(uiState.value.weatherData!!.list)
                        ?: R.drawable.cloudy
                ),
                contentDescription = "Current Day Image",
            )
            Column {
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                Text(
                    text = "5 Day Forecast",
                    fontSize = 28.sp,
                    modifier = Modifier.padding(16.dp),
                    color = Color.White,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(1.dp)
                )
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                LazyColumn {
                    items(DateUtils.getLatestPerWeekday(uiState.value.weatherData!!.list)) { dayData ->
                        WeatherItem(dayData = dayData)
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherItem(
    modifier: Modifier = Modifier,
    dayData: DayDataDTO
) {
    Card(
        modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = DateUtils.getDayName(dayData.dt_txt),
                    fontSize = 26.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Image(
                    painter = painterResource(
                        ResourceProvider.getImageIcon(dayData.weather[0].icon)
                    ),
                    contentDescription = stringResource(R.string.weather_icon),
                    modifier = Modifier
                        .padding(8.dp)
                        .size(48.dp)
                )
            }
            Text(
                text = "${dayData.main.temperature}°C",
                fontSize = 28.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}