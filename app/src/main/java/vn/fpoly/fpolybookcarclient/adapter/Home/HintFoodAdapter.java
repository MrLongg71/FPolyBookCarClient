package vn.fpoly.fpolybookcarclient.adapter.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.HintFood;
import vn.fpoly.fpolybookcarclient.R;

public class HintFoodAdapter extends RecyclerView.Adapter<HintFoodAdapter.ViewHolder> {

    private Context context;
    private int layout;
    private List<HintFood> arrHintFood;

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
        holder.imgFood.setImageResource(hintFood.getImage());
        holder.txtDetail.setText(hintFood.getDetail());
        holder.txttitle.setText(hintFood.getTitle());
        holder.txtRate.setText(hintFood.getRate());
        holder.txtMinute.setText(hintFood.getMinute());
    }

    @Override
    public int getItemCount() {
        return arrHintFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood,imgRate,imgMinute;
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
