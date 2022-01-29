package com.elchaninov.gbprofessionaldevelopment.view.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elchaninov.gbprofessionaldevelopment.R
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel

class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: DataModel, onItemClickListener: MainAdapter.OnListItemClickListener) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            itemView.findViewById<TextView>(R.id.header_textview_recycler_item).text = data.text

            itemView.findViewById<TextView>(R.id.description_textview_recycler_item).text =
                if (data.meanings?.size != 0) data.meanings?.get(0)?.translation?.translation
                else ""


            itemView.setOnClickListener { onItemClickListener.onItemClick(data) }
        }
    }
}