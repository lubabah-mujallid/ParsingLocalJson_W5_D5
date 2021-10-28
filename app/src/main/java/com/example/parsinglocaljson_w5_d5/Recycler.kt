package com.example.parsinglocaljson_w5_d5

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parsinglocaljson_w5_d5.databinding.RecylcerLayoutBinding

class Recycler(val messages: ArrayList<Image>, val context: Context): RecyclerView.Adapter<Recycler.ViewHolder>() {
    class ViewHolder(val binding: RecylcerLayoutBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecylcerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messages[position]
        holder.binding.apply {
            Glide.with(cv).load(message.url).into(rvImageView)
            rvTextView.text = message.title
        }
    }

    override fun getItemCount() = messages.size
}