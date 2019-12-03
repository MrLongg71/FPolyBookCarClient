package vn.fpoly.fpolybookcarclient.adapter.food;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.model.objectClass.Restaurant;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private List<Restaurant> arrRestaurant;
    private Context context;
    private int layout;

    public RestaurantAdapter(List<Restaurant> arrRestaurant, Context context, int layout) {
        this.arrRestaurant = arrRestaurant;
        this.context = context;
        this.layout = layout;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Restaurant restaurant = arrRestaurant.get(position);
        holder.txtTitle.setText(restaurant.getName());
        holder.txtDetail.setText(restaurant.getDetail());
        Log.d("aaaaaa",restaurant.getImage());
        Glide.with(context).load(restaurant.getImage()).into(holder.roundedImageView);

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView roundedImageView;
        TextView txtDistance,txtTitle,txtDetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView    = itemView.findViewById(R.id.imgFood1);
            txtDistance         = itemView.findViewById(R.id.txtDistanceFood1);
            txtDetail           = itemView.findViewById(R.id.txtDetailFood1);
            txtTitle            = itemView.findViewById(R.id.txtTitleFood1);
        }
    }
}
