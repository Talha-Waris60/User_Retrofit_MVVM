
package com.devdroiddev.databinding.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devdroiddev.databinding.api.RetrofitInstance
import com.devdroiddev.databinding.model.DataModel
import com.devdroiddev.databinding.model.NewModel
import com.devdroiddev.databinding.model.PostModel
import com.devdroiddev.databinding.repository.Repository
import com.devdroiddev.databinding.repository.StudentRepository
import com.devdroiddev.databinding.utils.Constant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

class MainViewModel() : ViewModel() {
    // var studentLiveData = MutableLiveData<List<NewModel>?>()
    // var isApiCall = MutableLiveData<Boolean>(false)
    private val studentRepository : StudentRepository = StudentRepository()
    private val studentLiveData = MutableLiveData<DataModel>()
    private var isApiCall = MutableLiveData<Boolean>(false)
    var page: Int = 0

    fun loadStudentData() {
        Log.d(Constant.APP_TAG, "Calling Api")
        isApiCall.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = studentRepository.getAllStudent(page, Constant.limit)
            if (result.body() != null) {
                studentLiveData.postValue(result.body())
            }
            page++
            isApiCall.postValue(false)
        }
    }

    // Access the live data of Repository
     val studentData : LiveData<DataModel>
          get() = studentLiveData

    val callingApi : LiveData<Boolean>
        get() = isApiCall

    /* fun loadData() {
          // Log.d(Constant.APP_TAG, "${isApiCall.value}")
          isApiCall.value = true
         // Log.d(Constant.APP_TAG, "${isApiCall.value}")
          //Log.d(Constant.APP_TAG, "${isApiCall.value}")
          val retrofitService = RetrofitInstance.studentService
          CoroutineScope(Dispatchers.IO).launch {
               val response = retrofitService.getALLStudent(page, limit, Constant.APP_ID)
               isApiCall.postValue(false)
               withContext(Dispatchers.Main) {
                       try {
                            if (response != null) {
                                 studentLiveData.value = response.body()!!.data
                                 page++
                            }
                       } catch (e: HttpException) {
                            Log.d("Student_Record", e.message.toString())
                            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                       }
                  }

          }
     }*/
}