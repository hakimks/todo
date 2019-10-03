package com.hakim.todo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.hakim.todo.models.Todo;
import com.hakim.todo.services.ServiceBuilder;
import com.hakim.todo.services.TodoService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView listView = findViewById(R.id.todo_listview);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceBuilder.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TodoService todoService = retrofit.create(TodoService.class);
        Call<List<Todo>> call = todoService.getTodos();

        call.enqueue(new Callback<List<Todo>>() {

            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                List<Todo> todos = response.body();
                String[] todosTitles = new String[todos.size()];

                for (int i = 0; i < todosTitles.length; i++){
                    todosTitles[i] = todos.get(i).getTitle();
                }

                listView.setAdapter(
                        new ArrayAdapter<String>(
                                getApplicationContext(), android.R.layout.simple_list_item_1, todosTitles
                        )
                );


            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());


            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
