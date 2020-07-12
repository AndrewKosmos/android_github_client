package com.kosmos.kotlincourse.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kosmos.kotlincourse.R
import com.kosmos.kotlincourse.domain.models.Commit
import com.kosmos.kotlincourse.domain.utils.Constants.Companion.TAG
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.commit_item.view.*
import java.time.*
import java.time.format.DateTimeFormatter

class CommitsAdapter(private val context: Context,
                     private var commitsList: MutableList<Commit>
) : RecyclerView.Adapter<CommitsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.commit_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = commitsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commit = commitsList[position]
        with(commit) {
            commitDetail?.message?.let { holder.commitText.text = it }
            commitDetail?.author?.date?.let {
                val instant = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(it))
                val date = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.id))
                holder.dateText.text = "${date.dayOfMonth}.${date.monthValue}.${date.year}"
            }
            author?.avatarUrl?.let { Glide.with(context).load(it).into(holder.authorImg) }
            if ( !author?.login.isNullOrEmpty() ) {
                holder.authorLoginText.text = author?.login
            }
            else {
                commitDetail?.author?.name?.let { holder.authorLoginText.text = it }
            }
        }
    }

    class ViewHolder : RecyclerView.ViewHolder {
        var commitText: TextView
        var dateText: TextView
        var authorImg: CircleImageView
        var authorLoginText: TextView

        constructor(itemView: View) : super(itemView) {
            commitText = itemView.findViewById(R.id.commitText)
            dateText = itemView.findViewById(R.id.commitDateText)
            authorImg = itemView.findViewById(R.id.commitAvatar)
            authorLoginText = itemView.findViewById(R.id.commitAuthorText)
        }

    }
}