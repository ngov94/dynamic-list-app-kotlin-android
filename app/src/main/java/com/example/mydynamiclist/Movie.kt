package com.example.mydynamiclist

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.nio.charset.Charset
import kotlin.text.Charsets.UTF_8

class Movie(val name: String, val image: String, val actors: Array<String>, val description: String, val category: String) {
//    var name:String
//    var image:String
//    var actors:JSONArray
//    var description:String
//    var category:String
//
//    constructor(_name: String, _image: String, _actors: JSONArray, _description: String, _category: String) {
//        name = _name
//        image = _image
//        actors = _actors
//        description = _description
//        category = _category
//    }

//    var name:String
//    init {
//        name = _name
//    }
companion object {
    fun getMovieList(filename: String, context: Context): ArrayList<Movie> {
        val movieItemList = ArrayList<Movie>()
        val jsonStr = getJsonFromAssets(filename, context)

        //Step 7: JSONObject
        val movieJson = JSONObject(jsonStr)
        val movieList = movieJson.getJSONArray("movies")

        //mapping the movies
        (0 until movieList.length()).mapTo(movieItemList) {
            val acts = movieList.getJSONObject(it).getJSONArray("actors")
            Movie(
                movieList.getJSONObject(it).getString("name"),
                movieList.getJSONObject(it).getString("image"),
                Array(acts.length()){acts.getString(it)},
                movieList.getJSONObject(it).getString("description"),
                movieList.getJSONObject(it).getString("category")
            )
        }
        return movieItemList
    }

    fun getMovieList(filename: String, parent: File): ArrayList<Movie> {
        val movieItemList = ArrayList<Movie>()
        val jsonStr = getJsonFromAssets(filename, parent)

        //Step 7: JSONObject
        val movieJson = JSONObject(jsonStr)
        val movieList = movieJson.getJSONArray("movies")

        //mapping the movies
        (0 until movieList.length()).mapTo(movieItemList) {
            val acts = movieList.getJSONObject(it).getJSONArray("actors")
            Movie(
                movieList.getJSONObject(it).getString("name"),
                movieList.getJSONObject(it).getString("image"),
                Array(acts.length()){acts.getString(it)},
                movieList.getJSONObject(it).getString("description"),
                movieList.getJSONObject(it).getString("category")
            )
        }
        return movieItemList
    }

    private fun getJsonFromAssets(filename: String, context: Context): String? {
        var jsonStr: String? = null

        //Step 1: open -> Stream of bytes
        val inStream = context.assets.open(filename)
        //Whenever there is a stream you need a buffer
        // becuase the rate of reading can be the rate of streamming
        //So we need to accumalate things? somewhere before it gets stored

        //Step 3
        val size = inStream.available()
        //Step 2: Bytearray (reading bunch of bytes from the file)
        val buffer = ByteArray(size)
        //Step 4
        inStream.read(buffer)
        //Step 5
        inStream.close()
        //Step 6
        jsonStr = String(buffer, Charsets.UTF_8)

        return jsonStr
    }

    private fun getJsonFromAssets(filename: String, parent: File): String? {
        var jsonStr: String? = null

        //Step 1: open -> Stream of bytes
        val inStream = File(parent,filename).inputStream()
        //Whenever there is a stream you need a buffer
        // becuase the rate of reading can be the rate of streamming
        //So we need to accumalate things? somewhere before it gets stored

        //Step 3
        val size = inStream.available()
        //Step 2: Bytearray (reading bunch of bytes from the file)
        val buffer = ByteArray(size)
        //Step 4
        inStream.read(buffer)
        //Step 5
        inStream.close()
        //Step 6
        jsonStr = String(buffer, Charsets.UTF_8)

        return jsonStr
    }
}
}
