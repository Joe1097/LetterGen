package com.example.lettergen;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class DBTransaction { // Clase para las hacer las transacciones con la BD

    // Funcion para obtener las letras configuracion del ususario
    public ArrayList<String> getDefaultLetters(Context context){ // Parametros: el contexto de la Activity
        ArrayList<String> letterList=new ArrayList<>(); // Se crea un ArrayList que contendra las letras de la configuracion

        try {
            AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(context,"lettergen",null,1); // Se crean estos objetos para poder realizar las transacciones
            SQLiteDatabase sqLiteDatabase = adminSQLiteOpenHelper.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT letter FROM letters WHERE included=1",null); // Se crea un cursor que contendra los datos de la consulta especificada
            while (cursor.moveToNext())
                letterList.add(cursor.getString(0)); // Se llenan el ArrayList con los datos del cursor
            sqLiteDatabase.close(); // Se cierra la BD
        } catch (Exception e){
            Toast.makeText(context, e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        return letterList; // Se retorna el ArrayList
    }

    // Funcion para obtener las letras que no son las de la configuracion del usuario
    public ArrayList<String> getNonDefaultLetters(Context context){ // Parametros: el contexto de la Activity
        ArrayList<String> letterList=new ArrayList<>();

        try {
            AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(context,"lettergen",null,1); // Se crean estos objetos para poder realizar las transacciones
            SQLiteDatabase sqLiteDatabase = adminSQLiteOpenHelper.getWritableDatabase();

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT letter FROM letters WHERE included=0",null); // Se crea un cursor que contendra los datos de la consulta especificada
            while (cursor.moveToNext())
                letterList.add(cursor.getString(0)); // Se llenan el ArrayList con los datos del cursor
            sqLiteDatabase.close(); // Se cierra la BD
        } catch (Exception e){
            Toast.makeText(context, e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        return letterList; // Se retorna el ArrayList
    }

    // Funcion para asignar la nueva configuracion personalizada de letras del usuario
    public void changeDefaultLetters(Context context, ArrayList<String> letter_arrayList){ // Parametros: el contexto de la Activity y un ArrayList con las letras de la nueva configuracion
        try {
            AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(context,"lettergen",null,1); // Se crean estos objetos para poder realizar las transacciones
            SQLiteDatabase sqLiteDatabase = adminSQLiteOpenHelper.getWritableDatabase();

            sqLiteDatabase.execSQL("UPDATE letters SET included=0"); // Se ejecuta un actualizacion para que ninguna letra sea de la configuracion

            for(int i=0;i<letter_arrayList.size();i++){ // Luego se actualizan las letras que si seran de la actualizacion
                sqLiteDatabase.execSQL("UPDATE letters SET included=1 WHERE letter='"+letter_arrayList.get(i)+"'");
            }

            sqLiteDatabase.close(); // Se cierra la BD

            Toast.makeText(context,"Changes saved",Toast.LENGTH_LONG).show(); // Se notifica mediante un Toast el exito de la actualizacion
        } catch (Exception e){
            Toast.makeText(context, e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
