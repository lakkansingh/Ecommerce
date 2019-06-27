package com.e.ecommerce.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.e.ecommerce.R;
import com.e.ecommerce.app.AppController;
import com.e.ecommerce.helper.PreferManager;
import com.e.ecommerce.model.ProductCategoryModel;

import java.util.ArrayList;

class CategoryItemsListAdapter extends RecyclerView.Adapter<CategoryItemsListAdapter.MyViewHolder> {

    private final static String TAG = CategoryItemsListAdapter.class.getSimpleName();
    private ArrayList<ProductCategoryModel> productCategoryItemModels;
    private Activity activity;
    private int parentId;

    public CategoryItemsListAdapter(ArrayList<ProductCategoryModel> productCategoryItemModels, Activity activity, int parentId) {
        this.productCategoryItemModels = productCategoryItemModels;
        this.activity = activity;
        this.parentId = parentId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_category_rowitems, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final ProductCategoryModel myLocationsModel = productCategoryItemModels.get(position);
        holder.lblProductTitle.setText(myLocationsModel.getName());
        try {
            if (myLocationsModel.getImagesModel().getSrc() != null) {
                Glide.with(activity)
                        .load(myLocationsModel.getImagesModel().getSrc()).placeholder(R.drawable.ic_image_place_holder)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.ic_image_place_holder)
                        .into(holder.imgProductThumb);
            } else {
                Toast.makeText(activity, "Image model is null", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.imgProductThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(AppController.getInstance().getApplicationContext(), ProductListActivity.class);
//                intent.putExtra("productid", myLocationsModel.getId());
//                intent.putExtra("parentId",parentId);
               // intent.putExtra(PreferManager.PRODUCT_ID, myLocationsModel.getId());
               // intent.putExtra(PreferManager.PARENT_ID, parentId);
                //activity.startActivity(intent);

            }
        });
        holder.lblProductTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(AppController.getInstance().getApplicationContext(), ProductListActivity.class);
                //intent.putExtra(PreferManager.PRODUCT_ID, myLocationsModel.getId());
                //activity.startActivity(intent);

            }
        });
        /*if (myLocationsModel.getImagesModel() != null) {
            Glide.with(AppController.getInstance().getApplicationContext())
                    .load(myLocationsModel.getImagesModel().getSrc())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop().placeholder(R.drawable.ic_image_place_holder)
                    .into(holder.imgProductThumb);
        } else {
            Glide.with(AppController.getInstance().getApplicationContext())
                    .load(R.drawable.ic_image_place_holder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(holder.imgProductThumb);
        }
*/
    }

    @Override
    public int getItemCount() {
        return productCategoryItemModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lblProductTitle;
        public ImageView imgProductThumb;

        public MyViewHolder(View view) {
            super(view);
            lblProductTitle = (TextView) view.findViewById(R.id.idproductImagetext);
            imgProductThumb = view.findViewById(R.id.idProductImage);
        }
    }
}
