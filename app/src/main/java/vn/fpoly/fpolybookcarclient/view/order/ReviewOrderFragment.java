package vn.fpoly.fpolybookcarclient.view.order;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.fpoly.fpolybookcarclient.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewOrderFragment extends Fragment {


    public ReviewOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_order, container, false);
    }

}
