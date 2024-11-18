package com.example.exportedwebviewwithcompose

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val destinationURL = "https://www.boingboing.net/"

    fun getDestinationURL(): String = destinationURL

}