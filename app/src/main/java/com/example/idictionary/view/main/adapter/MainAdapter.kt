package com.example.idictionary.view.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.idictionary.R
import com.example.utils.convertMeaningsToString

class MainAdapter(private var onItemClick: (com.example.model.DataModel) -> Unit) :
    RecyclerView.Adapter<MainAdapter.DictionaryViewHolder>() {
    private val data = mutableListOf<com.example.model.DataModel>()

    fun setData(dataToSet: List<com.example.model.DataModel>) {
        data.apply {
            clear()
            addAll(dataToSet)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewHolder {
        return DictionaryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_main_recyclerview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size


    inner class DictionaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title_textview_recycler_item)
        val content = itemView.findViewById<TextView>(R.id.content_textview_recycler_item)
        fun bind(data: com.example.model.DataModel) {
            if(layoutPosition != RecyclerView.NO_POSITION){
                title.text = data.text
                content.text = com.example.utils.convertMeaningsToString(data.meanings)
                itemView.setOnClickListener{ onItemClick.invoke(data)}
            }
        }

    }
}
