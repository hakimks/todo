package com.hakim.todo.services;

import com.hakim.todo.models.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoService {
    @GET("todos")
    Call<List<Todo>> getTodos();
}
