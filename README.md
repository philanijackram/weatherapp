# weatherapp
This is an application That shows a 5 day weather forecast based on the users location.
It connects to the https://openweathermap.org/forecast5 Api to get the weather forecast.

Architecture That is used is MVVM.

Here is the list of tools used in this project

Retrofit (For Network calls), 
Jetpack Compose (Kotlin's Declarative UI), 
Kotlin Coroutines (For asynchronous code), 
Hilt (For dependancy injection), 
HMS Location (to get the device location for devices that don't have Google play services),
GMS Location (To get the device location for devices with Google Play Services),

To build the project
-Android Studio IDE,  
-HMS or GMS device or emulator,
-Clone the project,
-Open the project,
-under build variant select the platform you are using either HMS or GMS.
-The run the project -HOORAY

The APK for this project can be found here. https://drive.google.com/drive/folders/16cA9CQMnHOqBteE_5JElA1sDdt0R4t0O?usp=drive_link
