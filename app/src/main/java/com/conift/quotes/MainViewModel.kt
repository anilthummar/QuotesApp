package com.conift.quotes

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset

class MainViewModel(val context :Context) :  ViewModel() {

    private  var quoteList:Array<Quote> = emptyArray()
    private var index=0
    private var size=0

    init {
        quoteList =loadQoutesFromAssets()
        size=quoteList.size
    }

    fun getQoute()= quoteList[index]


    fun nextQoute() :Quote{
        index=(index+1) % size
        return  quoteList[index]
        //quoteList[(++index + quoteList.size) % quoteList.size]
    }

    fun previousQoute() :Quote{

        index--
        if(index<0) index=size-1
        return  quoteList[index]

        //quoteList[(--index + quoteList.size) % quoteList.size]
    }

    private fun loadQoutesFromAssets(): Array<Quote> {

        val inputStream = context.assets.open("quotes.json")
        val size = inputStream.available()
        val buffer =ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json= String(buffer, Charsets.UTF_8)
        val gson =Gson()

        return gson.fromJson(json,Array<Quote>::class.java)

    }

}