package vn.fpoly.fpolybookcarclient.adapter.food;

import android.content.Context;
import android.net.Uri;
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
import vn.fpoly.fpolybookcarclient.model.objectClass.BreakFast_MenuFood;
import vn.fpoly.fpolybookcarclient.model.objectClass.FoodMenu;

public class BreakFast_MenuFoodAdapter extends RecyclerView.Adapter<BreakFast_MenuFoodAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private List<FoodMenu> arrFoodMenu;


    public BreakFast_MenuFoodAdapter(Context context, int layout, List<FoodMenu> arrFoodMenu) {
        this.context = context;
        this.layout = layout;
        this.arrFoodMenu = arrFoodMenu;
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
        FoodMenu foodMenu = arrFoodMenu.get(position);
        holder.txtTitle.setText(foodMenu.getName());
        Glide.with(context).load(foodMenu.getImage()).into(holder.roundedImageView);

    }

    @Override
    public int getItemCount() {
        return arrFoodMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView roundedImageView;
        TextView txtTitle,txtDistance;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView    = itemView.findViewById(R.id.imgFood4);
            txtDistance         = itemView.findViewById(R.id.txtDistanceFood2);
            txtTitle            = itemView.findViewById(R.id.txtFood4);
        }
    }
}
