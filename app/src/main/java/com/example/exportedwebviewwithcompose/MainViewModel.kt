package com.example.exportedwebviewwithcompose

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val destinationURL = "https://www.boingboing.net/"
    private val destinationURL2 = "https://www.atlasobscura.com/"
    private val destinationURL3 = "https://www.basicinstructions.net/"

    fun getDestinationURL(): String = destinationURL
    fun getDestinationURL2(): String = destinationURL2
    fun getDestinationURL3(): String = destinationURL3

}