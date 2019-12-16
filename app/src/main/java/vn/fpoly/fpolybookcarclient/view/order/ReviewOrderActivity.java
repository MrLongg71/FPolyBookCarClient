package vn.fpoly.fpolybookcarclient.view.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import vn.fpoly.fpolybookcarclient.Constans;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;
import vn.fpoly.fpolybookcarclient.model.objectClass.OderCar;
import vn.fpoly.fpolybookcarclient.model.objectClass.OrderFood;
import vn.fpoly.fpolybookcarclient.presenter.order.PresenterOrder;

public class ReviewOrderActivity extends AppCompatActivity implements IViewReview {

    private TextView txtNameDriverRV,txtLicenseDriverRV,txtPriceRV,txtPlaceGoRV,txtPlaceComeRV;
    private RatingBar rateRV;
    private Button btnReview;
    private PresenterOrder presenterOrder;
    private String idOrder = "";
    private String event = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_order);
        initView();
        presenterOrder.getOrder(idOrder,event);


    }

    private void initView() {
        Intent intent = getIntent();
        idOrder = intent.getStringExtra(Constans.REVIEW_ORDER_ID);
        event = intent.getStringExtra(Constans.REVIEW_ORDER_EVENT);
        Log.d("LONgKUTE", "onReceive: " + idOrder + " - " + event);

        txtNameDriverRV = findViewById(R.id.txtNameDriverRV);
        txtLicenseDriverRV = findViewById(R.id.txtLicenseDriverRV);
        txtPriceRV = findViewById(R.id.txtPriceRV);
        txtPlaceGoRV = findViewById(R.id.txtPlaceGoRV);
        txtPlaceComeRV = findViewById(R.id.txtPlaceComeRV);
        btnReview = findViewById(R.id.btnReview);
        rateRV = findViewById(R.id.rateRV);
        presenterOrder = new PresenterOrder(this);

    }


    @Override
    public void onSuccessFood(OrderFood orderFood, Driver driver) {
        Log.d("LONgKUTE", "onSuccessFood: ");
        txtNameDriverRV.setText(driver.getName());
        txtLicenseDriverRV.setText(driver.getLicenseplate());
        txtPlaceComeRV.setText(orderFood.getPlaceNameC());
        txtPlaceGoRV.setText(orderFood.getPlaceNameRes());
        txtPriceRV.setText(orderFood.getPrice() + " VND");
        onReview(orderFood.getKeyDriver());

    }

    @Override
    public void onSuccessCar(OderCar oderCar, Driver driver) {
        Log.d("LONgKUTE", "onSuccessCar: ");
        txtNameDriverRV.setText(driver.getName());
        txtLicenseDriverRV.setText(driver.getLicenseplate());
        txtPlaceComeRV.setText(oderCar.getPlacenamecome());
        txtPlaceGoRV.setText(oderCar.getPlacenamego());
        txtPriceRV.setText(oderCar.getPrice() + " VND");
        onReview(oderCar.getKeydriver());
    }

    @Override
    public void onSuccessRate() {

    }

    @Override
    public void onFailed() {
        finish();
    }

    private void onReview(final String idDriver){
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenterOrder.setRateDriver(rateRV.getRating(),idDriver);
            }
        });
    }

}
