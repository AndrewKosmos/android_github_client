package com.kosmos.kotlincourse.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kosmos.kotlincourse.R
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import com.kosmos.kotlincourse.presentation.adapters.ViewPagerAdapter

class MainFragment : Fragment(), ExploreFragment.FragmentListener, FavoritesFragment.FragmentListener {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var listener: MainFragmentListener
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_main, container, false)
        listener = requireActivity() as MainFragmentListener
        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle, this)
        viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = viewPagerAdapter.tabNames[position]
                tab.icon = resources.getDrawable(viewPagerAdapter.icons[position], requireActivity().theme)
            }).attach()

        /*viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val fragment = viewPagerAdapter.getFragmentAtPosition(position)
                if (fragment is FavoritesFragment) {
                    fragment.reloadFavorites()
                }
                super.onPageSelected(position)
            }
        })*/
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun repositoryClicked(repository: GitRepository) {
        Log.d(TAG, "repositoryClicked: MainFragment")
        listener.repositoryClicked(repository)
    }

    override fun favoriteDeleted(repository: GitRepository) {

    }

    override fun favoriteAdded(repository: GitRepository) {

    }

    interface MainFragmentListener {
        fun repositoryClicked(repository: GitRepository)
    }
}