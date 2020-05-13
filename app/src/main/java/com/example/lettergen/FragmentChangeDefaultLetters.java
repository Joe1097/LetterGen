package com.example.lettergen;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Objects;

public class FragmentChangeDefaultLetters extends Fragment implements View.OnClickListener {

    // Declaracion de variables
    private Button b_q, b_w, b_e, b_r, b_t, b_y, b_u, b_i, b_o, b_p, b_a, b_s, b_d, b_f, b_g, b_h, b_j, b_k, b_l, b_z, b_x, b_c, b_v, b_b, b_n, b_m, b_save;

    private ArrayList<String> letters_arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_fragment_change_default_letters, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //Inicializacion de views
        b_q = (Button)view.findViewById(R.id.button_q);
        b_w = (Button)view.findViewById(R.id.button_w);
        b_e = (Button)view.findViewById(R.id.button_e);
        b_r = (Button)view.findViewById(R.id.button_r);
        b_t = (Button)view.findViewById(R.id.button_t);
        b_y = (Button)view.findViewById(R.id.button_y);
        b_u = (Button)view.findViewById(R.id.button_u);
        b_i = (Button)view.findViewById(R.id.button_i);
        b_o = (Button)view.findViewById(R.id.button_o);
        b_p = (Button)view.findViewById(R.id.button_p);
        b_a = (Button)view.findViewById(R.id.button_a);
        b_s = (Button)view.findViewById(R.id.button_s);
        b_d = (Button)view.findViewById(R.id.button_d);
        b_f = (Button)view.findViewById(R.id.button_f);
        b_g = (Button)view.findViewById(R.id.button_g);
        b_h = (Button)view.findViewById(R.id.button_h);
        b_j = (Button)view.findViewById(R.id.button_j);
        b_k = (Button)view.findViewById(R.id.button_k);
        b_l = (Button)view.findViewById(R.id.button_l);
        b_z = (Button)view.findViewById(R.id.button_z);
        b_x = (Button)view.findViewById(R.id.button_x);
        b_c = (Button)view.findViewById(R.id.button_c);
        b_v = (Button)view.findViewById(R.id.button_v);
        b_b = (Button)view.findViewById(R.id.button_b);
        b_n = (Button)view.findViewById(R.id.button_n);
        b_m = (Button)view.findViewById(R.id.button_m);

        b_save=view.findViewById(R.id.button_save);

        b_q.setOnClickListener(this);
        b_w.setOnClickListener(this);
        b_e.setOnClickListener(this);
        b_r.setOnClickListener(this);
        b_t.setOnClickListener(this);
        b_y.setOnClickListener(this);
        b_u.setOnClickListener(this);
        b_i.setOnClickListener(this);
        b_o.setOnClickListener(this);
        b_p.setOnClickListener(this);
        b_a.setOnClickListener(this);
        b_s.setOnClickListener(this);
        b_d.setOnClickListener(this);
        b_f.setOnClickListener(this);
        b_g.setOnClickListener(this);
        b_h.setOnClickListener(this);
        b_j.setOnClickListener(this);
        b_k.setOnClickListener(this);
        b_l.setOnClickListener(this);
        b_z.setOnClickListener(this);
        b_x.setOnClickListener(this);
        b_c.setOnClickListener(this);
        b_v.setOnClickListener(this);
        b_b.setOnClickListener(this);
        b_n.setOnClickListener(this);
        b_m.setOnClickListener(this);

        b_save.setOnClickListener(this);

        initButtons(); // Se llama a esta funcion para asignar al teclado la configuracion de letras del usuario
    }

    // Funcion para asignar al teclado la configuracion de letras del usuario
    private void initButtons(){
        ArrayList<String> letterList=(new DBTransaction()).getDefaultLetters(getContext()); // Se crea un ArrayList auxiliar con las lettras obtenidas de la funcion que consulta las letras de preferencia de usuario en la BD
        letters_arrayList.clear(); // Se retiran todas las letras del ArrayList
        for(int i=0;i<letterList.size();i++){
            getButtonByLetter(letterList.get(i)).setBackgroundTintList(ContextCompat.getColorStateList(Objects.requireNonNull(getContext()), R.color.colorButtons)); // Se le da color al boton correspondiente
            letters_arrayList.add(letterList.get(i)); // Se agrega al ArrayList cada letra del ArrayList auxiliar
        }
    }

    // Funcion para obtener el boton que le corresponde a cada letra
    private Button getButtonByLetter(String letter){
        Button button=null;
        switch (letter){
            case "a": button=b_a; break;
            case "b": button=b_b; break;
            case "c": button=b_c; break;
            case "d": button=b_d; break;
            case "e": button=b_e; break;
            case "f": button=b_f; break;
            case "g": button=b_g; break;
            case "h": button=b_h; break;
            case "i": button=b_i; break;
            case "j": button=b_j; break;
            case "k": button=b_k; break;
            case "l": button=b_l; break;
            case "m": button=b_m; break;
            case "n": button=b_n; break;
            case "o": button=b_o; break;
            case "p": button=b_p; break;
            case "q": button=b_q; break;
            case "r": button=b_r; break;
            case "s": button=b_s; break;
            case "t": button=b_t; break;
            case "u": button=b_u; break;
            case "v": button=b_v; break;
            case "w": button=b_w; break;
            case "x": button=b_x; break;
            case "y": button=b_y; break;
            case "z": button=b_z; break;
        }
        return button;
    }

    // Funcion onClick para los botones de la app
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_q: includeExcludeLetter(b_q, "q"); break;
            case R.id.button_w: includeExcludeLetter(b_w, "w"); break;
            case R.id.button_e: includeExcludeLetter(b_e, "e"); break;
            case R.id.button_r: includeExcludeLetter(b_r, "r"); break;
            case R.id.button_t: includeExcludeLetter(b_t, "t"); break;
            case R.id.button_y: includeExcludeLetter(b_y, "y"); break;
            case R.id.button_u: includeExcludeLetter(b_u, "u"); break;
            case R.id.button_i: includeExcludeLetter(b_i, "i"); break;
            case R.id.button_o: includeExcludeLetter(b_o, "o"); break;
            case R.id.button_p: includeExcludeLetter(b_p, "p"); break;
            case R.id.button_a: includeExcludeLetter(b_a, "a"); break;
            case R.id.button_s: includeExcludeLetter(b_s, "s"); break;
            case R.id.button_d: includeExcludeLetter(b_d, "d"); break;
            case R.id.button_f: includeExcludeLetter(b_f, "f"); break;
            case R.id.button_g: includeExcludeLetter(b_g, "g"); break;
            case R.id.button_h: includeExcludeLetter(b_h, "h"); break;
            case R.id.button_j: includeExcludeLetter(b_j, "j"); break;
            case R.id.button_k: includeExcludeLetter(b_k, "k"); break;
            case R.id.button_l: includeExcludeLetter(b_l, "l"); break;
            case R.id.button_z: includeExcludeLetter(b_z, "z"); break;
            case R.id.button_x: includeExcludeLetter(b_x, "x"); break;
            case R.id.button_c: includeExcludeLetter(b_c, "c"); break;
            case R.id.button_v: includeExcludeLetter(b_v, "v"); break;
            case R.id.button_b: includeExcludeLetter(b_b, "b"); break;
            case R.id.button_n: includeExcludeLetter(b_n, "n"); break;
            case R.id.button_m: includeExcludeLetter(b_m, "m"); break;
            case R.id.button_save: button_save_onClick(); break;
        }
    }

    // Funcion para "activar" o "descativar" una letra
    private void includeExcludeLetter(View view, String letter){ // Parametros: El respectivo boton de la letra y la letra
        if(letters_arrayList.contains(letter)){ // Si el ArrayList contiene la letra...
            view.setBackgroundTintList(ContextCompat.getColorStateList(Objects.requireNonNull(getContext()), R.color.colorTransparent)); // Se cambia el color del boton a transparente
            letters_arrayList.remove(letter); // Se retira la letra del ArrayList
        } else { // Si no la contiene...
            view.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.colorButtons)); // Se cambia el color del boton a un rojo
            letters_arrayList.add(letter); // Se agrega la letra al ArrayList
        }
    }

    // Funcion para guardar la nueva configuracion de teclado del usuarion
    private void button_save_onClick(){
        (new DBTransaction()).changeDefaultLetters(getContext(),letters_arrayList); // Se crea este objeto para utilizar la funcion que guardara la nueva configuracion personalizada del teclado
    }
}
