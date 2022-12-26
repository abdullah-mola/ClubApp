package com.example.hiringapp.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hiringapp.R
import com.example.hiringapp.adapter.RcClubsAdapter
import com.example.hiringapp.databinding.FragmentClubsBinding
import com.example.hiringapp.viewModels.ClubsViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ClubsFragment : Fragment() {
    @Inject
    lateinit var clubAdapter: RcClubsAdapter
    private lateinit var clubsViewModel: ClubsViewModel
    private var _binding: FragmentClubsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClubsBinding.inflate(inflater, container, false)
        clubsViewModel = ViewModelProvider(this)[ClubsViewModel::class.java]
        setUpRecyclerView()

        context?.let { clubsViewModel.getDataFromServer(it) }


        clubsViewModel.getLocalClubItem().observe(viewLifecycleOwner) {

            clubAdapter.swapData(it)

        }


        context?.getString(R.string.app_name)?.let { setActivityTitle(it) }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clubAdapter.click {
            Navigation.findNavController(binding.root).navigate(
                ClubsFragmentDirections.actionClubsFragmentToClubsDetailsFragment(
                    Gson().toJson(it)
                )
            )

        }


    }

    private fun setUpRecyclerView() = binding.rvAllClubs.apply {
        adapter = clubAdapter
        layoutManager = LinearLayoutManager(context)
    }

    fun Fragment.setActivityTitle(title: String) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }

}