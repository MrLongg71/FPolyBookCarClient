package vn.fpoly.fpolybookcarclient.presenter.home.newsbanner;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.objectClass.News;

public interface IPresenterNewsBanner {
    void getListCakeBanner();
    void resultGetListCake(ArrayList<News> cakesBannerList,ArrayList<News> PlaceBannerList,ArrayList<News>foodBannerList);
}
