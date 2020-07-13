package com.kosmos.kotlincourse.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kosmos.kotlincourse.R
import com.kosmos.kotlincourse.presentation.ui.ExploreFragment
import com.kosmos.kotlincourse.presentation.ui.FavoritesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val listener: Fragment) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    val tabNames = listOf("Explore", "Favorites")
    val icons = listOf(R.drawable.ic_internet, R.drawable.ic_favorite)
    val fragments : MutableList<Fragment> = mutableListOf()

    override fun getItemCount(): Int = tabNames.count()

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                var fragment = ExploreFragment.newInstance(listener as ExploreFragment.FragmentListener)
                fragments.add(fragment)
                return fragment
            }
            else -> {
                val fragment = FavoritesFragment.newInstance(listener as FavoritesFragment.FragmentListener)
                fragments.add(fragment)
                return fragment
            }
        }
    }

    fun getFragmentAtPosition(position: Int) = fragments[position]
}