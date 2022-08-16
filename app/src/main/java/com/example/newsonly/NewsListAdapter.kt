package com.example.newsonly

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RemoteViews
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener:NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>(){

    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewholder=NewsViewHolder(view)
        view.setOnClickListener {
         listener.onItemClicked(items[viewholder.adapterPosition])
        }
     return viewholder
    }

    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
     val currentitem=items[position]
    holder.titleView.text = currentitem.title
        holder.author.text = currentitem.author
        Glide.with(holder.itemView.context).load(currentitem.imageUrl).into(holder.image)
    }
    fun updateNews(updatedNews: ArrayList<News>)
    {
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }

  }

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView: TextView=itemView.findViewById(R.id.title)
    val image: ImageView=itemView.findViewById(R.id.Image)
    val author: TextView=itemView.findViewById(R.id.Author)
}
interface NewsItemClicked{
    fun onItemClicked(item:News)

}