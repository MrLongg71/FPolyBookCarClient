package vn.fpoly.fpolybookcarclient.view.email;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

import vn.fpoly.fpolybookcarclient.adapter.email.EmailAdapter;
import vn.fpoly.fpolybookcarclient.library.CallBackFragment;
import vn.fpoly.fpolybookcarclient.model.objectClass.Email;
import vn.fpoly.fpolybookcarclient.R;

public class MailFragment extends Fragment {
    SwipeMenuListView swipeMenuListView;
    EmailAdapter emailAdapter;
    ArrayList<Email> arrEmail = new ArrayList<>();
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mail, container, false);
        swipeMenuListView = view.findViewById(R.id.swipmenu);
        fragmentManager = getActivity().getSupportFragmentManager();
        CallBackFragment.CallbackHome(view,fragmentManager);
        SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem detail = new SwipeMenuItem(getActivity());
                detail.setWidth(400);
                detail.setTitle("Delete");

                detail.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                detail.setIcon(R.drawable.icon_grabage);
                detail.setTitleColor(Color.BLUE);
                menu.addMenuItem(detail);
            }
        };
        swipeMenuListView.setMenuCreator(swipeMenuCreator);
        swipeMenuListView.setCloseInterpolator(new BounceInterpolator());
        data();
        emailAdapter = new EmailAdapter(arrEmail,getActivity(),R.layout.row_email);
        swipeMenuListView.setAdapter(emailAdapter);

        return view;
    }

    private void data() {
        arrEmail.add(new Email("Nhận ngay gói ưu đãi đi Mỹ miễn phí 100% cho 1 người", "Cùng đi nào mọi người ơi", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Tặng ưu đã 100k khi chi sẽ 1 người", "Cùng nhau chia sẽ nào!!", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Tặng ưu đã 100k khi chi sẽ 1 người", "Cùng nhau chia sẽ nào!!", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Nhận ngay gói ưu đãi đi Mỹ miễn phí 100% cho 1 người", "Cùng đi nào mọi người ơi", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Tặng ưu đã 100k khi chi sẽ 1 người", "Cùng nhau chia sẽ nào!!", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Tặng ưu đã 100k khi chi sẽ 1 người", "Cùng nhau chia sẽ nào!!", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Nhận ngay gói ưu đãi đi Mỹ miễn phí 100% cho 1 người", "Cùng đi nào mọi người ơi", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Tặng ưu đã 100k khi chi sẽ 1 người", "Cùng nhau chia sẽ nào!!", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Tặng ưu đã 100k khi chi sẽ 1 người", "Cùng nhau chia sẽ nào!!", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Nhận ngay gói ưu đãi đi Mỹ miễn phí 100% cho 1 người", "Cùng đi nào mọi người ơi", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Tặng ưu đã 100k khi chi sẽ 1 người", "Cùng nhau chia sẽ nào!!", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Tặng ưu đã 100k khi chi sẽ 1 người", "Cùng nhau chia sẽ nào!!", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Nhận ngay gói ưu đãi đi Mỹ miễn phí 100% cho 1 người", "Cùng đi nào mọi người ơi", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Tặng ưu đã 100k khi chi sẽ 1 người", "Cùng nhau chia sẽ nào!!", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Tặng ưu đã 100k khi chi sẽ 1 người", "Cùng nhau chia sẽ nào!!", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Nhận ngay gói ưu đãi đi Mỹ miễn phí 100% cho 1 người", "Cùng đi nào mọi người ơi", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Tặng ưu đã 100k khi chi sẽ 1 người", "Cùng nhau chia sẽ nào!!", R.drawable.custom_emainoti_check));
        arrEmail.add(new Email("Tặng ưu đã 100k khi chi sẽ 1 người", "Cùng nhau chia sẽ nào!!", R.drawable.custom_emainoti_check));

    }
}
