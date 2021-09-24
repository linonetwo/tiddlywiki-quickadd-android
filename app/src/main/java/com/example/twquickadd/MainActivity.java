package com.example.twquickadd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Bean> mData = new ArrayList<Bean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Auto open IME after delay so Activity is initialized
        EditText textArea = findViewById(R.id.textArea);
        textArea.requestFocus();
        Handler timerHandler = new Handler(Looper.getMainLooper());
        Runnable timerRunnable = () -> {
            InputMethodManager manager =
                    (InputMethodManager) textArea.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.showSoftInput(textArea, 0);
        };
        timerHandler.postDelayed(timerRunnable, 500);

        ListView listView = findViewById(R.id.listView);
        ListAdaptor adaptor = new ListAdaptor(mData, this);
        listView.setAdapter(adaptor);
        // handle add
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bean bean = new Bean();
                String content = textArea.getText().toString();
                if (content.replaceAll(" +", "").length() == 0) {
                    return;
                }
                bean.setContent(textArea.getText().toString());
                mData.add(bean);
                adaptor.notifyDataSetChanged();
                Snackbar.make(view, "Added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                textArea.setText("");
            }
        });

        // try sync to desktop
        startService(new Intent(this, SyncService.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}