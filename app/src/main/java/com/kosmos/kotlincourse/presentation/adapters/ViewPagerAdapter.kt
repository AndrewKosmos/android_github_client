package com.kosmos.kotlincourse.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kosmos.kotlincourse.R
import com.kosmos.kotlincourse.presentation.ui.ExploreFragment
import com.kosmos.kotlincourse.presentation.ui.FavouritesFragment

class ViewPagerAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    val tabNames = listOf("Explore", "Favorites")
    val icons = listOf(R.drawable.ic_internet, R.drawable.ic_favorite)

    override fun getItemCount(): Int = tabNames.count()

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ExploreFragment.newInstance()
            else -> FavouritesFragment.newInstance()
        }
    }
}