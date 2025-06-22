package com.dvt.weatherapp.presentation.feature.home_screen

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dvt.weatherapp.R
import com.dvt.weatherapp.domain.DayData
import com.dvt.weatherapp.domain.DummyData
import com.dvt.weatherapp.domain.WeatherData

@Preview
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    weatherData: WeatherData? = DummyData.weatherData
) {
    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(weatherData?.currentDayImage ?: R.drawable.cloudy),
            contentDescription = "Current Day Image",
        )

        Column {
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text(
                text = "5 Day Forecast",
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp),
                color = Color.White,
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
                if (weatherData != null) {
                    items(weatherData.days) { dayData ->
                        WeatherItem(dayData = dayData)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun WeatherItem(
    modifier: Modifier = Modifier,
    dayData: DayData = DayData(
        day = "Monday",
        temperature = "25°C",
        weatherIcon = R.drawable.property_120rainlight
    )
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
                Text(text = dayData.day, fontSize = 26.sp)

                Image(
                    painter = painterResource(dayData.weatherIcon),
                    contentDescription = stringResource(R.string.weather_icon),
                    modifier = Modifier
                        .padding(8.dp)
                        .size(48.dp)
                )
            }
            Text(text = dayData.temperature)
        }
    }
}