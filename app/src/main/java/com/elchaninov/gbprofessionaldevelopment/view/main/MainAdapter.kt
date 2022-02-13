package com.elchaninov.gbprofessionaldevelopment.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elchaninov.gbprofessionaldevelopment.databinding.ActivityMainRecyclerviewItemBinding
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel


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
        holder.itemView.setOnClickListener { onListItemClickListener(data[position]) }
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class RecyclerItemViewHolder(private val viewBinding: ActivityMainRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(dataDto: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                if (layoutPosition != RecyclerView.NO_POSITION) {
                    viewBinding.headerTextviewRecyclerItem.text = dataDto.text

                    viewBinding.descriptionTextviewRecyclerItem.text =
                        dataDto.meanings?.get(0)?.translation?.translation
                }
            }
        }
    }
}