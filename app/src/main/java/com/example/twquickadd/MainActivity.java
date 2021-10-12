package com.example.twquickadd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.twquickadd.databinding.TiddlerViewModel;
import com.example.twquickadd.room.Tiddler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private TiddlerViewModel mListViewModel;

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

        // prepare list's ViewModel, associate ViewModel with Activity
        mListViewModel = new ViewModelProvider(this).get(TiddlerViewModel.class);
        // Render list
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView listView = findViewById(R.id.listView);
        listView.setLayoutManager(linearLayoutManager);
        TiddlerListAdaptor adaptor = new TiddlerListAdaptor(new TiddlerListAdaptor.TiddlerDiff(), this);
        listView.setAdapter(adaptor);
        // connect viewModel with list
        mListViewModel.getAllTiddlers().observe(this, list -> adaptor.submitList(list));
        // handle add
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tiddler tiddler = new Tiddler();
                String content = textArea.getText().toString();
                if (content.replaceAll(" +", "").length() == 0) {
                    return;
                }
                tiddler.setContent(textArea.getText().toString());
                mListViewModel.addNewTiddler(tiddler);
                adaptor.notifyDataSetChanged();
                Snackbar.make(view, "Added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                textArea.setText("");
            }
        });

        // try sync to desktop
        startService(new Intent(this, SyncService.class));

        SharedPreferences sp = getSharedPreferences("WikiServiceInfo", Context.MODE_PRIVATE);
        sp.edit().putString("url", "192.xxx").apply();
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