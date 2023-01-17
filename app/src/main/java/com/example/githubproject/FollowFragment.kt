package com.example.githubproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubproject.adapter.UserAdapter
import com.example.githubproject.databinding.FragmentFollowBinding
import com.example.githubproject.viewModel.DetailViewModel
import com.example.githubproject.viewModel.Results

class FollowFragment : Fragment() {

    private var binding:FragmentFollowBinding? = null
    private val adapter by lazy {
        UserAdapter{

        }
    }
    private val viewModel by activityViewModels<DetailViewModel>()
    var type = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvFollow?.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
            adapter = this@FollowFragment.adapter
        }

        when (type) {
            followers -> {
                viewModel.resultFollowers.observe(viewLifecycleOwner, this::manageResultFollow)
            }
            followings -> {
                viewModel.resultFollowing.observe(viewLifecycleOwner, this::manageResultFollow)
            }
        }
    }

    private fun manageResultFollow(state: Results){
        when (state){
            is Results.Success<*> -> {
                adapter.setData(state.data as MutableList<ResponseUser.Items>)
            }
            is Results.Error -> {
                Toast.makeText(requireActivity(), state.exception.message.toString(), Toast.LENGTH_SHORT).show()
            }
            is Results.Loading -> {
                binding?.progressBar?.isVisible = state.isLoading
            }
        }
    }

    companion object {
        const val followers = 0
        const val followings = 1
        fun newInstance(type: Int) = FollowFragment()
            .apply{
                this.type = type
            }
    }
}