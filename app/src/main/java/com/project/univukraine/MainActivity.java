package com.project.univukraine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.project.univukraine.model.University;
import com.project.univukraine.model.UniversityRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    UniversityRepository universityRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        //onStartPref();
        universityRepository =  new UniversityRepository(getBaseContext(),
                getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null));
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

    public void onClick(View view){
        Intent intent = new Intent(MainActivity.this, UnivTableActivity.class);
        startActivity(intent);
//        List<University> universities = universityRepository.getAllUniversities();
//        TableLayout tableLayout = (TableLayout)findViewById(R.id.tab);
//        tableLayout.removeAllViews();
//
//        TableRow tableRow0 = new TableRow(getApplicationContext());
//        tableRow0.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//        tableRow0.setBackgroundColor(Color.parseColor("#aaffff"));
//        tableRow0.setWeightSum(100);
//
//        createDefaultTextView(tableRow0,"Id",Color.parseColor("#000000"), Color.parseColor("#ccffff"), 10);
//        createDefaultTextView(tableRow0,"Name", Color.parseColor("#000000"), Color.parseColor("#aaffff"),40);
//        createDefaultTextView(tableRow0,"Address", Color.parseColor("#000000"), Color.parseColor("#aaffff"), 10);
//        createDefaultTextView(tableRow0,"Student amount", Color.parseColor("#000000"), Color.parseColor("#aaffff"),10);
//        createDefaultTextView(tableRow0,"World Rank", Color.parseColor("#000000"), Color.parseColor("#aaffff"),10);
//        createDefaultTextView(tableRow0,"Excellence", Color.parseColor("#000000"), Color.parseColor("#aaffff"),10);
//        createDefaultTextView(tableRow0,"onMap", Color.parseColor("#000000"), Color.parseColor("#aaffff"),10);
//        tableLayout.addView(tableRow0);
//
//        for(int i=0;i<universities.size();i++){
//            Log.d("Rows",universities.get(i).toString());
//            TableRow tableRow = new TableRow(getApplicationContext());
//            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//            //Handler handler = null;
//            University university = universities.get(i);
//
//            createDefaultTextView(tableRow, university.getId()+"", Color.parseColor("#000000"), Color.parseColor("#ffffff"),3);
//            createDefaultTextView(tableRow, university.getName(), Color.parseColor("#000000"), Color.parseColor("#ffffff"),15);
//            createDefaultTextView(tableRow, university.getAddress(), Color.parseColor("#000000"), Color.parseColor("#ffffff"),5);
//            createDefaultTextView(tableRow, university.getStudentAmount()+"", Color.parseColor("#000000"), Color.parseColor("#ffffff"),5);
//            createDefaultTextView(tableRow, university.getWebometrix()+"", Color.parseColor("#000000"), Color.parseColor("#ffffff"),5);
//            createDefaultTextView(tableRow, university.getExcellence()+"", Color.parseColor("#000000"), Color.parseColor("#ffffff"),5);
//            Button button = new Button(getApplicationContext());
//            button.setText(university.getId()+"");
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Button button1 = (Button)(v);
//                    University university1 = universityRepository.getUniversityById(Integer.parseInt(button1.getText().toString()));
//                    Intent myIntent = new Intent(MainActivity.this, MapsActivity2.class);
//                    myIntent.putExtra("university", university); //Optional parameters
//                    startActivity(myIntent);
//                }
//            });
//            tableRow.addView(button);
//
//            tableLayout.addView(tableRow);
      //  }
    }

//    private TextView createDefaultTextView(TableRow tableRow0, String value, int textColor, int bgColor, float weight) {
//        TextView columsView = new TextView(getApplicationContext());
//        columsView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, weight));
//        columsView.setTextColor(textColor);
//        columsView.setBackgroundColor(bgColor);
//        columsView.setText(value);
//        columsView.setTextSize(12);
//        columsView.setMaxLines(12);
//        columsView.setGravity(Gravity.LEFT);
//        tableRow0.addView(columsView);
//        return columsView;
//    }
}