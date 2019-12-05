package vn.fpoly.fpolybookcarclient.view.food.menu_restaurant;

import android.content.SharedPreferences;
import android.graphics.Color;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;


import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.Constans;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.adapter.food.RestaurentMenuFoodAdapter;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodMenu;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;
import vn.fpoly.fpolybookcarclient.presenter.food.menu_restaurant.PresenterMenuRes;

import static android.content.Context.MODE_PRIVATE;

public class MenuRestaurantFragment extends Fragment implements IViewMenuRes {

    private ImageView imgBackgroudMenuRes;
    private TextView txtStartMenuRes, txtTimeMenuRes, txtDistanceMenuRes;
    private RecyclerView reycelviewMenuRestaurant;
    private PresenterMenuRes presenterMenuRes;
    private Toolbar toolbarMenuRes;
    private Restaurant restaurant;
    private boolean checked_heart = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_restaurant, container, false);

        initView(view);

        return view;
    }

    private void toolbar(View view) {
        toolbarMenuRes = view.findViewById(R.id.toolbarMenuRes);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarMenuRes);
        toolbarMenuRes.setTitleTextColor(Color.BLACK);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarMenuRes.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                getActivity().getSupportFragmentManager().popBackStack();


//                CallBackFragment.callBackpress(view,getActivity().getSupportFragmentManager());
            }
        });
    }

    private void initView(View view) {
        imgBackgroudMenuRes = view.findViewById(R.id.imgBackgroudMenuRes);
        txtStartMenuRes = view.findViewById(R.id.txtStartMenuRes);
        txtDistanceMenuRes = view.findViewById(R.id.txtDistanceMenuRes);
        txtTimeMenuRes = view.findViewById(R.id.txtTimeMenuRes);
        reycelviewMenuRestaurant = view.findViewById(R.id.reycelviewMenuRestaurant);
        presenterMenuRes = new PresenterMenuRes(this);
        toolbar(view);
        initEvent();

    }

    private void initEvent() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            restaurant = bundle.getParcelable(Constans.KEY_BUNDEL_RESTAURANT);
            Glide.with(getActivity()).load(restaurant.getImage()).into(imgBackgroudMenuRes);
            toolbarMenuRes.setTitle(restaurant.getName());
            String distance = bundle.getString(Constans.KEY_BUNDEL_RESTAURANT_DISTANCETO);
            double time = 2.5 * Double.parseDouble(distance);
            txtTimeMenuRes.setText((time + ""));
            txtStartMenuRes.setText(restaurant.getRate() + "");
            txtDistanceMenuRes.setText(distance + " km");
            presenterMenuRes.getListMenuFood(restaurant.getKey());

        }


    }


    @Override
    public void displayListMenuFood(ArrayList<FoodMenu> foodMenulist) {
        LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        reycelviewMenuRestaurant.setLayoutManager(layoutManager);

        RestaurentMenuFoodAdapter restaurentMenuFoodAdapter = new RestaurentMenuFoodAdapter(getActivity(), R.layout.custom_item_menu_res, foodMenulist);
        reycelviewMenuRestaurant.setAdapter(restaurentMenuFoodAdapter);
        restaurentMenuFoodAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayListMenuFoodFailed() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);
        getCheckedHeartRestaurant();
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
            SharedPreferences.Editor editor = getActivity().getSharedPreferences(Constans.KEY_RESTAURANT, MODE_PRIVATE).edit();
            editor.putBoolean(restaurant.getKey(), checked);
            editor.apply();
        }

    }

    private void getCheckedHeartRestaurant() {
        if (restaurant != null) {
            SharedPreferences prefs = getActivity().getSharedPreferences(Constans.KEY_RESTAURANT, MODE_PRIVATE);
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
}
