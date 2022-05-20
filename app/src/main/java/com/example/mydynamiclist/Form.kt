package com.example.mydynamiclist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_form.*
import java.io.File
import java.io.FileInputStream

class Form : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        btnBackList.setOnClickListener {
            val intentMain = Intent(this,MainActivity::class.java)
            startActivity(intentMain)
        }

       btnSave.setOnClickListener {

           var name = nameMovie.text.toString()
           var desc = desMovie.text.toString()
           var act:ArrayList<String> = actMovie.text.toString().split(",") as ArrayList<String>
           var cat = catMovie.text.toString()
           var img = imgMovie.text.toString()

//           println(name+" "+desc+" "+cat+" "+img)
//
//           for(a in act){
//               println(a)
//           }
           var movieObject = MovieObject(name, desc, act, cat, img)
           var gsonPretty = GsonBuilder().setPrettyPrinting().create()
           var jsonString: String = gsonPretty.toJson(movieObject)

           println(jsonString)

           val file = File(this.filesDir, "new_movies.json")
           if(file.createNewFile()){
               file.appendText("{\n  \"movies\": [")
               file.appendText(jsonString)
               file.appendText("]\n}")
           }else{
               var readResult = FileInputStream(file).bufferedReader().use { it.readText() }
               var startFile = readResult.removeSuffix("]\n}")
               startFile += ","
               file.writeText(startFile)
               file.appendText(jsonString)
               file.appendText("]\n}")
           }

           var saveToast = Toast.makeText(this,"Saved Movie", Toast.LENGTH_LONG)

           saveToast.show()

       }
    }

    class MovieObject{//CLEAN THIS UP
        var name: String? = null
        var description: String? = null
        var actors: ArrayList<String>? = null
        var category: String? = null
        var image: String? = null

        constructor() : super() {}

        constructor(movieName: String, movieDesc: String, movieAct: ArrayList<String>, movieCat: String, movieImg: String): super(){
            name = movieName
            description = movieDesc
            actors = movieAct
            category = movieCat
            image =movieImg

        }
    }
}

//class MovieJSONBulider(var name:String, var desc:String, var act:ArrayList<String>, var cat:String, var img:String){
//    override fun toString(): String {
//        return "movies [name: $name, image: $img, actors: $act, description: $desc, category: $cat]"
//    }
//}