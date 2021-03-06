package com.example.lettergen;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class FragmentClassic extends Fragment implements View.OnClickListener {

    // Declaracion de variables
    private Button b_q, b_w, b_e, b_r, b_t, b_y, b_u, b_i, b_o, b_p, b_a, b_s, b_d, b_f, b_g, b_h, b_j, b_k, b_l, b_z, b_x, b_c, b_v, b_b, b_n, b_m, b_res;
    private ImageButton ib_startstop;
    private TextView tv_letter, tv_gbt;

    private ArrayList<String> letters_arrayList = new ArrayList<>(); // ArrayList donde permaneceran las letras que prefiera usar el usuario
    private int letter_number=0; // Entero para ir recorriendo el ArryaList cuando el usuario haga click en el boton Start
    private int playing=0; // Entero para saber el estado de la genarcion de la letra (playing=0; default, playing=1; detenido, playing=2; corriendo)
    private Thread thread; // Hilo para cambiar la letra del TextView a medida que avanza el tiempo
    private Handler h = new Handler(); // Variable es ncesaria para poder cambiar el texto del TextView dentro de la funcion del hilo
    private boolean letter_generated=false; // Boolean para determinar si la letra ha sido generada
    private int miliseconds; // Entero para asignar el tiempo que se va a pausar el hilo

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_fragment_classic, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //Inicializacion de views
        tv_letter=view.findViewById(R.id.textView_letter);
        tv_gbt=view.findViewById(R.id.textView_GenerateButtonText);

        b_q=view.findViewById(R.id.button_q);
        b_w=view.findViewById(R.id.button_w);
        b_e=view.findViewById(R.id.button_e);
        b_r=view.findViewById(R.id.button_r);
        b_t=view.findViewById(R.id.button_t);
        b_y=view.findViewById(R.id.button_y);
        b_u=view.findViewById(R.id.button_u);
        b_i=view.findViewById(R.id.button_i);
        b_o=view.findViewById(R.id.button_o);
        b_p=view.findViewById(R.id.button_p);
        b_a=view.findViewById(R.id.button_a);
        b_s=view.findViewById(R.id.button_s);
        b_d=view.findViewById(R.id.button_d);
        b_f=view.findViewById(R.id.button_f);
        b_g=view.findViewById(R.id.button_g);
        b_h=view.findViewById(R.id.button_h);
        b_j=view.findViewById(R.id.button_j);
        b_k=view.findViewById(R.id.button_k);
        b_l=view.findViewById(R.id.button_l);
        b_z=view.findViewById(R.id.button_z);
        b_x=view.findViewById(R.id.button_x);
        b_c=view.findViewById(R.id.button_c);
        b_v=view.findViewById(R.id.button_v);
        b_b=view.findViewById(R.id.button_b);
        b_n=view.findViewById(R.id.button_n);
        b_m=view.findViewById(R.id.button_m);

        ib_startstop =view.findViewById(R.id.imageButton_startstop);
        b_res=view.findViewById(R.id.button_reset);

        // Asignacion del onClickListener a los botones
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

        ib_startstop.setOnClickListener(this);
        b_res.setOnClickListener(this);

        initButtons(); // Se llama a esta funcion para asignar al teclado la configuracion de letras del usuario

        // Instanciacion del hilo
        thread =new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){ // Lo siguiente se estara ejecutando siempre
                    if(playing==2){ // Si la generacion de la letra esta corriendo...

                        // En cada iteracion del ciclo

                        // se pausa el hilo un determinado lapso de tiempo,
                        try {
                            Thread.sleep(miliseconds);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                tv_letter.setText(letters_arrayList.get(letter_number)); // se asigna una letra del ArrayList al TextView,
                            }
                        });

                        // y se avanza a la siguiente letra del ArrayList o se vuelve a la primera
                        if(letter_number<letters_arrayList.size()-1)
                            letter_number++;
                        else
                            letter_number=0;

                        letter_generated=false; // La letra no ha sido generada
                    } else if(playing==1&&!letter_generated) { // Si la generacion de la letra esta detenida y la letra no ha sido generada (Se entra en este if cuando el usuario hace click en el boton Start/Stop)...
                        h.post( new Runnable() {
                            @Override
                            public void run() {
                                String letter=tv_letter.getText().toString(); // Se obtiene la letra del TextView
                                includeExcludeLetter(getButtonByLetter(letter), letter); // Se "desactiva" la letra
                            }
                        });

                        letter_number=0; // Se vuelve a la primera letra del ArrayList
                        letter_generated=true; // La letra ya fue generada
                    }
                }
            }
        });
        thread.start(); // Empieza a correr el hilo
    }

    // Funcion para cuando el usuario hace click en el boton Start/Stop
    private void imageButton_startstop_onClick(){
        if(!letters_arrayList.isEmpty()){ // Si hay letras disponibles en el ArrayList...
            if(playing!=2){ // Si la generacion de la letra esta detenida o esta en default...
                miliseconds=generateRandomIntIntRange(1,5)*100; // Se llama a esta funcion para determinar cada cuanto tiempo cambiara el TextView de letra
                playing=2; // Se cambia el estado de la generacion a corriendo
                tv_gbt.setText(R.string.string_stop); // Se cambia el texto del boton startstop
                // Se le avisa al usuario que la generacion de la letra esta corriendo
                Toast t=Toast.makeText(getContext(), "Generating...",Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP,0,220);
                t.show();
            }else if(playing!=1){ // Si la genreacion de la letra esta corriendo...
                playing=1; // Se cambia el estado de la generacion a detenido
                tv_gbt.setText(R.string.string_start); // Se cambia el texto del boton startstop
            }
        } else { // Si no ha letras diponibles...

            // Se le avisa al usuario que elija lagunas letras a traves de un Toast
            Toast t=Toast.makeText(getContext(),"Select some letters",Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP,0,220);
            t.show();
        }

    }

    // Funcion para el boton que reinicia el teclado con la configuracion de letras del usuarion
    private void button_reset_onClick() {
        ArrayList<String> letterList=(new DBTransaction()).getNonDefaultLetters(getContext()); // Se crea un ArrayList auxiliar con las lettras obtenidas de la funcion que consulta las letras de preferencia de usuario en la BD
        letters_arrayList.clear(); // Se retiran todas las letras del ArrayList
        for(int i=0;i<letterList.size();i++){
            getButtonByLetter(letterList.get(i)).setBackgroundTintList(ContextCompat.getColorStateList(Objects.requireNonNull(getContext()), R.color.colorTransparent)); // Se le da color al boton correspondiente
            letters_arrayList.add(letterList.get(i)); // Se agrega al ArrayList cada letra del ArrayList auxiliar
        }

        initButtons();
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

    // Funcion para obtener un entero aleatorio
    public static int generateRandomIntIntRange(int min, int max) { // Parametros: un entero minimo y un maximo
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
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
            case R.id.imageButton_startstop: imageButton_startstop_onClick(); break;
            case R.id.button_reset: button_reset_onClick(); break;
        }
    }
}
