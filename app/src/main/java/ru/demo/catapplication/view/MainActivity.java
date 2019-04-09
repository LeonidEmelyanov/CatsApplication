package ru.demo.catapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.demo.catapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(R.string.app_name);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MvvmFragment())
                .commit();
    }
}
