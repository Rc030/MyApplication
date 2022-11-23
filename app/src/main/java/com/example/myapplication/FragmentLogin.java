package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentLogin extends Fragment {
    final static String loginUrl = "http://34.175.83.209:8080/Connect/login";
    Button btnLogin, btnRegister;
    EditText etUserName, etPassword;
    CallbackFragment callbackFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        etUserName = view.findViewById(R.id.etUserName);
        etPassword = view.findViewById(R.id.etPassword);

        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), VideosActivity.class);
            startActivity(intent);
            loginRequest(etUserName.getText().toString(), etPassword.getText().toString());
        });

        btnRegister.setOnClickListener(v -> {
            if(callbackFragment != null){
                callbackFragment.changefragmentRegister();
            }
            System.out.println(callbackFragment);
        });

        return view;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment){
        this.callbackFragment = callbackFragment;
    }

    protected void loginRequest(String user, String password){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        JSONObject postData = new JSONObject();
        try {
            postData.put("user", user);
            postData.put("pass", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, loginUrl, postData, response -> {
            try {
                if(response.getString("status").equals("Sucesso")){
                    Intent intent = new Intent(getActivity(), VideosActivity.class);
                    startActivity(intent);
                    System.out.println("granted");
                }else{
                    System.out.println("invalid login");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> System.out.println(error.toString()));
        requestQueue.add(jsonObjectRequest);
    }
}


