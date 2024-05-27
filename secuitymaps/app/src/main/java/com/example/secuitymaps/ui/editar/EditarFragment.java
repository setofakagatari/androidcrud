package com.example.secuitymaps.ui.editar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.secuitymaps.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EditarFragment extends Fragment {

    private EditText userIdEditText, usernameEditText, fullnameEditText, emailEditText, passwordEditText;
    private Button editarButton;

    public EditarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editar, container, false);

        // Initialize UI components
        userIdEditText = view.findViewById(R.id.user_id);
        usernameEditText = view.findViewById(R.id.username);
        fullnameEditText = view.findViewById(R.id.fullname);
        emailEditText = view.findViewById(R.id.email);
        passwordEditText = view.findViewById(R.id.password);
        editarButton = view.findViewById(R.id.editar);

        // Set click listener for the editarButton
        editarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarUsuario();
            }
        });

        return view;
    }

    private void actualizarUsuario() {
        // Get input values from EditText fields
        String userId = userIdEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String fullname = fullnameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Construct the URL for the API endpoint
        String url = "http://10.0.2.2:5000/api/ActualizarUsuario/" + userId + "/" + username + "/" + fullname + "/" + email + "/" + password;

        // Create a StringRequest to make a GET request to the URL
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parse the response JSON object
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            // Display success message
                            Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                            // Clear EditText fields
                            userIdEditText.setText("");
                            usernameEditText.setText("");
                            fullnameEditText.setText("");
                            emailEditText.setText("");
                            passwordEditText.setText("");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Display error message
                        Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(stringRequest);
    }
}
