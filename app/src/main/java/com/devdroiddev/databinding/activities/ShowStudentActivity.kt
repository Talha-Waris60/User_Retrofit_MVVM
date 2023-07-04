package com.devdroiddev.databinding.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devdroiddev.databinding.R
import com.devdroiddev.databinding.api.RetrofitInstance
import com.devdroiddev.databinding.api.StudentService
import com.devdroiddev.databinding.databinding.ActivityShowStudentBinding
import com.devdroiddev.databinding.model.NewModel
import com.devdroiddev.databinding.repository.StudentRepository
import com.devdroiddev.databinding.utils.Constant
import com.devdroiddev.databinding.utils.Constant.Companion.APP_ID
import com.devdroiddev.databinding.viewModel.ShowStudentViewModel
import com.devdroiddev.databinding.viewModelFactory.ShowStudentFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import javax.security.auth.callback.Callback

class ShowStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowStudentBinding
    private val APP_TAG = "Student_Record"
    private lateinit var studentId: String
    lateinit var showStudentViewModel: ShowStudentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        studentId = intent.getStringExtra("studentId")!!
        showStudentViewModel = ViewModelProvider(this).get(ShowStudentViewModel::class.java)

        // Call viewModel Function
        showStudentViewModel.loadStudentById(studentId)

        showStudentViewModel.currentStudent.observe(this, Observer { currentStudent ->
            binding.student = currentStudent
        })

        showStudentViewModel.isCalling.observe(this, Observer { isCalling ->
            if (isCalling) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })
    }
}