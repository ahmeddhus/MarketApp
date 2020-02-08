package com.example.marketapp.ui.main.intro;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.marketapp.R;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.ic_add_shopping_cart_150dp,
            R.drawable.ic_local_shipping_150dp,
            R.drawable.ic_store_mall_directory_150dp, R.drawable.ic_store_mall_directory_70dp};
    private Integer[] texts = {R.string.pick_yours,
            R.string.deliver, R.string.start, R.string.empty};

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.introslider_adapter, null);
        ImageView imageView = view.findViewById(R.id.introslider_img);
        TextView textView = view.findViewById(R.id.introslider_txt);

        Glide.with(context)
                .load(images[position])
                .into(imageView);
        textView.setText(texts[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}