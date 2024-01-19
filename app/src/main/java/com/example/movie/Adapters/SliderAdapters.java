package com.example.movie.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.movie.Domains.SliderItem;
import com.example.movie.R;

import java.util.List;

public class SliderAdapters extends RecyclerView.Adapter<SliderAdapters.SliderViewHolder> {

    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;
    private Context context;

    public SliderAdapters(List<SliderItem> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderAdapters.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.slide_item_containers, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapters.SliderViewHolder holder, int position) {
        holder.setImage(sliderItems.get(position));
        if (position == sliderItems.size() - 2)
        {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        public SliderViewHolder(@NonNull View itemview)
        {
            super(itemview);
            imageView = itemview.findViewById(R.id.imageSlide);
        }

        void setImage(SliderItem sliderItem)
        {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(60));

            Glide.with(context)
                    .load(sliderItem.getImage())
                    .apply(requestOptions)
                    .into(imageView);
        }

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
}
