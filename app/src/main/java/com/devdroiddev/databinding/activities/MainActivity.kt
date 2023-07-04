package com.devdroiddev.databinding.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devdroiddev.databinding.R
import com.devdroiddev.databinding.adapter.StudentAdapter
import com.devdroiddev.databinding.api.RetrofitInstance
import com.devdroiddev.databinding.databinding.ActivityMainBinding
import com.devdroiddev.databinding.interfaces.OnItemClickListener
import com.devdroiddev.databinding.model.DataModel
import com.devdroiddev.databinding.model.NewModel
import com.devdroiddev.databinding.model.PostModel
import com.devdroiddev.databinding.repository.Repository
import com.devdroiddev.databinding.repository.StudentRepository
import com.devdroiddev.databinding.utils.Constant
import com.devdroiddev.databinding.utils.Constant.Companion.APP_ID
import com.devdroiddev.databinding.viewModel.MainViewModel

import com.devdroiddev.databinding.viewModelFactory.MainViewModelFactory

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.HTTP
import javax.security.auth.callback.Callback



class MainActivity : AppCompatActivity(), OnItemClickListener<NewModel>{
    lateinit var binding : ActivityMainBinding
    lateinit var studentAdapter: StudentAdapter
    private var studentRecordsList = mutableListOf<NewModel>()
    lateinit var mainViewModel: MainViewModel
    private lateinit var layoutManger: LinearLayoutManager
    private var isLoading = false
    private var isAllDataLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Initialize Adapter Here
        layoutManger = LinearLayoutManager(this@MainActivity)
        binding.studentListRecycler.layoutManager = layoutManger
        studentAdapter = StudentAdapter(this, studentRecordsList, this)
        binding.studentListRecycler.adapter = studentAdapter
        setupRecyclerView()

        mainViewModel.loadStudentData()

        mainViewModel.studentData.observe(this, Observer {
            studentRecordsList.addAll(it.data)
            studentAdapter.notifyDataSetChanged()
            if(it.data.size < Constant.limit){
                isAllDataLoaded = true
            }
        })

        mainViewModel.callingApi.observe( this, Observer {isApiCall ->
            isLoading = isApiCall
            if (isApiCall) {
                if (mainViewModel.page == 0) {
                    binding.progressBarFirstTime.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
            else {
                binding.progressBarFirstTime.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun setupRecyclerView() {
        binding.studentListRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                // val lastVisibleItemPosition = layoutManger.findLastVisibleItemPosition()
                // val totalItemCount = layoutManger.itemCount

                if (layoutManger.findLastVisibleItemPosition() == layoutManger.itemCount - 1) {
                    // Reached the last item, load more data
                    if (!isLoading && !isAllDataLoaded) {
                        mainViewModel.loadStudentData()
                    }
                }
            }
        })
    }

    private fun getStudentRecord() {
      /*  if (mainViewModel.page == 0 ) {
            binding.progressBarFirstTime.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.VISIBLE
        }
        val retrofitService = RetrofitInstance.interfaceInstance
        CoroutineScope(Dispatchers.IO).launch {
            val response = retrofitService.getALLStudent(page, limit, APP_ID)
            isApiCall = false
            withContext(Dispatchers.Main) {
                try {
                    if (page == 0) {
                        binding.progressBarFirstTime.visibility = View.GONE
                    } else {
                        binding.progressBar.visibility = View.GONE
                    }

                    binding.progressBar.visibility = View.GONE
                    if (response != null) {
                        studentRecordsList.addAll(response.body()!!.data)
                        studentAdapter.notifyDataSetChanged()
                        page++
                    } else {
                        Toast.makeText(this@MainActivity, response.code(), Toast.LENGTH_SHORT).show()
                    }
                } catch (e : HttpException) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }*/
    }

    override fun onItemClicked(model: NewModel) {
        val intent = Intent(this@MainActivity, ShowStudentActivity::class.java)
        intent.putExtra("studentId", model.id)
        startActivity(intent)
    }
}

