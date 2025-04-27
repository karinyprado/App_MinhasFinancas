package com.example.minhasfinancas.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.minhasfinancas.R;
import com.example.minhasfinancas.data.DBHelper;
import com.example.minhasfinancas.model.Gasto;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GastoAdapter adapter;
    private Button btnAdicionar, btnResumo;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        recyclerView = findViewById(R.id.recyclerViewGastos);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnResumo = findViewById(R.id.btnResumo);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAdicionar.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, CadastroActivity.class));
        });

        btnResumo.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ResumoActivity.class));
        });
    }

    private void carregarGastos() {
        List<Gasto> gastos = db.listarGastos();
        adapter = new GastoAdapter(gastos);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarGastos();
    }
}
