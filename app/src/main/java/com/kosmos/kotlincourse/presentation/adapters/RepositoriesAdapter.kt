package com.kosmos.kotlincourse.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kosmos.kotlincourse.R
import com.kosmos.kotlincourse.data.repositories.FavoriteRepoRepositoryImpl
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.domain.repositories.FavoriteRepoRepository
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import com.kosmos.kotlincourse.presentation.ui.MainActivity
import de.hdodenhof.circleimageview.CircleImageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class RepositoriesAdapter(
    private val context: Context,
    private var repositories: MutableList<GitRepository>,
    private var favoritesRepository: FavoriteRepoRepository,
    private val listener: AdapterListener) : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    fun clear() {
        repositories.clear()
        notifyDataSetChanged()
    }

    fun addAll(values: List<GitRepository>) {
        repositories.addAll(values)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.repository_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = repositories[position]
        with(repository) {
            if (ownerAvatarUrl != null && ownerAvatarUrl.isNotEmpty()) {
                Glide.with(context).load(ownerAvatarUrl).into(holder.avatarView)
            }
            else {
                holder.avatarView.setImageResource(R.mipmap.ic_launcher)
            }
            name?.let { holder.repositoryNameView.text = it }
            ownerLogin?.let { holder.profileNameView.text = it }
            description?.let { holder.repositoryDescView.text = it }
            holder.starsCountView.text = starsCount?.toString() ?: "0"
            holder.forksCountView.text = forksCount?.toString() ?: "0"
            language?.let {
                holder.languageImageView.isVisible = true
                holder.languageView.text = it
            }

            favoritesRepository.isFavorite(fullName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer { value ->
                    if (value == 1) {
                        holder.likeImageView.setImageResource(R.drawable.ic_heart_enabled)
                        holder.isFavorite = true
                    }
                    else {
                        holder.likeImageView.setImageResource(R.drawable.ic_heart_disabled)
                        holder.isFavorite = false
                    }
                })
        }

        holder.itemView.setOnClickListener {
            Log.d(TAG, "onBindViewHolder: Clicked item")
            listener.itemClicked(repository)
        }

        holder.likeImageView.setOnClickListener {
            if (holder.isFavorite) {
                holder.likeImageView.setImageResource(R.drawable.ic_heart_disabled)
            }
            else
            {
                holder.likeImageView.setImageResource(R.drawable.ic_heart_enabled)
            }
            listener.likeClicked(repository)
        }
    }

    class ViewHolder : RecyclerView.ViewHolder {
        var avatarView: CircleImageView
        var profileNameView: TextView
        var repositoryNameView : TextView
        var repositoryDescView: TextView
        var starsCountView: TextView
        var forksCountView: TextView
        var languageView: TextView
        var languageImageView: ImageView
        var likeImageView: ImageView
        var isFavorite = false

        constructor(itemView: View) : super(itemView) {
            avatarView = itemView.findViewById(R.id.profile_image)
            profileNameView = itemView.findViewById(R.id.profile_name)
            repositoryNameView = itemView.findViewById(R.id.repository_name)
            repositoryDescView = itemView.findViewById(R.id.repository_desc)
            starsCountView = itemView.findViewById(R.id.stars_count_tv)
            forksCountView = itemView.findViewById(R.id.forks_count_tv)
            languageView = itemView.findViewById(R.id.lang_tv)
            languageImageView = itemView.findViewById(R.id.img_lang)
            likeImageView = itemView.findViewById(R.id.img_btn_like)
        }
    }

    interface AdapterListener {
        fun itemClicked(repository: GitRepository)
        fun likeClicked(repository: GitRepository)
    }

}