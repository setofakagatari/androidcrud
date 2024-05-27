package com.example.secuitymaps.ui.eliminar;

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

public class eliminarFragment extends Fragment {

    private EditText userIdEditText;

    public eliminarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_eliminar, container, false);

        userIdEditText = root.findViewById(R.id.user_id);

        Button eliminarButton = root.findViewById(R.id.eliminar);

        eliminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarUsuario();
            }
        });

        return root;
    }

    private void eliminarUsuario() {
        String userId = userIdEditText.getText().toString();
        String url = "http://10.0.2.2:5000/api/EliminarUsuario/" + userId;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Mostrar mensaje de éxito
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        // Mostrar mensaje de error
                        Toast.makeText(getContext(), "Error de red, por favor revisa tu conexión", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(requireContext()).add(stringRequest);
    }
}
