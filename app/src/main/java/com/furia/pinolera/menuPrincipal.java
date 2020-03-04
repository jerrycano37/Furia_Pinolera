package com.furia.pinolera;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.furia.pinolera.adapter.TabPageAdaptador;

public class menuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        //getSupportActionBar().setElevation(0);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.pager);

        viewPager.setAdapter(new TabPageAdaptador(getSupportFragmentManager(), this));
        tabLayout.setupWithViewPager(viewPager);
    }
}
