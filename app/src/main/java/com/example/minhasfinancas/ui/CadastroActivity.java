package com.example.minhasfinancas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.minhasfinancas.R;
import com.example.minhasfinancas.data.DBHelper;
import com.example.minhasfinancas.model.Gasto;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtDescricao, edtValor, edtData;
    private Spinner spinnerCategoria;
    private Button btnSalvar;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        db = new DBHelper(this);

        edtDescricao = findViewById(R.id.edtDescricao);
        edtValor = findViewById(R.id.edtValor);
        edtData = findViewById(R.id.edtData);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        btnSalvar = findViewById(R.id.btnSalvar);

        String[] categorias = {"Alimentação", "Transporte", "Lazer", "Saúde", "Outros"};
        spinnerCategoria.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categorias));

        btnSalvar.setOnClickListener(view -> {
            Gasto gasto = new Gasto(
                    edtDescricao.getText().toString(),
                    Double.parseDouble(edtValor.getText().toString()),
                    spinnerCategoria.getSelectedItem().toString(),
                    edtData.getText().toString()
            );
            db.inserirGasto(gasto);
            finish();
        });
    }
}
