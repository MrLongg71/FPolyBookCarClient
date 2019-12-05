package vn.fpoly.fpolybookcarclient.view.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.library.CallBackFragment;

public class AccountFragment extends Fragment {
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();
        CallBackFragment.CallbackHome(view,fragmentManager);
        return view;
    }
}
