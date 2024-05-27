package com.example.secuitymaps.ui.insertar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.secuitymaps.R;
import org.json.JSONException;
import org.json.JSONObject;

public class InsertarFragment extends Fragment {

    private EditText usernameEditText, fullnameEditText, emailEditText, passwordEditText;
    private Button insertButton;

    public InsertarFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insertar, container, false);

        usernameEditText = view.findViewById(R.id.usernameEditText);
        fullnameEditText = view.findViewById(R.id.fullnameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        insertButton = view.findViewById(R.id.insertButton);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarUsuario();
            }
        });

        return view;
    }

    private void insertarUsuario() {
        String username = usernameEditText.getText().toString();
        String fullname = fullnameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        String url = "http://10.0.2.2:5000/api/InsertarUsuario/" + username + "/" + fullname + "/" + email + "/" + password;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    usernameEditText.setText("");
                    fullnameEditText.setText("");
                    emailEditText.setText("");
                    passwordEditText.setText("");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(getActivity()).add(postRequest);
    }
}