package com.example.hiringapp.ui

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.hiringapp.R
import com.example.hiringapp.data.local.LocalClubItem
import com.example.hiringapp.databinding.FragmentClubsDetailsBinding
import com.google.gson.Gson


class ClubsDetailsFragment : Fragment() {
    private val args: ClubsDetailsFragmentArgs by navArgs()
    private var _binding: FragmentClubsDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClubsDetailsBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val clubName = args.name
        val club = Gson().fromJson(clubName, LocalClubItem::class.java)
        val details = SpannableStringBuilder()
            .append(context?.resources?.getString(R.string.the_club))
            .bold { append(" " + club.name) }
            .append(
                " ${context?.resources?.getString(R.string.from).toString()} ${club.country}  ${
                    context?.resources?.getString(R.string.value)
                        .toString()
                }  ${club.value}  ${context?.resources?.getString(R.string.euro).toString()}\n\n"
            )
            .bold { append(club.name) }
            .append(
                " ${
                    context?.resources?.getString(R.string.achievements1).toString()
                } ${club.european_titles} ${
                    context?.resources?.getString(R.string.achievements2).toString()
                }"
            )




        Glide.with(binding.root).load(club.image).into(binding.IvClub)

        binding.tvClubsNames.text = club.country

        binding.tvClubsDetails.text = details


        setActivityTitle(club.name)
    }

    fun Fragment.setActivityTitle(title: String) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }
}