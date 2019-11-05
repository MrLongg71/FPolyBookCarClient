package vn.fpoly.fpolybookcarclient.view.Fragment;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.adapter.home.CakeBannerAdapter;
import vn.fpoly.fpolybookcarclient.model.objectClass.CakesBanner;

public interface IViewCakeBanner {
    void displayListCake(ArrayList<CakesBanner> arrCakeBanner);
}
