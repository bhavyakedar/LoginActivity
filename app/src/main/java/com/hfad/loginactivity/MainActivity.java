package com.hfad.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextWatcher textWatcher;
    EditText username, password;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_up_activity);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        button =(Button) findViewById(R.id.button);
        SQLiteOpenHelper SqLiteOpenHelper = new DatabaseHelper(this);
        final SQLiteDatabase db = SqLiteOpenHelper.getReadableDatabase();
        final Cursor cursor = db.query("CREDENTIALS",
                                        new String[] {"USERNAME","PASSWORD"},
                                null, null,
                                null,null,null);
        if(cursor.moveToFirst())
        {
            String u_n_real = cursor.getString(0);
            String p_w_real = cursor.getString(1);
            cursor.close();
            Intent intent = new Intent(MainActivity.this,login_activity.class);
            intent.putExtra(login_activity.USER_NAME,u_n_real);
            intent.putExtra(login_activity.PASS_WORD,p_w_real);
            startActivity(intent);
            finish();
        }
            textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String user_name = username.getText().toString();
                String pass_word = password.getText().toString();
                if( !user_name.isEmpty() && !pass_word.isEmpty())
                {
                    button.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        username.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete("CREDENTIALS", null,null);
                String u_n_real, p_w_real;
                u_n_real = username.getText().toString();
                p_w_real = password.getText().toString();
                DatabaseHelper.insertEntry(db,u_n_real,p_w_real);
                Toast toast = Toast.makeText(MainActivity.this,"Credentials are set!! Please close the app and restart it.",Toast.LENGTH_LONG);
                toast.show();
                finish();
            }
        });
    }
}
