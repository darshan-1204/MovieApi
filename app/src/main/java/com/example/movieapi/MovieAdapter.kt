package com.example.movieapi

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.intellij.lang.annotations.Language

class MovieAdapter(results: List<ResultsItem?>?) : Adapter<MovieAdapter.MovieHolder>() {

    var results = results

    class MovieHolder(itemView: View) : ViewHolder(itemView){

        var img= itemView.findViewById<ImageView>(R.id.imgMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false))
    }

    override fun getItemCount(): Int {
        return results?.size!!
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        var str = "https://image.tmdb.org/t/p/w500"
        Glide.with(holder.itemView.context).load(str+results?.get(position)?.posterPath).into(holder.img)

        holder.itemView.setOnClickListener {

            var dialog =BottomSheetDialog(holder.itemView.context)
            dialog.setContentView(R.layout.movie_item)
//            dialog.window?.setBackgroundDrawable(ColorDrawable(android.R.color.transparent))

            var movieName = dialog.findViewById<TextView>(R.id.movieName)
            var Language = dialog.findViewById<TextView>(R.id.lang)
            var RealeseDate = dialog.findViewById<TextView>(R.id.relaseDate)
            var desc = dialog.findViewById<TextView>(R.id.description)

            movieName?.setText(results?.get(position)?.originalTitle)
            Language?.setText(results?.get(position)?.originalLanguage)
            RealeseDate?.setText(results?.get(position)?.releaseDate)
            desc?.setText(results?.get(position)?.overview)

            dialog.show()
        }
    }
}