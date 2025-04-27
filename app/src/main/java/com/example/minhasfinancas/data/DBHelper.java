package com.example.minhasfinancas.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.minhasfinancas.model.Gasto;

import java.util.ArrayList;
import java.util.List;
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MinhasFinancas.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE gastos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "descricao TEXT NOT NULL, " +
                "valor REAL NOT NULL, " +
                "categoria TEXT NOT NULL, " +
                "data TEXT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS gastos");
        onCreate(db);
    }

    public void inserirGasto(Gasto gasto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("descricao", gasto.getDescricao());
        cv.put("valor", gasto.getValor());
        cv.put("categoria", gasto.getCategoria());
        cv.put("data", gasto.getData());
        db.insert("gastos", null, cv);
    }

    public List<Gasto> listarGastos() {
        List<Gasto> lista = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM gastos", null);

        if (cursor.moveToFirst()) {
            do {
                Gasto gasto = new Gasto();
                gasto.setId(cursor.getInt(0));
                gasto.setDescricao(cursor.getString(1));
                gasto.setValor(cursor.getDouble(2));
                gasto.setCategoria(cursor.getString(3));
                gasto.setData(cursor.getString(4));
                lista.add(gasto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}
