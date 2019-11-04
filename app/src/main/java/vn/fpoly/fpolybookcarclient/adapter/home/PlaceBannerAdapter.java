package vn.fpoly.fpolybookcarclient.adapter.home;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.PlaceBanner;
import vn.fpoly.fpolybookcarclient.R;

public class PlaceBannerAdapter extends RecyclerView.Adapter<PlaceBannerAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private List<PlaceBanner> arrPlace;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();


    public PlaceBannerAdapter(Context context, int layout, List<PlaceBanner> arrPlace) {
        this.context = context;
        this.layout = layout;
        this.arrPlace = arrPlace;
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
        PlaceBanner placeBanner = arrPlace.get(position);
        holder.txtDetail.setText(placeBanner.getDetail());
        holder.txtTitle.setText(placeBanner.getTitle());
        setImagePlace(holder.imgBanner,placeBanner);
    }

    private void setImagePlace(final ImageView imgBanner, PlaceBanner placeBanner){
        storageReference.child("Imagenewsplace").child(placeBanner.getArrImage().get(0)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String URL = uri.toString();
                Uri linkHinh = uri.parse(URL);
                Glide.with(context).load(linkHinh).into(imgBanner);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrPlace.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView imgBanner;
        ToggleButton toggleButtonLike;
        TextView txtTitle,txtDetail,txtbookride;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBanner            = itemView.findViewById(R.id.imgItemPlace);
            toggleButtonLike     = itemView.findViewById(R.id.toglelikePlace);
            txtTitle             = itemView.findViewById(R.id.txttitlePlaceItem);
            txtDetail            = itemView.findViewById(R.id.txtDetaiPlaceItem);
            txtbookride          = itemView.findViewById(R.id.txtBookNowFood);
        }
    }
}
