package com.example.minhasfinancas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.minhasfinancas.R;
import com.example.minhasfinancas.data.DBHelper;
import com.example.minhasfinancas.model.Gasto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumoActivity extends AppCompatActivity {

    private TextView txtResumo;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        db = new DBHelper(this);
        txtResumo = findViewById(R.id.txtResumo);

        new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulando carregamento pesado
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<Gasto> gastos = db.listarGastos();
            Map<String, Double> totais = new HashMap<>();
            double totalMes = 0;
            String categoriaMaior = "";
            double maiorValor = 0;

            for (Gasto g : gastos) {
                double soma = totais.getOrDefault(g.getCategoria(), 0.0) + g.getValor();
                totais.put(g.getCategoria(), soma);
                totalMes += g.getValor();
            }

            for (Map.Entry<String, Double> entry : totais.entrySet()) {
                if (entry.getValue() > maiorValor) {
                    maiorValor = entry.getValue();
                    categoriaMaior = entry.getKey();
                }
            }

            final StringBuilder resumo = new StringBuilder();
            resumo.append("Gasto Total: R$ ").append(String.format("%.2f", totalMes)).append("\n\n")
                    .append("Categoria com maior gasto: ").append(categoriaMaior).append("\n\n")
                    .append("Gastos por Categoria:\n");

            for (Map.Entry<String, Double> entry : totais.entrySet()) {
                resumo.append(entry.getKey()).append(": R$ ")
                        .append(String.format("%.2f", entry.getValue())).append("\n");
            }

            runOnUiThread(() -> txtResumo.setText(resumo.toString()));
        }).start();
    }
}
