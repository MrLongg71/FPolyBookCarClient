package vn.fpoly.fpolybookcarclient.view.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import vn.fpoly.fpolybookcarclient.adapter.history.HistoryCarAdapter;
import vn.fpoly.fpolybookcarclient.adapter.history.HistoryFoodAdapter;
import vn.fpoly.fpolybookcarclient.library.CallBackFragment;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.model.objectClass.OderCar;
import vn.fpoly.fpolybookcarclient.model.objectClass.OrderFood;
import vn.fpoly.fpolybookcarclient.presenter.history.PresenterHistoryOrder;

public class HistoryFragment extends Fragment implements IVIewHistoryOrder {
    private RecyclerView recyclerViewHistoryOrderCar,recyclerViewHistoryOrderFood;
    private HistoryCarAdapter historyCarAdapter;
    private HistoryFoodAdapter historyFoodAdapter;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        recyclerViewHistoryOrderCar = view.findViewById(R.id.recyclerViewHistoryOrderCar);
        recyclerViewHistoryOrderFood = view.findViewById(R.id.recyclerViewHistoryOrderFood);
        fragmentManager = getActivity().getSupportFragmentManager();
        CallBackFragment.CallbackHome(view,fragmentManager);
        PresenterHistoryOrder presenterHistoryOrder = new PresenterHistoryOrder(this);
        presenterHistoryOrder.getListOrder();


        return view;
    }

    @Override
    public void displayListOrder(ArrayList<OderCar> oderCars, ArrayList<OrderFood> orderFoods) {
        recyclerViewHistoryOrderCar.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerViewHistoryOrderCar.setLayoutManager(layoutManager);
        historyCarAdapter = new HistoryCarAdapter(getActivity(),R.layout.custom_item_history_order,oderCars);
        recyclerViewHistoryOrderCar.setAdapter(historyCarAdapter);

        recyclerViewHistoryOrderFood.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerViewHistoryOrderFood.setLayoutManager(manager);
        historyFoodAdapter = new HistoryFoodAdapter(getActivity(),R.layout.custom_item_history_order,orderFoods);
        recyclerViewHistoryOrderFood.setAdapter(historyFoodAdapter);

        historyFoodAdapter.notifyDataSetChanged();
        historyCarAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayListOrderFailed() {

    }
}
