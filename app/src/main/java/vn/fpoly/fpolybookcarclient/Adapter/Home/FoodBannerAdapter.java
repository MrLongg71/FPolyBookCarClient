package vn.fpoly.fpolybookcarclient.Adapter.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.fpoly.fpolybookcarclient.Model.ObjectClass.FoodBanner;
import vn.fpoly.fpolybookcarclient.Model.ObjectClass.PlaceBanner;
import vn.fpoly.fpolybookcarclient.R;

public class FoodBannerAdapter extends RecyclerView.Adapter<FoodBannerAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private List<FoodBanner> arrFood;

    public FoodBannerAdapter(Context context, int layout, List<FoodBanner> arrFood) {
        this.context = context;
        this.layout = layout;
        this.arrFood = arrFood;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodBanner foodBanner = arrFood.get(position);
        holder.imgBanner.setImageResource(foodBanner.getImage());
        holder.txtDetail.setText(foodBanner.getDetail());
        holder.txtTitle.setText(foodBanner.getTitle());
    }

    @Override
    public int getItemCount() {
        return arrFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBanner;
        ToggleButton toggleButtonLike;
        TextView txtTitle, txtDetail, txtbooknow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBanner            = itemView.findViewById(R.id.imgItemFood);
            toggleButtonLike     = itemView.findViewById(R.id.toglelikeFood);
            txtTitle             = itemView.findViewById(R.id.txttitleFoodItem);
            txtDetail            = itemView.findViewById(R.id.txtDetaiFoodItem);
            txtbooknow          = itemView.findViewById(R.id.txtBookNowFood);
        }
    }
}
