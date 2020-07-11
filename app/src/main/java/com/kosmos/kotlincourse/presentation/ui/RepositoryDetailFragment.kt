package com.kosmos.kotlincourse.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kosmos.kotlincourse.R

class RepositoryDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repository_detail, container, false)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RepositoryDetailFragment()
    }
}