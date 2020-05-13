package com.example.lettergen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Fucion que se ejecuta la primera vez que se instala la aplicacion
    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("CREATE TABLE letters(letter TEXT, included INTEGER)"); // Se crea la tabla con un campo que contendra todas las letras del abecedario y otro que determinara si la letra pertenece a la configuraion del usuario
        // Se insertan todas las letras y se determina que todas perteneceran a la configuracion
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('a',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('b',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('c',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('d',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('e',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('f',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('g',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('h',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('i',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('j',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('k',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('l',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('m',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('n',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('o',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('p',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('q',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('r',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('s',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('t',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('u',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('v',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('w',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('x',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('y',1)");
        BaseDeDatos.execSQL("INSERT INTO letters(letter, included) VALUES ('z',1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
