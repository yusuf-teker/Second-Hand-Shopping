package com.yt.graduation.UI.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yt.graduation.UI.Adapters.UsersAdapter
import com.yt.graduation.databinding.FragmentUsersBinding
import com.yt.graduation.main.MainActivity
import com.yt.graduation.model.User

private lateinit var recyclerView: RecyclerView
private lateinit var viewModel: UsersViewModel

class UsersFragment : Fragment() {
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)

        //TODO deprecated
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)


        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        var adapter = UsersAdapter(ArrayList<User>(), requireContext())
        recyclerView.adapter = adapter

        viewModel.usersList.observe(viewLifecycleOwner){
            adapter.refreshData(it)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshSpeeches()
    }

}