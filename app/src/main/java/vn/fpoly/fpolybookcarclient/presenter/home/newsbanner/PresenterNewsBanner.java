package vn.fpoly.fpolybookcarclient.presenter.home.newsbanner;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.home.newsbanner.ModelNewsBanner;
import vn.fpoly.fpolybookcarclient.model.objectClass.News;
import vn.fpoly.fpolybookcarclient.view.home.IViewCakeBanner;

public class PresenterNewsBanner implements IPresenterNewsBanner {
    private ModelNewsBanner modelNewsBanner;
    private IViewCakeBanner iViewCakeBanner;


    public PresenterNewsBanner(IViewCakeBanner  iViewCakeBanner) {
        this.iViewCakeBanner = iViewCakeBanner;
        modelNewsBanner = new ModelNewsBanner();

    }

    @Override
    public void getListCakeBanner() {
        modelNewsBanner.dowloaLisCake(this);
    }

    @Override
    public void resultGetListCake(ArrayList<News> cakesBannerList, ArrayList<News> PlaceBannerList,ArrayList<News>foodBannerList) {
        iViewCakeBanner.displayListCake(cakesBannerList,PlaceBannerList,foodBannerList);
    }


}
