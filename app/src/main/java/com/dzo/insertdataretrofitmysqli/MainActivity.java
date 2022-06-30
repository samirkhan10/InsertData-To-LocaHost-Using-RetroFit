package com.dzo.insertdataretrofitmysqli;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,email;
    private static final String url = "http://10.0.2.2/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
    }

    public void insertData(View view) {
        processData();

    }

    private void processData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyInterface minterface = retrofit.create(MyInterface.class);

        Call<myModel> call = minterface.addData(name.getText().toString(),email.getText().toString());

        call.enqueue(new Callback<myModel>() {
            @Override
            public void onResponse(Call<myModel> call, Response<myModel> response) {
                name.setText("");
                email.setText("");
                Toast.makeText(MainActivity.this, "Data Has Inserted"+response, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<myModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();

            }
        });

    }
}