package com.example.postapicallexample

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel(){

    @SuppressLint("CheckResult")
    fun createUser(userRequest: UserRequest){
        apiRepository.createUser(userRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                // Handle the successful response
                val createdUser = response.body()
               // binding.tvResponse.text = response.body().toString()
               // return@subscribe response.body()
                Log.d("Response", response.body().toString())
            }, { throwable ->
                // Handle the error
                Log.d("Response", "Error: ${throwable.message}")
                //println("Error: ${throwable.message}")
            })


    }
}