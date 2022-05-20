package com.example.mydynamiclist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


//Adapter helps you to bind the view and the data source
//Adapter sits between view and data source
class MovieAdapter(val context: Context, val dataSource:ArrayList<Movie>):BaseAdapter() {

    //LayoutInflater - will help you to create a new view out of an existing xml layouts
    val inflater = LayoutInflater.from(context)

    //ctrl - i to get the baseAdapter member functions that need to be implemented
    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view:View = inflater.inflate(R.layout.list_item_movie, parent, false)
        val holder = ViewHolder()

        holder.nameTextView = view.findViewById(R.id.movie_name) as TextView
        holder.thumbnailImageView = view.findViewById(R.id.movie_thumbnail) as ImageView
        holder.actTextView = view.findViewById(R.id.movie_act) as TextView
        holder.descTextView = view.findViewById(R.id.movie_desc) as TextView
        holder.catTextView = view.findViewById(R.id.movie_cat) as TextView

        //attaching the holder to the view
        view.tag = holder

        val movie = getItem(position) as Movie

        var actText = "Cast: ${movie.actors[0]}"
        for(act in 1 until movie.actors.size){
            actText += ", ${movie.actors[act]}"
        }

        //Binding movie to view
        holder.nameTextView.text = movie.name
        Picasso.with(context).load(movie.image).into(holder.thumbnailImageView)
        holder.actTextView.text = actText
        holder.descTextView.text = movie.description
        holder.catTextView.text = movie.category

        return view
    }

    private class ViewHolder {
        lateinit var nameTextView: TextView
        lateinit var thumbnailImageView: ImageView
        lateinit var actTextView: TextView
        lateinit var descTextView: TextView
        lateinit var catTextView: TextView

    }
}