package com.example.android.mistareas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.android.mistareas.Constantes.BASEDATOS;
import static com.example.android.mistareas.Constantes.HECHA;
import static com.example.android.mistareas.Constantes.IMAGEN;
import static com.example.android.mistareas.Constantes.NOMBRE;
import static com.example.android.mistareas.Constantes.TABLA_TAREAS;

public class Database extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private final String[] SELECT = new String[]{_ID, NOMBRE, HECHA, IMAGEN};


    public Database(Context contexto) {
        super(contexto, BASEDATOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_TAREAS +
                " (" +_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOMBRE + " TEXT, " + HECHA + " INTEGER," +
                IMAGEN + " BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLA_TAREAS);
        onCreate(db);
    }

    public void nuevaTarea(Tarea tareaNueva) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(NOMBRE, tareaNueva.getNombre());
        valores.put(HECHA, tareaNueva.estaHecha());
        valores.put(IMAGEN, Util.getBytes(tareaNueva.getImagen()));
        db.insertOrThrow(TABLA_TAREAS, null, valores);
    }

    public ArrayList<Tarea> getLista(Cursor cursor) {
        ArrayList<Tarea> tareas = new ArrayList<>();
        while (cursor.moveToNext()) {
            Tarea tarea = new Tarea(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getInt(2) >= 1);
            tarea.setImagen(Util.getBitmap(cursor.getBlob(3)));
            tareas.add(tarea);
        }

        return tareas;
    }

    public ArrayList<Tarea> getTareas() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLA_TAREAS, SELECT, null, null, null, null, NOMBRE);
        return getLista(cursor);
    }

    public ArrayList<Tarea> getTareasHechas() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLA_TAREAS, SELECT, null, null, null, null, NOMBRE);
        return getLista(cursor);
    }

    public ArrayList<Tarea> getTareasPendientes() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLA_TAREAS, SELECT, null, null, null, null, NOMBRE);
        return getLista(cursor);
    }

    public ArrayList<Tarea> getTareas(String busqueda) {
        return null;
    }
}

