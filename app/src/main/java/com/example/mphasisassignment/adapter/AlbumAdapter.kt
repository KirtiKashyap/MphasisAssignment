package com.example.mphasisassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mphasisassignment.databinding.ItemLayoutBinding
import com.example.mphasisassignment.model.Album

class AlbumAdapter(
    private val listener: AlbumItemListener) : RecyclerView.Adapter<AlbumViewHolder>() {

    interface AlbumItemListener {
        fun onClickedAlbum(id: String)
    }

    private val items = ArrayList<Album>()

    fun setItems(items: ArrayList<Album>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding: ItemLayoutBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) = holder.bind(items[position])
}

class AlbumViewHolder(private val itemBinding: ItemLayoutBinding,
                      private val listener: AlbumAdapter.AlbumItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var album : Album

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: Album) {
        this.album = item
        itemBinding.textViewTitle.text = item.title
    }

    override fun onClick(v: View?) {
        listener.onClickedAlbum(album.id)
    }
}