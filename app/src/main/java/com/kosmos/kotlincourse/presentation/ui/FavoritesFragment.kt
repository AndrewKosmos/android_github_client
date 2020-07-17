package com.kosmos.kotlincourse.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kosmos.kotlincourse.CourseApplication
import com.kosmos.kotlincourse.R
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import com.kosmos.kotlincourse.presentation.adapters.FavoritesAdapter
import com.kosmos.kotlincourse.presentation.presenters.FavoriteFragmentPresenter
import javax.inject.Inject

class FavoritesFragment : Fragment(), FavoriteFragmentPresenter.View, FavoritesAdapter.AdapterListener {

    @Inject
    lateinit var presenter: FavoriteFragmentPresenter
    private lateinit var listener: FavoritesFragment.FragmentListener
    private lateinit var loadingLayout: View
    private lateinit var emptyStateLayout: View
    private lateinit var adapter: FavoritesAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as CourseApplication).getFavoritesComponent(this).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_favourites, container, false)
        loadingLayout = view.findViewById(R.id.fav_loading_layout)
        emptyStateLayout = view.findViewById(R.id.empty_state_layout)
        recyclerView = view.findViewById(R.id.favorite_repos_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        )
        presenter.getAllFavorites()
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(listener: FavoritesFragment.FragmentListener) =
            FavoritesFragment().apply {
                this.listener = listener
            }
    }

    override fun showFavorites(favoritesList: List<GitRepository>) {
        adapter = FavoritesAdapter(requireContext(),favoritesList.toMutableList(),this)
        recyclerView.adapter = adapter
    }

    override fun showEmptyState() {
        emptyStateLayout.visibility = View.VISIBLE
    }

    override fun hideEmptyState() {
        emptyStateLayout.visibility = View.GONE
    }

    override fun showProgress() {
        loadingLayout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loadingLayout.visibility = View.GONE
    }

    override fun itemClicked(repository: GitRepository) {
        listener.repositoryClicked(repository)
    }

    fun reloadFavorites() {
        presenter.getAllFavorites()
    }

    fun favoriteAdded(repository: GitRepository) {
        Log.d(TAG, "favoriteAdded: Added $repository")
    }

    fun favoriteDeleted(repository: GitRepository) {
        Log.d(TAG, "favoriteDeleted: Deleted $repository")
    }

    interface FragmentListener {
        fun repositoryClicked(repository: GitRepository)
    }
}