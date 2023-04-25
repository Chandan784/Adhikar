package com.adhikar.adhikar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.adhikar.adhikar.Adapter.SubDetailAdapter;
import com.adhikar.adhikar.Modal.Controller;
import com.adhikar.adhikar.Modal.LoginResponce;
import com.adhikar.adhikar.Modal.SubDetailModal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubDetailActivity extends AppCompatActivity {
    SubDetailModal subDetailModal;
    SubDetailAdapter adapter;
    List<SubDetailModal> data = new ArrayList<>();
    Intent intent;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_detail);
        intent = getIntent();
        recyclerView = findViewById(R.id.rv_sub_detail);
        String type = intent.getStringExtra("type");
        String title = intent.getStringExtra("title");
        dialog = new ProgressDialog(SubDetailActivity.this);
        dialog.setMessage("Loading");
        dialog.show();
       TextView toolbar =  findViewById(R.id.toolbar);
        toolbar.setText(title);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        Call<List<SubDetailModal>> call = Controller.getInstance().getApi().getSubDetails(type);


        call.enqueue(new Callback<List<SubDetailModal>>() {
            @Override
            public void onResponse(Call<List<SubDetailModal>> call, Response<List<SubDetailModal>> response) {
                data = response.body();
                adapter = new SubDetailAdapter(data, getApplicationContext());
                recyclerView.setAdapter(adapter);
                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<SubDetailModal>> call, Throwable t) {

            }
        });


    }


}