package com.example.githubproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.githubproject.ResponseUser
import com.example.githubproject.databinding.ItemListBinding

class UserAdapter (private val data:MutableList<ResponseUser.Items> = mutableListOf(),
private val listener:(ResponseUser.Items) -> Unit ):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    fun setData(data: MutableList<ResponseUser.Items>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    class UserViewHolder(private val v:ItemListBinding) : RecyclerView.ViewHolder(v.root) {
        fun bind(item: ResponseUser.Items) {
            v.imageView2.load(item.avatarUrl) {
                transformations(CircleCropTransformation())
            }
            v.username.text = item.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{
            listener(item)
    }
    }

    override fun getItemCount(): Int = data.size
}