package vn.fpoly.fpolybookcarclient.adapter.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.fpoly.fpolybookcarclient.R;
import vn.fpoly.fpolybookcarclient.model.objectClass.OderCar;

public class HistoryCarAdapter extends RecyclerView.Adapter<HistoryCarAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private List<OderCar>arrHistory;

    public HistoryCarAdapter(Context context, int layout, List<OderCar> arrHistory) {
        this.context = context;
        this.layout = layout;
        this.arrHistory = arrHistory;
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
        OderCar oderCar = arrHistory.get(position);
        holder.txtTimeOrder.setText(oderCar.getDate());
        holder.txtPlaceComeOrder.setText(oderCar.getPlacenamecome());
        holder.txtPriceOrder.setText(oderCar.getPrice()+"");

    }

    @Override
    public int getItemCount() {
        return arrHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtTimeOrder,txtPlaceComeOrder,txtPriceOrder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            imageView       = itemView.findViewById(R.id.imgIconCarHistory);
            txtTimeOrder         = itemView.findViewById(R.id.txtTimeOrder);
            txtPlaceComeOrder       = itemView.findViewById(R.id.txtPlaceComeOrder);
            txtPriceOrder       = itemView.findViewById(R.id.txtPriceOrder);
        }
    }
}
