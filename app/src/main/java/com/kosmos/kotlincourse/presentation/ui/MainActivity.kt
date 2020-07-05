package com.kosmos.kotlincourse.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kosmos.kotlincourse.CourseApplication
import com.kosmos.kotlincourse.R
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.presentation.presenters.MainPresenter
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import com.kosmos.kotlincourse.presentation.adapters.RepositoriesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView, RepositoriesAdapter.AdapterListener {

    @Inject lateinit var mainPresenter: MainPresenter
    private lateinit var adapter: RepositoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        all_repos_recyclerview.layoutManager = LinearLayoutManager(this)
        all_repos_recyclerview.addItemDecoration(
            DividerItemDecoration(all_repos_recyclerview.context, DividerItemDecoration.VERTICAL)
        )

        (application as CourseApplication).getMainComponent(this).inject(this)
        mainPresenter.getGitRepositories()
    }

    override fun showGitRepositories(repositories: List<GitRepository>) {
        Log.d(TAG, "showGitRepositories: show list")
        adapter = RepositoriesAdapter(this, repositories, this)
        all_repos_recyclerview.adapter = adapter
    }

    override fun showProgress() {
        Log.d(TAG, "showProgress: LOADING...")
        loading_layout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        Log.d(TAG, "hideProgress: HIDE PROGRESS")
        loading_layout.visibility = View.GONE
    }

    override fun showError(message: String) {
        Log.d(TAG, "showError: ERROR $message")
    }

    override fun itemClicked(repository: GitRepository) {
        Log.d(TAG, "itemClicked: ${repository.name}")
    }
}