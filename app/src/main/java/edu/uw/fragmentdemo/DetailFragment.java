package edu.uw.fragmentdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private static final String IMDB_ARG = "imdb_id";

    private static final String TITLE_ARG = "title";

    public DetailFragment() {
        // Required empty public constructor
    }
    public static DetailFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putString(IMDB_ARG, movie.imdbId);
        args.putString(TITLE_ARG, movie.title);

        DetailFragment fragment - new DetailFragment();
        fragment.setArguments(args);
        return fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        TextView titleView = (TextView)rootView.findViewById(R.id.txtMovieTitle);
        TextView imdbView = (TextView)rootView.findViewById(R.id.txtMovieIMDB);

        Bundle args = getArguments();
        titleView.setText(args.getString(TITLE_ARG));
        imdbView.setText(args.getString(IMDB_ARG));

        return rootView;
    }

}
