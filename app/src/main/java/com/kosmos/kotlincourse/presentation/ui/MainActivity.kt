package com.kosmos.kotlincourse.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

class MainActivity : AppCompatActivity(), MainView{

    //@Inject lateinit var mainPresenter: MainPresenter
    //private lateinit var adapter: RepositoriesAdapter
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager,
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = viewPagerAdapter.tabNames[position]
            tab.icon = resources.getDrawable(viewPagerAdapter.icons[position], theme)
        }).attach()
        //val navigation = Navigation.findNavController(this, R.id.navFragment)
        //NavigationUI.setupWithNavController(bottom_nav_view, navigation)
        //all_repos_recyclerview.layoutManager = LinearLayoutManager(this)
        //all_repos_recyclerview.addItemDecoration(
        //    DividerItemDecoration(all_repos_recyclerview.context, DividerItemDecoration.VERTICAL)
        //)

        (application as CourseApplication).getMainComponent(this).inject(this)
        //mainPresenter.getGitRepositories()
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_menu, menu)
        return true
    }*/

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = Navigation.findNavController(this, R.id.navFragment)
        val navigated = NavigationUI.onNavDestinationSelected(item!!, navController)
        return navigated || super.onOptionsItemSelected(item)
    }*/

    override fun showGitRepositories(repositories: List<GitRepository>) {
        //Log.d(TAG, "showGitRepositories: show list")
        //adapter = RepositoriesAdapter(this, repositories, this)
        //all_repos_recyclerview.adapter = adapter
    }

    override fun showProgress() {
        Log.d(TAG, "showProgress: LOADING...")
        //loading_layout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        Log.d(TAG, "hideProgress: HIDE PROGRESS")
        //loading_layout.visibility = View.GONE
    }

    override fun showError(message: String) {
        Log.d(TAG, "showError: ERROR $message")
    }
}