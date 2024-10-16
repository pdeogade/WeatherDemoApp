# Weather
This is Sample Weather app, show the weather of city you enter, It use https://www.weatherapi.com/api-explorer.aspx#forecast to fetch the weather related info.


## Tech Stack used:

**1) Kotlin:** A modern, expressive programming language for Android development.

**2) MVVM:** Model-View-ViewModel architecture for separating UI logic from business logic.

**3) Compose for UI:** A modern toolkit for building native Android UI.

**4) Hilt:** Dependency injection library for managing dependencies.

**5) Coroutines:** Simplify asynchronous programming with Kotlin coroutines.

**6) MockK, JUnit:** Tools for unit testing Kotlin code effectively.

**7) Retrofit:** A type-safe HTTP client for making API calls.

**8) Kotlin Flow:** A reactive streams API for managing async data

## Caching and API Integration:

	On Entering city name in search bar, api call will be made inorder to get the weather info.
 	upon fetching data it will be parsed and collected by coroutine flow, with that it will also cache data.
  	it can cache data of multiple cities with it.User can get this cache data even in offline mode.
	
   	
## Architecture:

Weather app is made with clean architecture approach. Here, business logic is independent of UI and frameworks, 
promoting maintainable and scalable code. Key principles include:
		
**1) Data Layer:** Manages data operations and acts as the source of data for the entire app.

**2) Domain Layer:** Contains business logic and domain entities, representing the core of the application.

**3) Dependency Injection:** Manages dependencies in a scalable way, enhancing testability and modularity.

**4) Presentation Layer:** Handles UI and user interaction, ensuring a clear separation from business logic



## WorkFlow:  

	User Interaction: 
	     User enters a city name to search for weather information.

	ViewModel:
	    WeatherHomeViewModel captures the city name input and triggers the api to fetch weather.
	    The ViewModel uses Kotlin Coroutines to launch a background task.

	Repository:
	    WeatherRepository fetches weather data from the weather API using Retrofit.
	    The data is processed and emitted as a Flow.

	Network Call:
	    Retrofit makes the API call to retrieve current weather and a 5-day forecast.
	    The response is parsed into data models.

	UI Update:
	  The ViewModel collects the Flow and updates the StateFlow with the weather data.
	  WeatherHomeScreen composable observes the StateFlow and updates the UI to display current weather conditions and forecast.

	UI Components:
	  WeatherHomeScreen contains input fields, displays weather details, and uses a custom gradient background.

   	OfflineMode:
    	  User can go offline by disabling internet. Now when user try to fetch the weather of city which has been entered previously,
      	  we will get the data from cache implemented in app

## Screen Shot:

![Screenshot 2024-10-15 184051](https://github.com/user-attachments/assets/7e227f7d-ccb2-47f5-bbab-567ce8e1a469)

## Video

[Screen_recording_20241015_182839.webm](https://github.com/user-attachments/assets/1e4b65b3-125c-416f-aff6-06d726a7da4f)


## Execute Test Task: 


	Before running any tests, make sure your project is fully synchronized with Gradle by clicking Sync Project  in the toolbar. 
 	You can run tests with different levels of granularity:
	To run all tests in a directory or file, open the Project window and do either of the following:
	Right-click on a directory or file and click Run .
	Select the directory or file and use shortcut Control+Shift+R.
---
> other way from terminal:
> use **./gradlew test**



		

  		
 		


 		
     		

   

