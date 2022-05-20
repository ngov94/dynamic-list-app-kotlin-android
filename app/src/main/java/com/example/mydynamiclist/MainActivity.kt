package com.example.mydynamiclist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddMovie.setOnClickListener {
            val intentAddMovie = Intent(this,Form::class.java)
            startActivity(intentAddMovie)
        }


        listView = findViewById(R.id.name_list_view) //R is the res folder
//        var movie = Movie("","", JSONArray(), "","")

        var movieItemList = Movie.getMovieList("movies.json", this)
        var newmovieItemList = Movie.getMovieList("new_movies.json", this.filesDir)

        movieItemList.addAll(newmovieItemList)


//        var listItems = arrayOfNulls<String>(movieItemList.size)
//        var listItemsDes = arrayOfNulls<String>(movieItemList.size)
//        USING FOR LOOPS
    //        var i = 0
//        for (item in movieItemList){
//            listItems[i++] = item.name
//            println(item.name)
//        }
//        //ANOTHER WAY OF USING FOR LOOPS
//        for(i in 0..movieItemList.lastIndex){
//            listItems[i] = movieItemList[i].name
//        }
        //ANOTHER WAY OF USING FOR LOOPS

//        for(i in 0 until movieItemList.size){
//            val currMovie = movieItemList[i]
//            listItems[i] =currMovie.name
//            listItemsDes[i] = currMovie.description
//
//        }

//        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
//        listView.adapter = adapter

        val adapter = MovieAdapter(this,movieItemList)
        listView.adapter = adapter

    }

}
//If there is no list then it shows an empty list
//List of items with only their "names" shown





//What is a JSON? (Javascript object notation)
//Universal way of communicating data
//It contains key value pairs
//It is an array of object
//Objects are contained with curly braces

//JSON file Structure is
//{
//  key:value
//}

//keys => strings
//values => any object