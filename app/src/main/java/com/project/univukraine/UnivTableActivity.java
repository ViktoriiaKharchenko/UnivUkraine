package com.project.univukraine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.project.univukraine.model.University;
import com.project.univukraine.model.UniversityRepository;

import java.util.List;

public class UnivTableActivity extends AppCompatActivity {
    SQLiteDatabase db;
    UniversityRepository universityRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_univ_table);
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        universityRepository =  new UniversityRepository(getBaseContext(),
                getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null));
        List<University> universities = universityRepository.getAllUniversities();
        TableLayout tableLayout = findViewById(R.id.tableLayout);


        for(int i=0;i<universities.size();i++){
            Log.d("Rows",universities.get(i).toString());
            University university = universities.get(i);

            TableRow tableRow = new TableRow(getApplicationContext());
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            tableRow.setClickable(true);
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(UnivTableActivity.this, MapsActivity.class);
                    myIntent.putExtra("university", university); //Optional parameters
                    startActivity(myIntent);
                }
            });

            createDefaultTextView(tableRow, university.getName()+"\n"+university.getAddress(), Color.parseColor("#000000"), Color.parseColor("#ffffff"),50);
            createDefaultTextView(tableRow, university.getStudentAmount()+"", Color.parseColor("#000000"), Color.parseColor("#ffffff"),25);
            createDefaultTextView(tableRow, university.getWebometrix()+"", Color.parseColor("#000000"), Color.parseColor("#ffffff"),25);

            tableLayout.addView(tableRow);
          }


    }
    public void rowClick(View view){
        Intent intent = new Intent(UnivTableActivity.this, MainActivity.class);
        startActivity(intent);

    }
    private TextView createDefaultTextView(TableRow tableRow0, String value, int textColor, int bgColor, float weight) {
        TextView columsView = new TextView(getApplicationContext());
        columsView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, weight));
        columsView.setTextColor(textColor);
        columsView.setBackgroundColor(bgColor);
        columsView.setText(value);
        columsView.setMaxWidth(300);
        columsView.setPadding(10,10,10,10);
        columsView.setTextSize(18);
        columsView.setGravity(Gravity.CENTER);
        tableRow0.addView(columsView);
        return columsView;
    }
}