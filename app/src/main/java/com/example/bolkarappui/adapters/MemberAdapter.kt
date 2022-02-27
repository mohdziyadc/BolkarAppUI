package com.example.bolkarappui.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bolkarappui.R
import com.example.bolkarappui.databinding.ItemUserBinding
import com.example.bolkarappui.models.Member


class MemberAdapter:RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    inner class MemberViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val binding = ItemUserBinding.bind(itemView)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Member>() {
        override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list:List<Member>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        return MemberViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val currentMember = differ.currentList[position]
        val url = Uri.parse("https://cdn1.bolkarapp.com/uploads/dp/${currentMember.u}.jpg")
        holder.apply {
            Glide.with(itemView)
                .load(url)
                .into(binding.memberProfile)

            binding.memberName.text = currentMember.n.split(" ")[0]
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}