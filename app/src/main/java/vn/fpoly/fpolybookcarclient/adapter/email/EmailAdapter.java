package vn.fpoly.fpolybookcarclient.adapter.email;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.fpoly.fpolybookcarclient.model.objectClass.Email;
import vn.fpoly.fpolybookcarclient.R;

public class EmailAdapter extends BaseAdapter {
    private List<Email>arrEmail;
    private Context context;
    private int layout;

    public EmailAdapter(List<Email> arrEmail, Context context, int layout) {
        this.arrEmail = arrEmail;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrEmail.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class ViewHolder{
        ImageView imgEmail;
        TextView txtTitle,txtDetail;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(layout, null, false);
            viewHolder.imgEmail = view.findViewById(R.id.imgrowItemEmail);
            viewHolder.txtTitle = view.findViewById(R.id.txtTitleEmailItem);
            viewHolder.txtDetail = view.findViewById(R.id.txtDetailEmail);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Email email = arrEmail.get(i);
        viewHolder.imgEmail.setImageResource(R.drawable.custom_emainoti_check);
        viewHolder.txtDetail.setText(email.getDetail());
        viewHolder.txtTitle.setText(email.getTitle());

        return view;
    }
}
