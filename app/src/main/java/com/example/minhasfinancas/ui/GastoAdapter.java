//Adapter para exibir os gastos num RecyclerView

package com.example.minhasfinancas.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minhasfinancas.R;
import com.example.minhasfinancas.model.Gasto;

import java.util.List;

public class GastoAdapter extends RecyclerView.Adapter<GastoAdapter.GastoViewHolder> {

    private List<Gasto> gastos;

    public GastoAdapter(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    @NonNull
    @Override
    public GastoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gasto, parent, false);
        return new GastoViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull GastoViewHolder holder, int position) {
        Gasto gasto = gastos.get(position);
        holder.txtDescricao.setText(gasto.getDescricao());
        holder.txtValor.setText(String.format("R$ %.2f", gasto.getValor()));
        holder.txtCategoria.setText(gasto.getCategoria());
        holder.txtData.setText(gasto.getData());
    }

    @Override
    public int getItemCount() {
        return gastos.size();
    }

    public static class GastoViewHolder extends RecyclerView.ViewHolder {
        TextView txtDescricao, txtValor, txtCategoria, txtData;

        public GastoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
            txtValor = itemView.findViewById(R.id.txtValor);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
            txtData = itemView.findViewById(R.id.txtData);
        }
    }
}
