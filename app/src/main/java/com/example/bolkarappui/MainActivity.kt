package com.example.bolkarappui

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bolkarappui.adapters.MemberAdapter
import com.example.bolkarappui.adapters.SpeakerAdapter
import com.example.bolkarappui.databinding.ActivityMainBinding
import com.example.bolkarappui.models.Speaker
import com.example.bolkarappui.viewmodel.BolkarViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var memberAdapter: MemberAdapter


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val viewModel = ViewModelProvider(this)[BolkarViewModel::class.java]
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.materialToolbar)
        speakerAdapter = SpeakerAdapter()
        memberAdapter = MemberAdapter()

        viewModel.getData()

        setupRecyclerView(this)

        viewModel.speakersList.observe(this){
            speakerAdapter.submitList(it)
            setupToolbar(it)
        }
        viewModel.membersList.observe(this){
            memberAdapter.submitList(it)
        }

        binding.backBtn.setOnClickListener {

        }
        val emojiText = unicodeToString(emojiUnicode = 0x270C)
        binding.leaveBtn.text = "$emojiText Leave"


    }

    private fun setupToolbar(list: MutableList<Speaker>) {
        binding.peopleNo.text = (list.size).toString()
        val url = Uri.parse("https://cdn1.bolkarapp.com/uploads/dp/${list[0].u}.jpg")
        Glide.with(this)
            .load(url)
            .into(binding.profilePicture)

    }

    private fun setupRecyclerView(context: Context) {
        binding.speakersRV.apply {
            adapter = speakerAdapter
            layoutManager = GridLayoutManager(context, 3)
        }

        binding.membersRV.apply{
            adapter = memberAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun unicodeToString(emojiUnicode: Int) =
        String(Character.toChars(emojiUnicode))

}