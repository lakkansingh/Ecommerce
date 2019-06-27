package com.e.ecommerce.adapters.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.e.ecommerce.R;
import com.e.ecommerce.model.ProductModel;


import java.util.ArrayList;

public class HomeHeaderProuctsListAdapter extends RecyclerView.Adapter<HomeHeaderProuctsListAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<ProductModel> fetchHeaderItemsModels;
    private boolean isFromOffers;


    public HomeHeaderProuctsListAdapter(Context context, ArrayList<ProductModel> fetchHeaderItemsModels, boolean isFromOffers) {
        this.fetchHeaderItemsModels = fetchHeaderItemsModels;
        this.mContext = context;
        this.isFromOffers = isFromOffers;
    }

    @NonNull
    @Override
    public HomeHeaderProuctsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = null;
        if (!isFromOffers) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.home_header_products_list_item, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.frag_main, parent, false);
        }
        return new MyViewHolder(itemView);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HomeHeaderProuctsListAdapter.MyViewHolder holder, int position) {
        ProductModel fetchHeaderItemsModel = fetchHeaderItemsModels.get(position);
        holder.productName.setText(fetchHeaderItemsModel.getName());
        try {
            Glide.with(mContext)
                    .load(fetchHeaderItemsModel.getImagesModelArrayList().get(0).getSrc()).placeholder(R.drawable.ic_image_place_holder)
                    .skipMemoryCache(true)
                    .error(R.drawable.ic_image_place_holder)
                    .into(holder.productImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return fetchHeaderItemsModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView productName;
        private ImageView productImage;

        public MyViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.imageIcon);
            productName = view.findViewById(R.id.idTitle);
        }

    }
}
