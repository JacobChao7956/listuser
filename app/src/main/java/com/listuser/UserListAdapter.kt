package com.listuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.listuser.databinding.ItemUserBinding
import com.listuser.datamodel.User

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    val itemList = mutableListOf<User>()

    lateinit var itemBinding: ItemUserBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(ItemUserBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ).apply {
        itemBinding = this
    })

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.showUser()
    }

    override fun getItemCount() = itemList.size

    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun showUser() {
            binding.name.text = itemList[layoutPosition].login
            Glide.with(itemView)
                .load(itemList[layoutPosition].avatar_url)
                .fitCenter()
                .into(binding.face)
        }

        override fun onClick(v: View?) {

        }
    }
}
