package com.azizova.loftcoin.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.azizova.loftcoin.CirclePagerIndicatorDecoration;
import com.azizova.loftcoin.R;
import com.azizova.loftcoin.prefs.Settings;
import com.azizova.loftcoin.ui.main.MainActivity;

public class WelcomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SnapHelper snapHelper;
    private WelcomePageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        recyclerView = findViewById(R.id.pager);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this, RecyclerView.HORIZONTAL, false));
        recyclerView.addItemDecoration(new CirclePagerIndicatorDecoration());

        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        adapter = new WelcomePageAdapter();
        recyclerView.swapAdapter(adapter, false);

        final Settings settings = new Settings(getApplicationContext());
        findViewById(R.id.btn_start).setOnClickListener((v) -> {
            startActivity(new Intent(this, MainActivity.class));
            settings.doNotShowWelcomeScreenNextTime();
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        recyclerView.swapAdapter(null, false);
        snapHelper.attachToRecyclerView(null);
        super.onDestroy();
    }
}
