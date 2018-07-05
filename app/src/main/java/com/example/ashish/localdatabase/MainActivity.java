package com.example.ashish.localdatabase;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// create a class from java> com.example...

public class MainActivity extends AppCompatActivity {
    Button btn,btn1;
    EditText name, surname, marks;

    SQLlitehelper myDB;         // initialize the new SQL DATABASE you created

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB =  new SQLlitehelper(this);

        btn = (Button)findViewById(R.id.btn);
        btn1 = (Button)findViewById(R.id.btn1);
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        marks = (EditText) findViewById(R.id.marks);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean insertdata = myDB.insertdata(name.getText().toString(),surname.getText().toString(),marks.getText().toString());
                if(insertdata==true)
                    Toast.makeText(getApplicationContext(),"Value is inserted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Value is not inserted", Toast.LENGTH_SHORT).show();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res =myDB.getAlldata();

                if (res.getCount()==0){

                    showMessage("Error","Nothing method");
                    return;
                }

                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID"+res.getString(0)+"\n");
                    buffer.append("name"+res.getString(1)+"\n");
                    buffer.append("surname"+res.getString(2)+"\n");
                    buffer.append("marks"+res.getString(3)+"\n");
                }

                showMessage("Data",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Message);
        builder.show();
    }
}
