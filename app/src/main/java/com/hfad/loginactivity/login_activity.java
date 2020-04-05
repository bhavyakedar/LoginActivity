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

public class login_activity extends AppCompatActivity {
    public static String USER_NAME = "user_name";
    public static String PASS_WORD = "pass_word";
    EditText username, password;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.button);
        Intent in = getIntent();
        final String unreal = in.getStringExtra(login_activity.USER_NAME);
        final String pwreal = in.getStringExtra(login_activity.PASS_WORD);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
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
                String u_n = username.getText().toString();
                String p_w = password.getText().toString();
                if( u_n.matches(unreal) && p_w.matches(pwreal))
                {
                    Intent intent = new Intent(login_activity.this,final_activity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
