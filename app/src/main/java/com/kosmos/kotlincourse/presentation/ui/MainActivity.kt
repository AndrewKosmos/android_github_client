package com.kosmos.kotlincourse.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.tabs.TabLayoutMediator
import com.kosmos.kotlincourse.CourseApplication
import com.kosmos.kotlincourse.R
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.presentation.presenters.MainPresenter
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import com.kosmos.kotlincourse.presentation.adapters.RepositoriesAdapter
import com.kosmos.kotlincourse.presentation.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainPresenter.MainView, MainFragment.MainFragmentListener {

    @Inject lateinit var mainPresenter: MainPresenter
    private lateinit var navigation: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation = Navigation.findNavController(this, R.id.navFragment)
        NavigationUI.setupActionBarWithNavController(this, navigation)
        (application as CourseApplication).getMainComponent(this).inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_sign_out -> {
                mainPresenter.signOut()
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean = navigation.navigateUp()

    override fun moveToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showError(message: String) {
        Log.d(TAG, "showError: ERROR $message")
    }

    override fun repositoryClicked(repository: GitRepository) {
        val action = MainFragmentDirections.actionMainFragmentToRepositoryDetailFragment()
        action.selectedRepository = repository
        navigation.navigate(action)
    }
}