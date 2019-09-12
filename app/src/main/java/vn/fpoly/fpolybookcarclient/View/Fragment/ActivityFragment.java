package vn.fpoly.fpolybookcarclient.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.Adapter.Activity.HistoryAdapter;
import vn.fpoly.fpolybookcarclient.Model.ObjectClass.HistoryBookCar;
import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.View.Activity.HomeActivity;

public class ActivityFragment extends Fragment {
    RecyclerView recyclerView;
    HistoryAdapter historyAdapter;
    ArrayList<HistoryBookCar> arrHistoryBook = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        recyclerView = view.findViewById(R.id.reycelviewItemHistory);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        dataItem();
        historyAdapter = new HistoryAdapter(arrHistoryBook,getActivity(),R.layout.row_historybookcar);
        recyclerView.setAdapter(historyAdapter);
        return view;
    }
    private void dataItem(){
        arrHistoryBook.add(new HistoryBookCar("Chuyến đi đến 593 Hoàng Xa","18 thg 7","Car",R.drawable.iconcar));
        arrHistoryBook.add(new HistoryBookCar("Cơm chay thích thì vô","20 thg 10","Food",R.drawable.iconfood));
        arrHistoryBook.add(new HistoryBookCar("Chuyến đi đến NewYord","30 thg 12","MotoBike",R.drawable.icon_motorbike));
        arrHistoryBook.add(new HistoryBookCar("Chuyến đi đến 593 Hoàng Xa","18 thg 7","Car",R.drawable.iconcar));
        arrHistoryBook.add(new HistoryBookCar("Cơm chay thích thì vô","20 thg 10","Food",R.drawable.iconfood));
        arrHistoryBook.add(new HistoryBookCar("Chuyến đi đến NewYord","30 thg 12","MotoBike",R.drawable.icon_motorbike));
        arrHistoryBook.add(new HistoryBookCar("Chuyến đi đến 593 Hoàng Xa","18 thg 7","Car",R.drawable.iconcar));
        arrHistoryBook.add(new HistoryBookCar("Cơm chay thích thì vô","20 thg 10","Food",R.drawable.iconfood));
        arrHistoryBook.add(new HistoryBookCar("Chuyến đi đến NewYord","30 thg 12","MotoBike",R.drawable.icon_motorbike));
        arrHistoryBook.add(new HistoryBookCar("Chuyến đi đến 593 Hoàng Xa","18 thg 7","Car",R.drawable.iconcar));
        arrHistoryBook.add(new HistoryBookCar("Cơm chay thích thì vô","20 thg 10","Food",R.drawable.iconfood));
        arrHistoryBook.add(new HistoryBookCar("Chuyến đi đến NewYord","30 thg 12","MotoBike",R.drawable.icon_motorbike));
    }
}
