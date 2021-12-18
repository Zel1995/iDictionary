package com.example.idictionary.view.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.idictionary.R
import com.example.idictionary.model.data.DataModel

class HistoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val data = mutableListOf<DataModel>()

    fun setData(dataToSet: List<DataModel>?) {
        data.apply {
            clear()
            dataToSet?.let { addAll(it) }
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.history_rv_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HistoryViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.history_rv_title_textView)
        fun bind(word: DataModel) {
            title.text = word.text
        }

    }
}