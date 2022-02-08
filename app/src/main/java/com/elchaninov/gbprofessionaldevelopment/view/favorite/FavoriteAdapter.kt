package com.elchaninov.gbprofessionaldevelopment.view.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elchaninov.gbprofessionaldevelopment.databinding.ActivityFavoriteRecyclerviewItemBinding
import com.elchaninov.model.usermodel.DataModel

class FavoriteAdapter(
    private var onListItemClickListener: (DataModel) -> Unit,
    private var onFavoriteClickListener: (DataModel) -> Unit,
) : RecyclerView.Adapter<FavoriteAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataModel> = arrayListOf()

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val binding = ActivityFavoriteRecyclerviewItemBinding.inflate(
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

    inner class RecyclerItemViewHolder(private val binding: ActivityFavoriteRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { onListItemClickListener(data[layoutPosition]) }
            binding.image.setOnClickListener { onFavoriteClickListener(data[layoutPosition]) }
        }

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerFavoriteTextviewRecyclerItem.text = data.text
            }
        }
    }
}
