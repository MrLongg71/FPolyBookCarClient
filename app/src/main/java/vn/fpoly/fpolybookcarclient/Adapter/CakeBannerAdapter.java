package vn.fpoly.fpolybookcarclient.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.fpoly.fpolybookcarclient.Model.ObjectClass.CakesBanner;
import vn.fpoly.fpolybookcarclient.R;

public class CakeBannerAdapter extends RecyclerView.Adapter<CakeBannerAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private List<CakesBanner> arrCake;

    public CakeBannerAdapter(Context context, int layout, List<CakesBanner> arrCake) {
        this.context = context;
        this.layout = layout;
        this.arrCake = arrCake;
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
        CakesBanner cakesBanner = arrCake.get(position);
        holder.imgBanner.setImageResource(cakesBanner.getImage());
        holder.txtTitle.setText(cakesBanner.getTitle());
        holder.txtDetail.setText(cakesBanner.getDetail());
    }

    @Override
    public int getItemCount() {
        return arrCake.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBanner;
        ToggleButton toggleLike;
        TextView txtTitle,txtDetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBanner   = itemView.findViewById(R.id.imgItemCake);
            txtDetail   = itemView.findViewById(R.id.txtDetaiCakeItem);
            txtTitle    = itemView.findViewById(R.id.txttitleCakeItem);
            toggleLike  = itemView.findViewById(R.id.toglelikeCake);
        }
    }
}
