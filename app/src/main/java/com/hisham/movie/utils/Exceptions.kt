package com.hisham.movie.utils

import java.io.IOException

class ApiException(message:String):IOException(message)
class NoInternetException(message: String):IOException(message)