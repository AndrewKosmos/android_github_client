package com.kosmos.kotlincourse.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kosmos.kotlincourse.CourseApplication
import com.kosmos.kotlincourse.R
import com.kosmos.kotlincourse.domain.models.Commit
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.domain.models.SessionManager
import com.kosmos.kotlincourse.domain.repositories.FavoriteRepoRepository
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import com.kosmos.kotlincourse.presentation.adapters.CommitsAdapter
import com.kosmos.kotlincourse.presentation.presenters.RepositoryDetailPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_repository_detail.*
import javax.inject.Inject

class RepositoryDetailFragment : Fragment(), RepositoryDetailPresenter.View {

    @Inject lateinit var presenter: RepositoryDetailPresenter
    @Inject lateinit var sessionManager: SessionManager
    private lateinit var adapter: CommitsAdapter
    private lateinit var commitsRecyclerView: RecyclerView
    private lateinit var loadLayout: View
    private lateinit var likeImageView: ImageView
    private lateinit var favoritesRepositoryRef: FavoriteRepoRepository
    private var selectedRepository: GitRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as CourseApplication).getRepoDetailsComponent(this).inject(this)
        favoritesRepositoryRef = (activity?.application as CourseApplication)
            .getApplicationComponent()
            .getFavoritesDbRepository()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repository_detail, container, false)
        loadLayout = view.findViewById(R.id.commitLoadLayout)
        likeImageView = view.findViewById(R.id.detailLikeBtn)
        commitsRecyclerView = view.findViewById(R.id.commitsRecyclerView)
        commitsRecyclerView.layoutManager = LinearLayoutManager(context)
        commitsRecyclerView.addItemDecoration(
            DividerItemDecoration(commitsRecyclerView.context, DividerItemDecoration.VERTICAL)
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        selectedRepository = arguments?.let { RepositoryDetailFragmentArgs.fromBundle(it).selectedRepository }
        selectedRepository?.let {
            showRepositoryInfo(it)
            if ( !it.ownerLogin.isNullOrEmpty() && !it.name.isNullOrEmpty() ) {
                presenter.getCommits(it.ownerLogin, it.name)
            }

            selectedRepository?.fullName?.let {
                favoritesRepositoryRef.isFavorite(it, sessionManager.currentLogin!!).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { value ->
                        run {
                            if (value == 1) {
                                likeImageView.setImageResource(R.drawable.ic_heart_enabled)
                            } else {
                                likeImageView.setImageResource(R.drawable.ic_heart_disabled)
                            }
                        }
                    }
            }

            likeImageView.setOnClickListener {
                presenter.repositoryLikeClicked(selectedRepository!!)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RepositoryDetailFragment()
    }

    override fun showRepositoryInfo(repository: GitRepository) {
        Log.d(TAG, "showRepositoryInfo: Show repos info")
        repository.ownerAvatarUrl?.let {
            Glide.with(this).load(it).into(detailAvatar)
        }
        repository.ownerLogin?.let { detailsRepoAuthor.text = it }
        repository.name?.let { detailRepoName.text = it }
        repository.description?.let { detailRepoDesc.text = it }
        repository.starsCount?.let { detailStarsCount.text = it.toString() }
        repository.forksCount?.let { detailForksCount.text = it.toString() }
    }

    override fun showCommits(commits: List<Commit>) {
        adapter = CommitsAdapter(requireContext(), commits.toMutableList())
        commitsRecyclerView.adapter = adapter
    }

    override fun showLikeViewState(liked: Boolean) {
        if (liked) {
            likeImageView.setImageResource(R.drawable.ic_heart_enabled)
        }
        else {
            likeImageView.setImageResource(R.drawable.ic_heart_disabled)
        }
    }

    override fun showProgress() {
        loadLayout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loadLayout.visibility = View.GONE
    }
}