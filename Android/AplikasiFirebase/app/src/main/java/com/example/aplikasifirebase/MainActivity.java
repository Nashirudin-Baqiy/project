package com.example.aplikasifirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pindah(View view) {
        switch (view.getId()){
            case R.id.add_mhs :
                startActivity(new Intent(MainActivity.this,TambahMhsActivity.class));
                break;
            case R.id.list_mhs :
                startActivity(new Intent(MainActivity.this,ListMhsActivity.class));
                break;
        }
    }

}