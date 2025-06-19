package  com.dvt.weatherapp.data

import com.dvt.weatherapp.domain.WeatherData
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApiService {

    @GET("data/2.5/forecast")
    suspend fun getWeatherForecast():Response<WeatherData>

}