package com.example.postapicallexample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.postapicallexample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val apiViewModel: ApiViewModel by viewModels()
    private val compositeDisposable = CompositeDisposable()
    val user = UserRequest(
        name = "Jakauddin",
        email = "nico@gmail.com",
        password = "1000",
        avatar = "https://picsum.photos/800"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.btnRegister.setOnClickListener{
           // registerUser()

            apiViewModel.createUser(user)

        }


    }

 //   @SuppressLint("CheckResult")
//    fun registerUser(){
//        compositeDisposable.add(
//        RetrofitClient.apiService.createUser(user)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ response ->
//                // Handle the successful response
//                val createdUser = response.body()
//                if (createdUser != null) {
//                    println("User created: ${createdUser.name}")
//                }
//                binding.tvResponse.text = response.body().toString()
//            }, { throwable ->
//                // Handle the error
//                println("Error: ${throwable.message}")
//            }))
//    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }



}