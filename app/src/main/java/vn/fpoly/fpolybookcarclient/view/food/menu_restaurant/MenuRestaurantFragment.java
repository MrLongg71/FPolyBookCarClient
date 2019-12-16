package vn.fpoly.fpolybookcarclient.view.food.menu_restaurant;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.Objects;

import vn.fpoly.fpolybookcarclient.Constans;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.adapter.food.RestaurantMenuFoodAdapter;
import vn.fpoly.fpolybookcarclient.adapter.food.RestaurantMenuFoodCartItemAdapter;
import vn.fpoly.fpolybookcarclient.library.CallBackFragment;
import vn.fpoly.fpolybookcarclient.model.objectClass.BillFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.Driver;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodMenu;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;
import vn.fpoly.fpolybookcarclient.presenter.food.menu_restaurant.PresenterMenuRes;
import vn.fpoly.fpolybookcarclient.presenter.maps.PresenterGoogleMap;
import vn.fpoly.fpolybookcarclient.view.activity.HomeActivity;
import vn.fpoly.fpolybookcarclient.view.maps.GoogleMapActivity;
import vn.fpoly.fpolybookcarclient.view.maps.ViewGoogleMap;

import static android.content.Context.MODE_PRIVATE;

public class MenuRestaurantFragment extends Fragment implements IViewMenuRes, CallbackRestaurantMenuFood, CallbackRestaurantMenuFoodCartItem {

    private ImageView imgBackgroundMenuRes;
    private TextView txtStartMenuRes, txtTimeMenuRes, txtDistanceMenuRes, txtTotalPriceCart,txtDetailRestaurant;
    private Button btnBookFoodMenuRes;
    private RecyclerView recyclerviewMenuRestaurant, recyclerItemCartFoodMenuRes;
    private PresenterMenuRes presenterMenuRes;
    private Toolbar toolbarMenuRes;
    private Restaurant restaurant;
    private boolean checked_heart = false;
    private ArrayList<FoodMenu> foodMenuListCart = new ArrayList<>();
    private ArrayList<BillFood> billFoodArrayList = new ArrayList<>();
    private BottomSheetBehavior bottomSheetBehavior;
    private int amount = 0;
    private NestedScrollView nestedScrollMenuFoodRes;
    private RestaurantMenuFoodCartItemAdapter restaurantMenuFoodCartItemAdapter;
    private PresenterGoogleMap presenterGoogleMap;
    private LatLng locationRes,locationCurrent;
    private String addresCurrent = "";
    private  int priceTotal = 0;
    private ProgressDialog progressDialog;
    private SearchView searchView;
    private   RestaurantMenuFoodAdapter restaurantMenuFoodAdapter;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_restaurant, container, false);
        initView(view);
        return view;
    }

    private void toolbar(View view) {
        toolbarMenuRes = view.findViewById(R.id.toolbarMenuRes);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbarMenuRes);
        toolbarMenuRes.setTitleTextColor(Color.BLACK);
        setHasOptionsMenu(true);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);
        CallBackFragment.callBackpress(view,getActivity().getSupportFragmentManager());
        toolbarMenuRes.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void initView(View view) {
        imgBackgroundMenuRes = view.findViewById(R.id.imgBackgroudMenuRes);
        txtStartMenuRes = view.findViewById(R.id.txtStartMenuRes);
        txtDistanceMenuRes = view.findViewById(R.id.txtDistanceMenuRes);
        txtTimeMenuRes = view.findViewById(R.id.txtTimeMenuRes);
        btnBookFoodMenuRes = view.findViewById(R.id.btnBookFoodMenuRes);
        recyclerviewMenuRestaurant = view.findViewById(R.id.reycelviewMenuRestaurant);
        nestedScrollMenuFoodRes = view.findViewById(R.id.nestedScrollMenuFoodRes);
        txtDetailRestaurant     = view.findViewById(R.id.txtDetailRestaurant);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading");
        presenterMenuRes = new PresenterMenuRes(this);

        toolbar(view);

        initEvent();
        LinearLayout linearLayoutBottomSheet = view.findViewById(R.id.layout_bottom_sheet);
        txtTotalPriceCart = view.findViewById(R.id.txtTotalPriceCart);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayoutBottomSheet);
        recyclerItemCartFoodMenuRes = view.findViewById(R.id.recyclerItemCartFoodMenuRes);
        linearLayoutBottomSheet.setClickable(true);
        linearLayoutBottomSheet.setFocusable(true);
//        linearLayoutBottomSheet.setVisibility(View.GONE);

    }

    @SuppressLint("SetTextI18n")
    private void initEvent() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            restaurant = bundle.getParcelable(Constans.KEY_BUNDEL_RESTAURANT);
            Glide.with(Objects.requireNonNull(getActivity())).load(restaurant.getImage()).into(imgBackgroundMenuRes);
            toolbarMenuRes.setTitle(restaurant.getName());
            String distance = bundle.getString(Constans.KEY_BUNDEL_RESTAURANT_DISTANCETO);
            assert distance != null;
            double time = 2.5 * Double.parseDouble(distance);
            txtTimeMenuRes.setText((time + ""));
            txtStartMenuRes.setText(restaurant.getRate() + "");
            txtDistanceMenuRes.setText(distance + " km");
            txtDetailRestaurant.setText(restaurant.getDetail());
            presenterMenuRes.getListMenuFood(restaurant.getKey());
            locationRes = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());
            addresCurrent = bundle.getString(Constans.KEY_BUNDEL_RESTAURANT_ADDRES_NAME_CURRENT);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("toado", 0);
            locationCurrent = new LatLng(Double.parseDouble(sharedPreferences.getString("locationlatitude", "")), Double.parseDouble(sharedPreferences.getString("locationlongitude", "")));

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void displayListMenuFood(ArrayList<FoodMenu> foodMenulist) {
        LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerviewMenuRestaurant.setLayoutManager(layoutManager);

         restaurantMenuFoodAdapter = new RestaurantMenuFoodAdapter(getActivity(), R.layout.custom_item_menu_res, foodMenulist, this);
        recyclerviewMenuRestaurant.setAdapter(restaurantMenuFoodAdapter);
        restaurantMenuFoodAdapter.notifyDataSetChanged();
        listenerScroll();

    }

    @Override
    public void displayListMenuFoodFailed() {

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);
        getCheckedHeartRestaurant();
        searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String query) {
              return false;
          }

          @Override
          public boolean onQueryTextChange(String newText) {
              restaurantMenuFoodAdapter.search(newText);
              restaurantMenuFoodAdapter.notifyDataSetChanged();
              return false;
          }
      });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_heart:
                if (checked_heart) {
                    item.setIcon(R.drawable.ic_heart);
                    checkedHeartRestaurant(!checked_heart);
                    checked_heart = false;
                } else {
                    item.setIcon(R.drawable.ic_heart_checked);
                    checkedHeartRestaurant(!checked_heart);
                    checked_heart = true;
                }
                break;
            case R.id.menu_search:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkedHeartRestaurant(boolean checked) {
        if (restaurant != null) {
            SharedPreferences.Editor editor = Objects.requireNonNull(getActivity()).getSharedPreferences(Constans.KEY_RESTAURANT, MODE_PRIVATE).edit();
            editor.putBoolean(restaurant.getKey(), checked);
            editor.apply();
        }

    }

    private void getCheckedHeartRestaurant() {
        if (restaurant != null) {
            SharedPreferences prefs = Objects.requireNonNull(getActivity()).getSharedPreferences(Constans.KEY_RESTAURANT, MODE_PRIVATE);
            boolean checked = prefs.getBoolean(restaurant.getKey(), false);
            Menu menu = toolbarMenuRes.getMenu();
            MenuItem item = menu.findItem(R.id.menu_heart);
            if (checked) {
                item.setIcon(R.drawable.ic_heart_checked);
            } else {
                item.setIcon(R.drawable.ic_heart);
            }
        }
    }

    @Override
    public void onClickItemMenuToCart(FoodMenu foodMenu) {
        addToCart(foodMenu);
    }

    private void addToCart(FoodMenu foodMenu) {
            foodMenuListCart.add(foodMenu);
            if(billFoodArrayList.size() ==0){
                billFoodArrayList.add(new BillFood("", foodMenu.getKeyfood(), 1));
            }else {
                for(BillFood billFood : billFoodArrayList){
                    if(foodMenu.getKeyfood().equals(billFood.getKeyFood())){
                        Log.d("LONgKUTE", "addToCart: aa");
                        billFood.setAmountBuy(billFood.getAmountBuy() + 1);
                        break;
                    }else {
                        Log.d("LONgKUTE", "addToCart: cc");

                        billFoodArrayList.add(new BillFood("", foodMenu.getKeyfood(), 1));
                    }
                }
            }

            setTotalCart();
            LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            recyclerItemCartFoodMenuRes.setLayoutManager(layoutManager);

            restaurantMenuFoodCartItemAdapter = new RestaurantMenuFoodCartItemAdapter(getActivity(), R.layout.custom_item_menu_res_cart, foodMenuListCart, billFoodArrayList, this);
            recyclerItemCartFoodMenuRes.setAdapter(restaurantMenuFoodCartItemAdapter);
            restaurantMenuFoodCartItemAdapter.notifyDataSetChanged();
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void listenerScroll() {
        nestedScrollMenuFoodRes.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initIncreasing(int i, TextView txtAmount) {
        BillFood billFood = billFoodArrayList.get(i);
        txtAmount.setText(billFood.getAmountBuy() + "");
        amount = billFood.getAmountBuy() + 1;
        billFood.setAmountBuy(amount);
        txtAmount.setText(billFood.getAmountBuy() + "");
        setTotalCart();

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initReduction(int i, TextView txtAmount) {
        if (!txtAmount.getText().toString().equals("1")) {
            BillFood billFood = billFoodArrayList.get(i);
            txtAmount.setText(billFood.getAmountBuy() + "");
            amount = billFood.getAmountBuy() - 1;
            billFood.setAmountBuy(amount);
            txtAmount.setText(billFood.getAmountBuy() + "");
            setTotalCart();
        }
    }


    @SuppressLint("SetTextI18n")
    private void setTotalCart() {
        priceTotal = 0;
        for (int i = 0; i < billFoodArrayList.size(); i++) {
            priceTotal += billFoodArrayList.get(i).getAmountBuy() * Integer.parseInt(foodMenuListCart.get(i).getPrice());
        }
        txtTotalPriceCart.setText(priceTotal + "K");
        initBookFood();
    }

    private void initBookFood() {
        btnBookFoodMenuRes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                Intent intent  = new Intent(getActivity(),GoogleMapActivity.class);

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(Constans.KEY_ORDERFOOD_BILLLIST, billFoodArrayList);
                bundle.putParcelable(Constans.KEY_ORDERFOOD_RESTAURANT, restaurant);
                bundle.putString(Constans.KEY_ORDERFOOD_ADDRES_CURRENT,addresCurrent);
                bundle.putInt(Constans.KEY_ORDERFOOD_PRICE,priceTotal);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }


}
