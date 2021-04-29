package com.project.univukraine;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.project.univukraine.model.ContactRepository;
import com.project.univukraine.model.UniversityRepository;


public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    UniversityRepository universityRepository;
    ContactRepository contactRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        onStartPref();
        //onStartPref2();
        universityRepository =  new UniversityRepository(getBaseContext(),
                getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null));
        contactRepository = new ContactRepository(getBaseContext(),
                getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null));
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
                intent = new Intent(MainActivity.this, UnivTableActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_main:
                intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_contact:
                intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void onStartPref (){
        db.execSQL("DROP TABLE IF EXISTS university");
        db.execSQL("DROP TABLE IF EXISTS univercity");

        db.execSQL("CREATE TABLE IF NOT EXISTS university (id INTEGER, name TEXT, address TEXT, studentAmount INTEGER, webometrix INTEGER, excellence INTEGER, latitude TEXT, longitude TEXT)");
        db.execSQL("INSERT INTO university VALUES (0, 'Київський національний університет імені Тараса Шевченка', 'Київ', 28000, 1164, 1408, '50.441963810463136', '30.510273553963103');");
        db.execSQL("INSERT INTO university VALUES (1, 'Київський політехнічний інститут імені Ігоря Сікорського', 'Київ', 21500, 1601, 2865, '50.449310022691996', '30.456202151468034');");
        db.execSQL("INSERT INTO university VALUES (2, 'Сумський державний університет', 'Суми', 12000, 1806, 2471, '50.89200907066976', '34.843152159397285');");
        db.execSQL("INSERT INTO university VALUES (3, 'Національний авіаційний університет', 'Київ', 25000, 2026, 3996, '50.440153638541375', '30.430087507651358');");
        db.execSQL("INSERT INTO university VALUES (4, 'Харківський національний університет імені В. Н. Каразіна', 'Харків', 17000, 2389, 2219, '50.0074612717723', '36.23062056930873');");
        db.execSQL("INSERT INTO university VALUES (5, 'Харківський політехнічний інститут', 'Харків', 24000, 2550, 3298, '49.999023606807334', '36.24835021092398');");
        db.execSQL("INSERT INTO university VALUES (6, 'Національний університет біоресурсів і природокористування України', 'Київ', 26000, 2614, 3281, '50.38344273447251', '30.495999238617475');");
        db.close();
    }
    private void onStartPref2 (){
        db.execSQL("DROP TABLE IF EXISTS contacts");
        db.execSQL("DROP TABLE IF EXISTS contacts");

        db.execSQL("CREATE TABLE IF NOT EXISTS contacts (id INTEGER, name TEXT, surname TEXT, phoneNum TEXT, email TEXT)");
        db.execSQL("INSERT INTO contacts VALUES (0, 'Іван', 'Іванов', '0678339912', 'ivanov@gmail.com');");
        db.execSQL("INSERT INTO contacts VALUES (1, 'Лілія', 'Іванова', '0678562912', 'ivanovaL@gmail.com');");
        db.execSQL("INSERT INTO contacts VALUES (2, 'Катерина', 'Оліщук', '0671452376', 'Kolischuk@ukr.net');");
        db.execSQL("INSERT INTO contacts VALUES (3, 'Олександр', 'Терещенко', '0961010989', 'tereshSasha@gmail.com');");
        db.execSQL("INSERT INTO contacts VALUES (4, 'Кирил', 'Корніленко', '0632880090', 'kornilenko@ukr.net');");
        db.execSQL("INSERT INTO contacts VALUES (5, 'Анастасія', 'Токар', '0950302121', 'TokNastia@gmail.com');");
        db.execSQL("INSERT INTO contacts VALUES (6, 'Ірина', 'Тітаренко', '0932011987', 'TitarenkoI@gmail.com');");
        db.close();
    }
    public void onClick(View view){
        Intent intent = new Intent(MainActivity.this, UnivTableActivity.class);
        startActivity(intent);

    }
    public void onClickExcellence(View view){
        Intent intent = new Intent(MainActivity.this, UnivTableExcellenceActivity.class);
        startActivity(intent);

    }

}