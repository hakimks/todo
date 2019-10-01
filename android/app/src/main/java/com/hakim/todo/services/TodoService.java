package com.hakim.todo.services;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoService {
    @GET("todos")
    Call<String> getTodos();
}
