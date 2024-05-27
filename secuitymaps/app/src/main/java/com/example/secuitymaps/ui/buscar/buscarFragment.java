package com.example.secuitymaps.ui.buscar;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.secuitymaps.R;
import org.json.JSONException;
import org.json.JSONObject;

public class buscarFragment extends Fragment {

    private EditText idUserEditText, usernameEditText, fullnameEditText, emailEditText, passwordEditText;

    public buscarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_buscar, container, false);

        idUserEditText = root.findViewById(R.id.id_user);
        usernameEditText = root.findViewById(R.id.username);
        fullnameEditText = root.findViewById(R.id.fullname);
        emailEditText = root.findViewById(R.id.email);
        passwordEditText = root.findViewById(R.id.password);

        Button buscarButton = root.findViewById(R.id.buscar);

        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarUsuario();
            }
        });

        return root;
    }

    private void buscarUsuario() {
        String idUser = idUserEditText.getText().toString();
        String url = "http://10.0.2.2:5000/api/ConsultarUsuario/" + idUser;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            // Actualiza los campos con los datos del usuario encontrado
                            usernameEditText.setText(jsonObject.getString("username"));
                            fullnameEditText.setText(jsonObject.getString("fullname"));
                            emailEditText.setText(jsonObject.getString("email"));
                            passwordEditText.setText(jsonObject.getString("password"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Error al procesar la respuesta del servidor", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getContext(), "Error de red, por favor revisa tu conexión", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(requireContext()).add(stringRequest);
    }
}
