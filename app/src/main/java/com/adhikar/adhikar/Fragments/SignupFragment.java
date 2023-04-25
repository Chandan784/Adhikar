package com.adhikar.adhikar.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adhikar.adhikar.MainActivity;
import com.adhikar.adhikar.Modal.Controller;
import com.adhikar.adhikar.Modal.LoginResponce;
import com.adhikar.adhikar.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupFragment extends Fragment {

    private EditText et_phone, et_name, et_age, et_password;

    private Button bt_login, bt_signup;
    SharedPreferences preferences;
    private ProgressDialog dialog;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        bt_login = view.findViewById(R.id.bt_login);
        bt_signup = view.findViewById(R.id.bt_signup);
        et_phone = view.findViewById(R.id.et_phone);
        et_age = view.findViewById(R.id.et_age);
        et_password = view.findViewById(R.id.et_pwd);
        et_name = view.findViewById(R.id.et_name);
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait");
        preferences = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                LoginFragment loginFragment = new LoginFragment();
                fragmentTransaction.replace(R.id.auth_frame, loginFragment);
                fragmentTransaction.commit();

            }
        });





        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bt_signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                if (!et_phone.getText().toString().equals("")) {
                    if (!et_name.getText().toString().equals("")) {

                        if (!et_age.getText().toString().equals("")) {
                            if (!et_password.getText().toString().equals("")) {

                                dialog.show();

                                Call<LoginResponce> call = Controller.getInstance().getApi().signUp(et_name.getText().toString(), et_phone.getText().toString(), et_age.getText().toString(), et_password.getText().toString(), "2023-03-07 02:06:33");
                                call.enqueue(new Callback<LoginResponce>() {
                                    @Override
                                    public void onResponse(Call<LoginResponce> call, Response<LoginResponce> response) {

                                        LoginResponce obj = response.body();
                                        Toast.makeText(view.getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                                        Log.d("reponce", response.body().getMessage());
                                        Toast.makeText(getContext(), obj.getMessage(), Toast.LENGTH_LONG).show();
                                        if (obj.getMessage().equals("Inserted")) {
                                          dialogdismis();
                                            SharedPreferences.Editor editor = preferences.edit();
                                            editor.putBoolean("isUserLogin", true);
                                            editor.commit();

                                            Toast.makeText(getContext(), "Login Sucess", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getContext(), MainActivity.class);
                                            startActivity(intent);
                                            getActivity().finish();
                                        }else {
                                            dialogdismis();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<LoginResponce> call, Throwable t) {



                                      dialogdismis();


                                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                        Log.d("rseponce", t.getMessage());
                                    }
                                });

                            } else {
                                et_password.setError("Please enter password");
                            }

                        } else {
                            et_age.setError("Please enter age");
                        }
                    } else {
                        et_name.setError("Please enter full name");
                    }

                } else {
                    et_phone.setError("Please enter phone number");
                }

            }
        });

    }

   private void dialogdismis(){
       if( dialog.isShowing()){

           dialog.hide();
           dialog.cancel();
           dialog.dismiss();
       }
   }
}