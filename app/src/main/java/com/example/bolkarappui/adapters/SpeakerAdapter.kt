package com.example.bolkarappui.adapters


import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bolkarappui.R

import com.example.bolkarappui.databinding.ItemParticipantBinding

import com.example.bolkarappui.models.Speaker

class SpeakerAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Speaker>() {
        override fun areItemsTheSame(oldItem: Speaker, newItem: Speaker): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Speaker, newItem: Speaker): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: List<Speaker>) = differ.submitList(list)
    inner class SpeakerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemParticipantBinding.bind(itemView)

        fun bind(position: Int) {

            val currentSpeaker = differ.currentList[position]
            val url: Uri = Uri.parse("https://cdn1.bolkarapp.com/uploads/dp/${currentSpeaker.u}.jpg")
            Glide.with(itemView)
                .load(url)
                .placeholder(R.drawable.profile_placeholder)
                .into(binding.particpantProfile)

            binding.speakerName.text = currentSpeaker.n.split(" ")[0]

            if (currentSpeaker.mod) {
                binding.speakerType.text = itemView.context.getString(R.string.host)
                binding.speakerBadge.visibility = View.VISIBLE

            } else {
                binding.speakerType.text = itemView.context.getString(R.string.speaker)
                binding.speakerBadge.visibility = View.GONE
            }
            if(currentSpeaker.mic){
                binding.micImage.visibility = View.GONE
            }else{
                binding.micImage.visibility = View.VISIBLE
            }
            if (currentSpeaker.socketid != ""){
                binding.particpantProfile.borderColor = ContextCompat.getColor(itemView.context,R.color.blue)
                binding.speakerBadge.visibility = View.VISIBLE
                binding.speakerType.text = itemView.context.getString(R.string.host)
            }


        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return SpeakerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_participant,
                    parent, false
                )
        )


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        SpeakerViewHolder(holder.itemView).bind(position)
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}