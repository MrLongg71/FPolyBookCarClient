package vn.fpoly.fpolybookcarclient.library;

import android.view.KeyEvent;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.view.home.HomeFragment;

public class CallBackFragment {
    public static void CallbackHome(View view, final FragmentManager fragmentManager){
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        HomeFragment fragment = new HomeFragment();
                        fragmentTransaction.replace(R.id.conten, fragment, fragment.getTag()).commit();

                        return true;
                    }
                }
                return false;
            }
        });
    }

        public static void callBackpress(View view, final FragmentManager fragmentManager){
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            fragmentManager.popBackStack();
                            return true;
                        }

                    }
                    return false;
                }
            });
        }


}