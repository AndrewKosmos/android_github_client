package com.kosmos.kotlincourse.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kosmos.kotlincourse.R
import com.kosmos.kotlincourse.domain.models.GitRepository
import com.kosmos.kotlincourse.domain.utils.Constants
import de.hdodenhof.circleimageview.CircleImageView

class FavoritesAdapter(
    private val context: Context,
    private var repositories: MutableList<GitRepository>,
    private val listener: FavoritesAdapter.AdapterListener
) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.repository_item, parent, false)
        return FavoritesAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.likeImageView.visibility = View.GONE
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
        }

        holder.itemView.setOnClickListener {
            listener.itemClicked(repository)
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
    }
}