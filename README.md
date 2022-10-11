#MoviesApp
This app shows the list of movies based on a keyword "top". The App is using this [API] (https://omdbapi.com/). The architecture of the app is based on MVVM. 
The room library is used for offline storage of movies data, but currently only the list of movies are stored for offline caching.
### Libraries
- Kotlin Coroutines
- Koin
- Retrofit 
- MockWebServer
- Glide
- Room
### API's
- OMDb API (https://omdbapi.com/)
### Testing
For testing, MockWebServer is used to test the status of the response. 