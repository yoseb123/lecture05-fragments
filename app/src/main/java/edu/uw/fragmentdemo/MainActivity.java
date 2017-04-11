package edu.uw.fragmentdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MoviesFragment.OnMovieClickListener {

    private static final String TAG = "MainActivity";

    private SearchFragment searchFragment;
    private MoviesFragment moviesFragment;
    private DetailFragment detailFragment;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchFragment = SearchFragment.newInstance();
        viewPager = (ViewPager)findViewById(R.id.pager);
        pagerAdapter = new MoviePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onSearchSubmitted(String searchTerm) {
        moviesFragment = MoviesFragment.newInstance(searchTerm);
        pagerAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(1);
    }

    @Override
    public void onMovieSelected(Movie movie) {
        Log.v(TAG, "Detail for " + movie);
        detailFragment = DetailFragment.newInstance(movie.toString(), movie.imdbId);
        pagerAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(2);
    }

    private class MoviePagerAdapter extends FragmentStatePagerAdapter {
        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) return searchFragment;
            if (position == 1) return moviesFragment;
            if (position == 2) return detailFragment;
            return null;
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            if(moviesFragment == null) {
                return 1;
            } else if (detailFragment == null) {
                return 2;
            } else {
                return 3;
            }
        }

        @Override
        public void onBackPressed() {
            if (viewPager.getCurrentItem() == 0) {
                super.onBackPressed();
            } else {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        }
    }

    //respond to search button clicking
    public void handleSearchClick(View v){
        EditText text = (EditText)findViewById(R.id.txtSearch);
        String searchTerm = text.getText().toString();

        //create the fragment
        MoviesFragment fragment = MoviesFragment.newInstance(searchTerm);

        FragmentManager fm = getSupportFragmentManager();
      //  MoviesFragment fragment = (MoviesFragment)fm.findFragmentById(R.id.container);

        FragmentTransaction ft = fm.beginTransaction();
        //add(), remove(), replace()
        ft.replace(R.id.container, fragment, "MovieFragment");
        ft.commit();

//        downloadMovieData(searchTerm);
        //myMovie1Fragment.downloadMovieData(searchTerm);
        fragment.downloadMovieData(searchTerm);
    }

    @Override
    public void onMovieClick(Movie movie) {
        DetailFragment fragment = DetailFragment.newInstance(movie);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, "DetailFragment")
                .addToBackStack(null)
                .commit();
    }
}
