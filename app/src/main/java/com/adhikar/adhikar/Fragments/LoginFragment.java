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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.adhikar.adhikar.MainActivity;
import com.adhikar.adhikar.Modal.Controller;
import com.adhikar.adhikar.Modal.LoginResponce;
import com.adhikar.adhikar.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {


    private SharedPreferences preferences;
    EditText et_phone;
    EditText et_password;
    Button bt_login, bt_signup;
    FrameLayout frameLayout;
    private ProgressDialog dialog;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        et_phone = view.findViewById(R.id.et_phone);

        et_password = view.findViewById(R.id.et_pwd);
        bt_login = view.findViewById(R.id.bt_login);
        bt_signup = view.findViewById(R.id.bt_signup);
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait");
        preferences = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
//        if (preferences.contains("isUserLogin")) {
//            Intent intent = new Intent(getContext(), MainActivity.class);
//            startActivity(intent);
//        }

        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SignupFragment signupFragment = new SignupFragment();
                fragmentTransaction.replace(R.id.auth_frame, signupFragment);
                fragmentTransaction.commit();

            }
        });
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        bt_login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (!et_phone.getText().toString().equals("")) {
                    if (!et_password.getText().toString().equals("")) {

                        dialog.show();
                        Call<LoginResponce> call = Controller.getInstance().getApi().logIn(et_phone.getText().toString(), et_password.getText().toString());
                        call.enqueue(new Callback<LoginResponce>() {
                            @Override
                            public void onResponse(Call<LoginResponce> call, Response<LoginResponce> response) {
                                LoginResponce obj = response.body();
                                //Toast.makeText(view.getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                               Log.d("reponce", response.body().getMessage());
                                Toast.makeText(getContext(), obj.getMessage(), Toast.LENGTH_LONG).show();
                                if (obj.getMessage().equals("Exist")) {
                                    if (isAdded()) {
                                        dialog.dismiss();
                                    }
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putBoolean("isUserLogin", true);
                                    editor.commit();

                                    Toast.makeText(getContext(), "Login Sucess", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                }else{
                                    if (isAdded()) {
                                        dialog.dismiss();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<LoginResponce> call, Throwable t) {
                                if (isAdded()) {
                                 dialog.dismiss();
                                }
                                Toast.makeText(getContext(), t.getMessage()+"...", Toast.LENGTH_LONG).show();
                                Log.d("response", t.getMessage());

                            }

                        });
                    } else {
                        et_password.setError("Please enter password");
                    }
                } else {
                    et_phone.setError("Please enter phone number");
                }


            }
        });

    }

    private void dialogdismis() {
        if (dialog.isShowing()) {

            dialog.hide();
            dialog.cancel();
            dialog.dismiss();
        }
    }

}
