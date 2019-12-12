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
import vn.fpoly.fpolybookcarclient.model.objectClass.OrderFood;

public class HistoryFoodAdapter extends RecyclerView.Adapter<HistoryFoodAdapter.ViewHolder> {
    private Context context;
    private int layout;
    private List<OrderFood>arrHistory;

    public HistoryFoodAdapter(Context context, int layout, List<OrderFood> arrHistory) {
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
        OrderFood orderFood = arrHistory.get(position);
        holder.txtTimeOrder.setText(orderFood.getDate());
        holder.txtPlaceComeOrder.setText(orderFood.getPlaceNameRes());
        holder.txtPriceOrder.setText(orderFood.getPrice()+"");

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
