package vn.fpoly.fpolybookcarclient.adapter.home;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.HintFood;
import vn.fpoly.fpolybookcarclient.R;

public class HintFoodAdapter extends RecyclerView.Adapter<HintFoodAdapter.ViewHolder> {

    private Context context;
    private int layout;
    private List<HintFood> arrHintFood;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public HintFoodAdapter(Context context, int layout, List<HintFood> arrHintFood) {
        this.context = context;
        this.layout = layout;
        this.arrHintFood = arrHintFood;
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
        HintFood hintFood = arrHintFood.get(position);
        holder.txtDetail.setText(hintFood.getAddress());
        holder.txttitle.setText(hintFood.getTitle());
        holder.txtRate.setText(hintFood.getLike());
        holder.txtMinute.setText(hintFood.getTime());
        setImage(holder.imgFood,hintFood);
    }
    private void setImage(final ImageView image, HintFood hintFood){
        storageReference.child("Imagenewstodayeat").child(hintFood.getArrImage().get(0)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String URL = uri.toString();
                Uri uri1 = uri.parse(URL);
                Glide.with(context).load(uri1).into(image);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrHintFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRate,imgMinute;
        RoundedImageView imgFood;
        TextView txttitle,txtDetail,txtRate,txtMinute,txtBookNow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood     = itemView.findViewById(R.id.imgItemHintFood);
            imgRate     = itemView.findViewById(R.id.imgRateHintFood);
            imgMinute   = itemView.findViewById(R.id.imgMinuteHintFood);
            txttitle    = itemView.findViewById(R.id.txttitleHintFoodItem);
            txtDetail   = itemView.findViewById(R.id.txtDetailHintFood);
            txtRate     = itemView.findViewById(R.id.txtrateHintFood);
            txtMinute   = itemView.findViewById(R.id.txtMinuteHintFood);
            txtBookNow  = itemView.findViewById(R.id.txtBookNowHintFood);

        }
    }
}
