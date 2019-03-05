package com.bismillah.bismillahsub2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bismillah.bismillahsub2.R;
import com.bismillah.bismillahsub2.adapter.Helper;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    private ImageView ivBackdrop;
    private TextView tvTitle, tvOverview, tvRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivBackdrop = findViewById(R.id.detail_image_poster);
        tvTitle = findViewById(R.id.detail_tv_title);
        tvOverview = findViewById(R.id.detail_tv_overview);
        tvRelease = findViewById(R.id.detail_tv_releaseDate);

        Intent intent = getIntent();
        String backdrop = intent.getStringExtra(Helper.BACKDROP);
        String title = intent.getStringExtra(Helper.TITLE);
        String overview = intent.getStringExtra(Helper.OVERVIEW);
        String release = intent.getStringExtra(Helper.RELEASE_DATE);

        getSupportActionBar().setTitle(title);
        tvTitle.setText(title);
        tvOverview.setText(overview);
        tvRelease.setText(release);
        Glide.with(this).load(backdrop).into(ivBackdrop);
        // TODO: 10/19/2018 fokus ke detail dulu aja di lengkapin
    }
}
