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
import vn.fpoly.fpolybookcarclient.model.objectClass.BreakFast;

public class BreakFastAdapter extends RecyclerView.Adapter<BreakFastAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private List<BreakFast> arrBreakFast;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    public BreakFastAdapter(Context context, int layout, List<BreakFast> arrBreakFast) {
        this.context = context;
        this.layout = layout;
        this.arrBreakFast = arrBreakFast;
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
        BreakFast breakFast = arrBreakFast.get(position);
        holder.txtTitle.setText(breakFast.getTitle());
        holder.txtDistance.setText(breakFast.getDistance());
        storageReference.child("Imagenewbreakfast").child(breakFast.getArrImage().get(0)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String URL = uri.toString();
                Glide.with(context).load(URL).into(holder.roundedImageView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrBreakFast.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView roundedImageView;
        TextView txtTitle,txtDistance;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView    = itemView.findViewById(R.id.imgFood2);
            txtDistance         = itemView.findViewById(R.id.txtDistanceFood2);
            txtTitle            = itemView.findViewById(R.id.txtTitleFood2);
        }
    }
}
