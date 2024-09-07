package com.example.postapicallexample

import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(private  val apiService: ApiService) {

    fun createUser(userRequest: UserRequest): Observable<Response<UserResponse>>{
        return apiService.createUser(userRequest)
    }
}