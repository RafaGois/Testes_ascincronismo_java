package com.example.carregandoimg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void baixarImagem(View view) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Baixando imagem");
        dialog.show();

        new Thread(){
            public void run() {
                Bitmap img = null;

                try {
                    URL url = new URL("https://media.racingonline.com.br/uploads/2021/06/Mitsubishi-Evo-VI-Tommi-M%C3%A4kinen-Foto-Mitsubishi-divulga%C3%A7%C3%A3o.png");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream input = connection.getInputStream();
                    img = BitmapFactory.decodeStream(input);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                final Bitmap imgAux = img;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.setMessage("Imagem Carregada");
                        ImageView iv = findViewById(R.id.imageView);
                        iv.setImageBitmap(imgAux);
                        dialog.dismiss();
                    }
                });
            }
        }.start();
    }
}