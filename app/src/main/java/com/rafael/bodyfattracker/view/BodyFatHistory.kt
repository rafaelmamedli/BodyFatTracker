package com.rafael.bodyfattracker.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafael.bodyfattracker.adapter.RecyclerViewAdapter
import com.rafael.bodyfattracker.adapter.SwipeDeleteCallBack
import com.rafael.bodyfattracker.data.model.BodyFatModel
import com.rafael.bodyfattracker.databinding.FragmentBodyFatHistoryBinding
import com.rafael.bodyfattracker.util.gone
import com.rafael.bodyfattracker.util.show
import com.rafael.bodyfattracker.util.toast
import com.rafael.bodyfattracker.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BodyFatHistory : Fragment() {

    lateinit var binding: FragmentBodyFatHistoryBinding
    private val viewModel: ViewModel by viewModels()
    lateinit var adapter: RecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBodyFatHistoryBinding.inflate(layoutInflater)
        showSwipeToDeleteToast()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = RecyclerViewAdapter(ArrayList())
        binding.recyclerView.adapter = adapter
        getData()
    }

    private fun showSwipeToDeleteToast() {
        val sharedPreferences =
            requireActivity().getSharedPreferences("ToastPrefs", Context.MODE_PRIVATE)
        if (!sharedPreferences.getBoolean("isToastShown", false)) {
            toast("Swipe for delete")
            val editor = sharedPreferences.edit()
            editor.putBoolean("isToastShown", true)
            editor.apply()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData(){
        viewModel.getAllResults().observe(viewLifecycleOwner) { values ->
            val list: ArrayList<BodyFatModel> = arrayListOf()
            if (values.isNullOrEmpty()) {
                binding.empty.show()
            } else {
                binding.empty.gone()
                list.addAll(values)
            }
            list.reverse()
            adapter.list = list
            adapter.notifyDataSetChanged()

            val swipeToDeleteBack = object : SwipeDeleteCallBack(requireContext()) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val item = adapter.list[position]
                    list.removeAt(position)
                    viewModel.delete(item)
                    adapter.notifyItemRemoved(position)
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeToDeleteBack)
            itemTouchHelper.attachToRecyclerView(binding.recyclerView)
        }
    }
}

