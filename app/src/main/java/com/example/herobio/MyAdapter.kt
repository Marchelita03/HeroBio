package com.example.herobio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(private var heroList: ArrayList<Hero>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var mListener : onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,
        parent, false)
        return MyViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = heroList[position]
        holder.judulGambar.setImageResource(currentItem.judulGambar)
        holder.tvHeading.text = currentItem.heading
        holder.tvHeadingTtl.text = currentItem.headingTtl
    }

    override fun getItemCount() : Int {

        return heroList.size
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val judulGambar: ShapeableImageView = itemView.findViewById(R.id.judul_gambar)
        val tvHeading: TextView = itemView.findViewById(R.id.tvHeading)
        val tvHeadingTtl: TextView = itemView.findViewById(R.id.tvHeadingTtl)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
}