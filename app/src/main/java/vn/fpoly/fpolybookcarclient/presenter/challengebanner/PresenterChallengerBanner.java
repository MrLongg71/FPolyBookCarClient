package vn.fpoly.fpolybookcarclient.presenter.challengebanner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.challengebanner.ModelChallengeBanner;
import vn.fpoly.fpolybookcarclient.model.objectClass.ChallengeBanner;
import vn.fpoly.fpolybookcarclient.view.Fragment.IViewChallengeBanner;

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
