package com.example.carregandoimg;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Bitmap;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements TarefaInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void baixarImagem(View view) {

        Tarefa tarefa = new Tarefa(this,this);
        tarefa.execute("https://media.racingonline.com.br/uploads/2021/06/Mitsubishi-Evo-VI-Tommi-M%C3%A4kinen-Foto-Mitsubishi-divulga%C3%A7%C3%A3o.png");
    }

    @Override
    public void depoisDownload(Bitmap bitmap) {
        ImageView iv = findViewById(R.id.imgfoto);
        iv.setImageBitmap(bitmap);
    }
}