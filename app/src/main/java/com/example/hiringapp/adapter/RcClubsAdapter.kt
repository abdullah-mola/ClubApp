package com.example.hiringapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hiringapp.data.local.LocalClubItem
import com.example.hiringapp.databinding.CvClubsBinding

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RcClubsAdapter @Inject constructor() : RecyclerView.Adapter<RcClubsAdapter.ClubsVh>() {

    var currentItem: LocalClubItem? = null
    private var dataList: List<LocalClubItem> = listOf()
    private lateinit var click: (clubItem: LocalClubItem) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubsVh {
        val binding = CvClubsBinding.inflate(LayoutInflater.from(parent.context), parent, false)



        return ClubsVh(binding)
    }

    override fun onBindViewHolder(holder: ClubsVh, position: Int) {

        currentItem = dataList[position]
        holder.binding.apply {
            TvClubCountry.text = currentItem!!.country
            TvValueOfClub.text =
                currentItem!!.value.toString()
            TvClubsName.text = currentItem!!.name
            Glide.with(holder.binding.root).load(currentItem!!.image).into(ClubImage)
            holder.binding.root.setOnClickListener { click(dataList[position]) }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun swapData(data: List<LocalClubItem>) {
        this.dataList = data
        notifyDataSetChanged()
    }


    @Singleton
    fun click(callback: (club: LocalClubItem) -> Unit) {
        this.click = callback
    }


    class ClubsVh(val binding: CvClubsBinding) : RecyclerView.ViewHolder(binding.root)

}
