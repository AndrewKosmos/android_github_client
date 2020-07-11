package com.kosmos.kotlincourse.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kosmos.kotlincourse.R
import com.kosmos.kotlincourse.presentation.ui.ExploreFragment
import com.kosmos.kotlincourse.presentation.ui.FavouritesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val listener: Fragment) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    val tabNames = listOf("Explore", "Favorites")
    val icons = listOf(R.drawable.ic_internet, R.drawable.ic_favorite)

    override fun getItemCount(): Int = tabNames.count()

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ExploreFragment.newInstance(listener as ExploreFragment.FragmentListener)
            else -> FavouritesFragment.newInstance()
        }
    }
}