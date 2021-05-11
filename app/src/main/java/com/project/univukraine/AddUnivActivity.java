package com.project.univukraine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.project.univukraine.model.University;
import com.project.univukraine.model.UniversityRepository;

public class AddUnivActivity extends AppCompatActivity {
    EditText univName, univCityName, univNSt, univWR, univIR, univOR, univER, univLat, univLng;
    SQLiteDatabase db;
    UniversityRepository universityRepository;
    University university;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_univ);

        univName = findViewById(R.id.univName);
        univCityName = findViewById(R.id.univCityName);
        univNSt = findViewById(R.id.univNumberName);
        univWR = findViewById(R.id.univWRName);
        univER = findViewById(R.id.univERName);
    }
    public void add (View view){
        university = new University();
        university.setName(univName.getText().toString());
        university.setAddress(univCityName.getText().toString());
        university.setStudentAmount(Integer.valueOf(univNSt.getText().toString()));
        university.setWebometrix(Integer.valueOf(univWR.getText().toString()));
        university.setExcellence(Integer.valueOf(univER.getText().toString()));

        universityRepository = new UniversityRepository(getBaseContext(),
                getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null));
        universityRepository.insertUniversity(university);
        Intent intent = new Intent(AddUnivActivity.this, UnivTableActivity.class);
        startActivity(intent);
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
                intent = new Intent(AddUnivActivity.this, UnivTableActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_main:
                intent = new Intent(AddUnivActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_contact:
                intent = new Intent(AddUnivActivity.this, ContactActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_about:
                intent = new Intent(AddUnivActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}