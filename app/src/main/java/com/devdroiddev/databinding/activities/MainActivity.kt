package com.devdroiddev.databinding.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devdroiddev.databinding.adapter.StudentAdapter
import com.devdroiddev.databinding.databinding.ActivityMainBinding
import com.devdroiddev.databinding.interfaces.OnItemClickListener
import com.devdroiddev.databinding.model.User
import com.devdroiddev.databinding.utils.Constant
import com.devdroiddev.databinding.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), OnItemClickListener<User>{

    lateinit var binding : ActivityMainBinding
    lateinit var studentAdapter: StudentAdapter
    private lateinit var layoutManger: LinearLayoutManager
    private lateinit var userList : MutableList<User>
    lateinit var mainViewModel: MainViewModel
    private var isLoading = false
    private var isAllDataLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {

        // Initialization here
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        userList = mutableListOf()
        layoutManger = LinearLayoutManager(this@MainActivity)
        binding.studentListRecycler.layoutManager = layoutManger
        studentAdapter = StudentAdapter(this, userList, this)
        binding.studentListRecycler.adapter = studentAdapter

        // Call the function to get data from api as activity launches
        mainViewModel.getAllUserData()
        displayUserDataOnUi()
        bottomScroll()
    }

    private fun displayUserDataOnUi() {

        // observer to show data on recyclerView
        mainViewModel.userLiveData.observe(this) { user ->
            userList.addAll(user.data)
            studentAdapter.notifyDataSetChanged()
            // if data is less then limit it means this is the last call
            if(user.data.size < Constant.limit){
                isAllDataLoaded = true
                Snackbar.make(binding.root, "Loaded All data", Snackbar.LENGTH_SHORT).show()
            }
        }

        // Observer to maintain the state of loader
        mainViewModel.isApiCall.observe( this) { isApiCall ->
            isLoading = isApiCall
            if (isApiCall) {
                if (mainViewModel.page == 0) {
                    binding.progressBarFirstTime.visibility = View.VISIBLE
                } else {
                    binding.bottomProgressBar.visibility = View.VISIBLE
                }
            }
            else {
                binding.progressBarFirstTime.visibility = View.GONE
                binding.bottomProgressBar.visibility = View.GONE
            }
        }
    }

    // function to call api when user reached at last item of recyclerView
    private fun bottomScroll() {
        binding.studentListRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (layoutManger.findLastVisibleItemPosition() == layoutManger.itemCount - 1) {
                    // Reached the last item, load more data
                    if (!isLoading && !isAllDataLoaded) {
                        mainViewModel.getAllUserData()
                    }
                }
            }
        })
    }

    override fun onItemClicked(model: User) {
        val intent = Intent(this@MainActivity, UserDetailActivity::class.java)
        intent.putExtra("studentId", model.id)
        startActivity(intent)
    }
}

