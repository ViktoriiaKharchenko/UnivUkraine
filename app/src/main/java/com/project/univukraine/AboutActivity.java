package com.project.univukraine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        int id = item.getItemId();
        Intent intent ;
        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.action_univ:
                intent = new Intent(AboutActivity.this, UnivTableActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_main:
                intent = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_contact:
                intent = new Intent(AboutActivity.this, ContactActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_about:
                intent = new Intent(AboutActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}