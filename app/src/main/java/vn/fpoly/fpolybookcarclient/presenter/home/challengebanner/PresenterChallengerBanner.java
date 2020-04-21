package vn.fpoly.fpolybookcarclient.presenter.home.challengebanner;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.challengebanner.ModelChallengeBanner;
import vn.fpoly.fpolybookcarclient.model.objectClass.ChallengeBanner;
import vn.fpoly.fpolybookcarclient.view.home.IViewChallengeBanner;

public class PresenterChallengerBanner implements IPresenterChallengeBanner {
    private ModelChallengeBanner modelChallengeBanner;
    private IViewChallengeBanner iViewChallengeBanner;
    private ArrayList<ChallengeBanner>arrChallenge = new ArrayList<>();

    public PresenterChallengerBanner(IViewChallengeBanner iViewChallengeBanner) {
        this.iViewChallengeBanner = iViewChallengeBanner;
        modelChallengeBanner = new ModelChallengeBanner();
    }

    @Override
    public void getListChallenge() {
        modelChallengeBanner.dowloadListChallege(this);
    }

    @Override
    public void resultGetListChallenge(ChallengeBanner challengeBanner) {
        arrChallenge.add(challengeBanner);
        iViewChallengeBanner.displayListChallenge(arrChallenge);
    }
}
