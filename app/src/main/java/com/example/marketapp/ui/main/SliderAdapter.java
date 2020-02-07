package com.example.marketapp.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.marketapp.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        viewHolder.bind(position);
    }

    @Override
    public int getCount() {
        return 4;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageViewBackground;

        SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView = itemView;
        }

        void bind(int position) {
            switch (position) {
                case 0:
                    Glide.with(itemView)
                            .load(R.drawable.imageslider1)
                            .into(imageViewBackground);
                    break;
                case 1:
                    Glide.with(itemView)
                            .load(R.drawable.imageslider2)
                            .into(imageViewBackground);
                    break;
                case 2:
                    Glide.with(itemView)
                            .load(R.drawable.imageslider3)
                            .into(imageViewBackground);
                    break;
                default:
                    Glide.with(itemView)
                            .load(R.drawable.imageslider4)
                            .into(imageViewBackground);
                    break;
            }
        }
    }
}