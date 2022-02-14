package com.elchaninov.gbprofessionaldevelopment.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elchaninov.gbprofessionaldevelopment.R
import com.elchaninov.gbprofessionaldevelopment.databinding.ActivityMainRecyclerviewItemBinding
import com.elchaninov.model.usermodel.DataModel
import com.elchaninov.utils.convertMeaningsToString
import com.elchaninov.utils.viewById


class MainAdapter(
    private var onListItemClickListener: (DataModel) -> Unit,
) : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataModel> = arrayListOf()

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val binding = ActivityMainRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RecyclerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class RecyclerItemViewHolder(viewBinding: ActivityMainRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        private val headerTextviewRecyclerItem by viewById<TextView>(R.id.header_textview_recycler_item)
        private val descriptionTextviewRecyclerItem by viewById<TextView>(R.id.description_textview_recycler_item)

        init {
            itemView.setOnClickListener { onListItemClickListener(data[layoutPosition]) }
        }

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                headerTextviewRecyclerItem.text = data.text
                descriptionTextviewRecyclerItem.text = convertMeaningsToString(data.meanings)
            }
        }
    }
}