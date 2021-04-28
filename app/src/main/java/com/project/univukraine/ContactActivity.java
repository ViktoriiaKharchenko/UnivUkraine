package com.project.univukraine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.project.univukraine.model.ContactRepository;
import com.project.univukraine.model.Contacts;

import java.util.List;

public class ContactActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ContactRepository contactRepository;
    TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        contactRepository =  new ContactRepository(getBaseContext(),
                getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null));
        List<Contacts> contacts = contactRepository.getAllContacts();
        tableLayout = findViewById(R.id.tableLayout);
        makeTable(contacts);
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
                intent = new Intent(ContactActivity.this, UnivTableActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_main:
                intent = new Intent(ContactActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_contact:
                intent = new Intent(ContactActivity.this, ContactActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void contactFilter(View view){
        List<Contacts> contacts = contactRepository.getContactFilter();
        makeTable(contacts);
    }
    private void cleanTable(TableLayout table) {

        int childCount = table.getChildCount();

        // Remove all rows except the first one
        if (childCount > 1) {
            table.removeViews(1, childCount - 1);
        }
    }

    private void makeTable (List<Contacts> contacts){
        cleanTable(tableLayout);
        for(int i=0;i<contacts.size();i++){
            Log.d("Rows",contacts.get(i).toString());
            Contacts contact = contacts.get(i);

            TableRow tableRow = new TableRow(getApplicationContext());
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

            createDefaultTextView(tableRow, contact.getName()+"\n"+contact.getSurname(), Color.parseColor("#000000"), Color.parseColor("#ffffff"),50);
            createDefaultTextView(tableRow, contact.getPhoneNum()+"", Color.parseColor("#000000"), Color.parseColor("#ffffff"),25);
            createDefaultTextView(tableRow, contact.getEmail()+"", Color.parseColor("#000000"), Color.parseColor("#ffffff"),25);

            tableLayout.addView(tableRow);
        }
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