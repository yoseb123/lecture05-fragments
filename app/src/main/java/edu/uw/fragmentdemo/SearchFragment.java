package edu.uw.fragmentdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private OnSearchListener callback;

    public interface OnSearchListener {
        public void onSearchSubmitted(String searchTerm);
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            callback = (OnSearchListener)context;
        } catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        final EditText text = (EditText)rootView.findViewById(R.id.txtSearch);
        Button button = (Button) rootView.findViewById(R.id.btnSearch);
        button.setOnClickListener(new View.OnClickListener()) {
            @Override
                public void onClick(View v) {
                String searchTerm = text.getText().toString();
                callback.onSearchSubmitted(searchTerm);
            }
        }
        return rootView;
    }

}
