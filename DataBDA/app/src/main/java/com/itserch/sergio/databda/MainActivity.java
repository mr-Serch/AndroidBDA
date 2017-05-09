package com.itserch.sergio.databda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView mainListView ;
    private StudentOperations studentDBoperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDBoperation = new StudentOperations(this);
        studentDBoperation.open();

        List listStudents = studentDBoperation.getAllStudents();
        mainListView = (ListView) findViewById( R.id.mainListView );

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listStudents);
        mainListView.setAdapter(adapter);
    }

    public void agregarDato(View view) {

        ArrayAdapter adapter = (ArrayAdapter) mainListView.getAdapter();

        EditText text = (EditText) findViewById(R.id.editText1);
        Student stud = studentDBoperation.addStudent(text.getText().toString());

        adapter.add(stud);

    }

    public void borrarDato(View view) {

        ArrayAdapter adapter = (ArrayAdapter) mainListView.getAdapter();
        Student stud = null;

        if (adapter.getCount() > 0) {
            stud = (Student) adapter.getItem(0);
            studentDBoperation.deleteStudent(stud);
            adapter.remove(stud);
        }

    }

    @Override
    protected void onResume() {
        studentDBoperation.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        studentDBoperation.close();
        super.onPause();
    }
}
