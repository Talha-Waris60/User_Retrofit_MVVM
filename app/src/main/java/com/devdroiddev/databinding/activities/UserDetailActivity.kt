package com.devdroiddev.databinding.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devdroiddev.databinding.databinding.ActivityShowStudentBinding
import com.devdroiddev.databinding.viewModel.UserDetailViewModel

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowStudentBinding
    private lateinit var userId: String
    lateinit var userDetailViewModel: UserDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialization
        init()

    }

    private fun init() {

        // Initialization here
        userDetailViewModel = ViewModelProvider(this).get(UserDetailViewModel::class.java)
        getUserDetail()

    }

    private fun getUserDetail() {

        // Initialization here
        userId = intent.getStringExtra("studentId").toString() ?: "0"

        // Call viewModel Function
        userDetailViewModel.getUserById(userId)

        // Observe the data and pass to viewModel
        userDetailViewModel.userLiveData.observe(this) { currentStudent ->
            binding.student = currentStudent
        }

        // Main the state of Loader
        userDetailViewModel.isApiCalling.observe(this) { isCalling ->
            if (isCalling) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}