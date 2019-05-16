package com.furia.pinolera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.furia.utils.Utils;

public class menuPrincipal extends AppCompatActivity {
    ImageView bgImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        inicializarComponentes();
        iniciarBackground();
    }

    private void inicializarComponentes() {
        bgImageView = findViewById(R.id.bgImageView);
    }

    private void iniciarBackground() {
        int id = R.drawable.login_background;
        Utils.setImageToImageView(getApplicationContext(), bgImageView, id);
    }




}
