package com.e.ecommerce.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.e.ecommerce.R;
import com.e.ecommerce.model.ProductModel;

import java.util.ArrayList;

public class HomeFooterTopProuctsListAdapter extends RecyclerView.Adapter<HomeFooterTopProuctsListAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<ProductModel> productDetailsModelList;


    public HomeFooterTopProuctsListAdapter(Context context, ArrayList<ProductModel> productDetailsModelList) {
        this.productDetailsModelList = productDetailsModelList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public HomeFooterTopProuctsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frag_main, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HomeFooterTopProuctsListAdapter.MyViewHolder holder, int position) {
        ProductModel productModel = productDetailsModelList.get(position);
        //setHasStableIds(false);
        //holder.productName.setText(productModel.getName());
        try {
            Glide.with(mContext)
                    .load(productModel.getImagesModelArrayList().get(0).getSrc()).placeholder(R.drawable.ic_image_place_holder)
                    .centerCrop()
                    .error(R.drawable.ic_image_place_holder)
                    .into(holder.productImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return productDetailsModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

       // private TextView productName;
        private ImageView productImage;

        public MyViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.image);
            //productName = view.findViewById(R.id.idTitle);
        }

    }
}
