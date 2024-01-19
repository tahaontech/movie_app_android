package com.example.movie.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.movie.Adapters.ActorListAdapter;
import com.example.movie.Adapters.CategoryEachFilmListAdapter;
import com.example.movie.Adapters.CategoryListAdapter;
import com.example.movie.Domains.FilmItem;
import com.example.movie.R;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
    private TextView titleTxt, movieRateTxt, movieTimeTxt, movieSummaryTxt, movieActorsInfoTxt;
    private int idFilm;
    private ImageView pic2, backImg;
    private RecyclerView.Adapter adapterActorsList, adapterCategory;
    private RecyclerView recyclerViewActors, recyclerViewCategories;
    private NestedScrollView scrollview;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        idFilm = getIntent().getIntExtra("id", 0);
        initView();

        sendRequest();
    }

    private void sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollview.setVisibility(View.GONE);

        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm,
        response -> {
            Gson gson = new Gson();
            progressBar.setVisibility(View.GONE);
            scrollview.setVisibility(View.VISIBLE);

            FilmItem item = gson.fromJson(response, FilmItem.class);

            Glide.with(DetailActivity.this)
                    .load(item.getPoster())
                    .into(pic2);

            titleTxt.setText(item.getTitle());
            movieRateTxt.setText(item.getImdbRating());
            movieTimeTxt.setText(item.getRuntime());
            movieSummaryTxt.setText(item.getPlot());
            movieActorsInfoTxt.setText(item.getActors());

            if (item.getImages() != null)
            {
                adapterActorsList = new ActorListAdapter(item.getImages());
                recyclerViewActors.setAdapter(adapterActorsList);
            }

            if (item.getGenres() != null)
            {
                adapterCategory = new CategoryEachFilmListAdapter(item.getGenres());
                recyclerViewCategories.setAdapter(adapterCategory);
            }
        }, error -> {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        });
        mRequestQueue.add(mStringRequest);
    }

    private void initView() {
        titleTxt = findViewById(R.id.movieNameTxt);
        progressBar = findViewById(R.id.progressBarDetail);
        scrollview = findViewById(R.id.detailsScrollView);
        pic2 = findViewById(R.id.picDetail);
        movieRateTxt = findViewById(R.id.movieStar);
        movieTimeTxt = findViewById(R.id.movieTime);
        movieSummaryTxt = findViewById(R.id.movieSummary);
        movieActorsInfoTxt = findViewById(R.id.movieActorInfo);
        backImg = findViewById(R.id.backImg);

        recyclerViewCategories = findViewById(R.id.genreView);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerViewActors = findViewById(R.id.imagesRecycler);
        recyclerViewActors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backImg.setOnClickListener(v -> finish());

    }
}