package com.kosmos.kotlincourse.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kosmos.kotlincourse.CourseApplication
import com.kosmos.kotlincourse.R
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.domain.repositories.FavoriteRepoRepository
import com.kosmos.kotlincourse.domain.utils.Constants
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import com.kosmos.kotlincourse.presentation.adapters.RepositoriesAdapter
import com.kosmos.kotlincourse.presentation.presenters.ExploreFragmentPresenter
import com.kosmos.kotlincourse.presentation.presenters.MainPresenter
import kotlinx.android.synthetic.main.fragment_explore.*
import javax.inject.Inject

class ExploreFragment : Fragment(), RepositoriesAdapter.AdapterListener, ExploreFragmentPresenter.View {

    @Inject
    lateinit var presenter: ExploreFragmentPresenter
    private lateinit var adapter: RepositoriesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var loadingLayout: ConstraintLayout
    private lateinit var listener: FragmentListener
    private lateinit var swipeContainer: SwipeRefreshLayout
    private lateinit var favoritesRepository: FavoriteRepoRepository
    private var list: List<GitRepository> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: Explore frag create")
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        Log.d(TAG, "onCreate: Explore frag onAttach")
        super.onAttach(context)
        (activity?.application as CourseApplication).getExploreComponent(this).inject(this)
        favoritesRepository = (activity?.application as CourseApplication).getApplicationComponent().getFavoritesDbRepository()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreate: Explore frag create view")
        var view = inflater.inflate(R.layout.fragment_explore, container, false)
        recyclerView = view.findViewById(R.id.all_repos_recyclerview)
        loadingLayout = view.findViewById(R.id.loading_layout)
        swipeContainer = view.findViewById(R.id.swipeContainer)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        )
        if (list.isEmpty()) {
            presenter.getGitRepositories()
        }
        else {
            showCachedRepositories()
        }
        swipeContainer.setOnRefreshListener {
            presenter.refreshGitRepositories()
        }
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(listener: FragmentListener) = ExploreFragment().apply {
            this.listener = listener
        }
    }

    private fun showCachedRepositories() {
        adapter = RepositoriesAdapter(requireContext(), list.toMutableList(), favoritesRepository,this)
        recyclerView.adapter = adapter
    }

    override fun itemClicked(repository: GitRepository) {
        //Log.d(Constants.TAG, "itemClicked: ${repository.name}")
        listener.repositoryClicked(repository)
    }

    override fun likeClicked(repository: GitRepository) {
        presenter.repositoryLikeClicked(repository)
    }

    override fun showGitRepositories(repositories: List<GitRepository>) {
        Log.d(Constants.TAG, "showGitRepositories: show list")
        list = repositories
        adapter = RepositoriesAdapter(requireContext(), repositories.toMutableList(), favoritesRepository,this)
        recyclerView.adapter = adapter
    }

    override fun refreshGitRepositories(repositories: List<GitRepository>) {
        list = repositories
        adapter.clear()
        adapter.addAll(repositories)
        swipeContainer.isRefreshing = false
    }

    override fun showProgress() {
        Log.d(Constants.TAG, "showProgress: LOADING...")
        loadingLayout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        Log.d(Constants.TAG, "hideProgress: HIDE PROGRESS")
        loadingLayout.visibility = View.GONE
    }

    override fun showError(message: String) {
        Log.d(Constants.TAG, "showError: ERROR $message")
    }
    
    interface FragmentListener {
        fun repositoryClicked(repository: GitRepository)
    }
}