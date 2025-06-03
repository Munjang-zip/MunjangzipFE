// 회원가입 요청 함수 포함
package com.example.munjangzip.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.munjangzip.model.RegisterRequest
import com.example.munjangzip.model.RegisterResponse
import com.example.munjangzip.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel : ViewModel() {
    private val _registerResult = MutableLiveData<RegisterResponse?>()
    val registerResult: LiveData<RegisterResponse?> get() = _registerResult

    private var hasRegistered = false

    fun registerUserOnce(nickname: String, libraryName: String, pet: String, petName: String) {
        if (hasRegistered) return

        val request = RegisterRequest(nickname, libraryName, pet, petName)
        RetrofitClient.api.registerUser(request).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful && response.body()?.isSuccess == true) {
                    _registerResult.value = response.body()
                    hasRegistered = true
                } else {
                    Log.e("AuthViewModel", "Register failed: ${response.errorBody()?.string()}")
                    _registerResult.value = null
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e("AuthViewModel", "Register error", t)
                _registerResult.value = null
            }
        })
    }
}