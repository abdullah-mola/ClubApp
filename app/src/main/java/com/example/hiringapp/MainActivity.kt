package com.example.hiringapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.hiringapp.adapter.RcClubsAdapter
import com.example.hiringapp.databinding.ActivityMainBinding
import com.example.hiringapp.viewModels.ClubsViewModel
import com.google.android.material.switchmaterial.SwitchMaterial
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding
    private lateinit var switchbtn: SwitchMaterial
    private lateinit var clubsViewModel: ClubsViewModel
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    lateinit var clubAdapter: RcClubsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding.root
        clubsViewModel = ViewModelProvider(this)[ClubsViewModel::class.java]

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.clubs_fc) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.clubsDetailsFragment,
                R.id.clubsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

    }


    //initializing switch toggle in the app bar with change listener
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem: MenuItem = menu.findItem(R.id.app_bar_switch)
        switchbtn = menuItem.actionView as SwitchMaterial
        switchbtn.text = " Filter By Name "

        navController.addOnDestinationChangedListener { _, destination, _ ->
            switchbtn.visibility =
                if (destination.id == R.id.clubsDetailsFragment) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
        }

        clubsViewModel.getClubsByName(applicationContext)
        clubsViewModel.getLocalClubItem().observe(this) {

            clubAdapter.swapData(it)

        }
        switchbtn.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                clubsViewModel.getClubsByValue(applicationContext)

                switchbtn.text = " Filter By Value "

            } else {
                clubsViewModel.getClubsByName(applicationContext)
                switchbtn.text = " Filter By Name "

            }
        }
        return true

    }


}