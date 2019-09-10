package vn.fpoly.fpolybookcarclient.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.fpoly.fpolybookcarclient.Model.ObjectClass.ChallengeBanner;
import vn.fpoly.fpolybookcarclient.R;

public class ChallengeBannerAdapter extends RecyclerView.Adapter<ChallengeBannerAdapter.ViewHolder>{
    private List<ChallengeBanner> arrBanner;
    private Context context;
    private int layout;

    public ChallengeBannerAdapter(List<ChallengeBanner> arrBanner, Context context, int layout) {
        this.arrBanner = arrBanner;
        this.context = context;
        this.layout = layout;
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
        ChallengeBanner banner = arrBanner.get(position);
        holder.imgBanner.setImageResource(banner.getImage());
    }

    @Override
    public int getItemCount() {
        return arrBanner.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBanner;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBanner = itemView.findViewById(R.id.imgRowBanner);
        }
    }
}
