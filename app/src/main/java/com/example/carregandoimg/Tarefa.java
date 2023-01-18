package com.example.carregandoimg;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Tarefa extends AsyncTask<String, String, Bitmap> {

    private ProgressDialog dialog;
    private Context context;
    private TarefaInterface ti;

    public Tarefa(Context context, TarefaInterface ti) {
        this.context = context;
        this.ti = ti;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = new ProgressDialog(context);
        dialog.setMessage("Baixando imagem");
        dialog.show();

    }


    @Override
    protected Bitmap doInBackground(String... params) {
        //todo testar se colocar isso dentro do for, cada execucao de atalizacao da of chama o doinbackground

        Bitmap img = null;

        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream input = connection.getInputStream();
            img = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return(img);
    }

    @Override
    protected void onProgressUpdate(String... params) {
        super.onProgressUpdate(params);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        dialog.setMessage("Imagem carregada!");
        ti.depoisDownload(bitmap);
        dialog.dismiss();
    }
}
