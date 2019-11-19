package vn.fpoly.fpolybookcarclient.presenter.breakfast;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.model.breakfast.ModelBreakFast;
import vn.fpoly.fpolybookcarclient.model.objectClass.BreakFast;
import vn.fpoly.fpolybookcarclient.view.Fragment.IViewBreakFast;

public class PresenterBreakFast implements IPresenterBreakFast  {
    private ModelBreakFast modelBreakFast;
    private ArrayList<BreakFast>arrBreakFast = new ArrayList<>();
    private IViewBreakFast iViewBreakFast;

    public PresenterBreakFast(IViewBreakFast iViewBreakFast) {
        this.iViewBreakFast = iViewBreakFast;
        modelBreakFast = new ModelBreakFast();
    }

    @Override
    public void getListBreakFast() {
        modelBreakFast.dowloadListBreakFast(this);
    }

    @Override
    public void resualGetBreakFast(BreakFast breakFast) {
        arrBreakFast.add(breakFast);
        iViewBreakFast.displayBreakFast(arrBreakFast);
    }
}
