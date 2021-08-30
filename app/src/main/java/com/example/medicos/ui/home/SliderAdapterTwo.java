package com.example.medicos.ui.home;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.medicos.R;
import com.example.medicos.SliderAdapter;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapterTwo extends SliderViewAdapter<SliderAdapterTwo.Holder>{

    int[] images;

    public SliderAdapterTwo(int[] images){

        this.images = images;

    }

    @Override
    public SliderAdapterTwo.Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapterTwo.Holder viewHolder, int position) {

        viewHolder.imageView.setImageResource(images[position]);

    }

    @Override
    public int getCount() {
        return images.length;
    }

    public static class Holder extends SliderViewAdapter.ViewHolder {

        ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);

        }
    }
}
