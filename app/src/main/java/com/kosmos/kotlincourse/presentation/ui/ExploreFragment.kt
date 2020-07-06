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
import com.kosmos.kotlincourse.CourseApplication
import com.kosmos.kotlincourse.R
import com.kosmos.kotlincourse.domain.models.GitRepository
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as CourseApplication).getExploreComponent(this).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_explore, container, false)
        recyclerView = view.findViewById(R.id.all_repos_recyclerview)
        loadingLayout = view.findViewById(R.id.loading_layout)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        )
        presenter.getGitRepositories()
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExploreFragment()
    }

    override fun itemClicked(repository: GitRepository) {
        Log.d(Constants.TAG, "itemClicked: ${repository.name}")
    }

    override fun showGitRepositories(repositories: List<GitRepository>) {
        Log.d(Constants.TAG, "showGitRepositories: show list")
        adapter = RepositoriesAdapter(requireContext(), repositories, this)
        recyclerView.adapter = adapter
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
}