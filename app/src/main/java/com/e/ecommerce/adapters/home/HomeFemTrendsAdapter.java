package com.e.ecommerce.adapters.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.e.ecommerce.R;
import com.e.ecommerce.model.ProductModel;
import java.io.Serializable;
import java.util.ArrayList;

public class HomeFemTrendsAdapter extends PagedListAdapter<ProductModel, HomeFemTrendsAdapter.MyViewHolder> {

    ProductModel productDetailsModel;
    private Context mContext;
    private ArrayList<ProductModel> productDetailsModelArrayList;
    private Activity activity;
    private static final StrikethroughSpan STRIKE_THROUGH_SPAN = new StrikethroughSpan();
    private String productName;
    private int productSetId;


    public HomeFemTrendsAdapter(Context context, ArrayList<ProductModel> productDetailsModelArrayList, Activity activity, int productSetId, String productName) {
        super(DIFF_CALLBACK);
        mContext = context;
        this.productDetailsModelArrayList = productDetailsModelArrayList;
        this.activity = activity;
        this.productName = productName;
        this.productSetId = productSetId;
    }


    public void refresh(ArrayList<ProductModel> productDetailsModelArrayList) {
        this.productDetailsModelArrayList = productDetailsModelArrayList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HomeFemTrendsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frag_main, parent, false);

        return new HomeFemTrendsAdapter.MyViewHolder(itemView);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HomeFemTrendsAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.setIsRecyclable(false);
        ProductModel productDetailsModel = productDetailsModelArrayList.get(i);
        productDetailsModel = productDetailsModelArrayList.get(i);

        try {
            if (productDetailsModel.getImagesModelArrayList() != null && productDetailsModel.getImagesModelArrayList().size() > 0) {
                Glide.with(mContext)
                        .load(productDetailsModel.getImagesModelArrayList().get(0).getSrc())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.trends)
                        .into(myViewHolder.imgIcon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (i == 0) {
            myViewHolder.imgIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* Intent intent = new Intent(AppController.getInstance().getApplicationContext(), ProductListActivity.class);
                    intent.putExtra(PreferManager.PRODUCT_NAME, productName);
                    intent.putExtra(PreferManager.PRODUCT_ID, productSetId);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);*/
                }
            });
        } else if (i > 0) {
            myViewHolder.imgIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(mContext,"working",Toast.LENGTH_LONG).show();
                    if (productDetailsModelArrayList.size() > 0) {
                 /*       ProductModel productModel = productDetailsModelArrayList.get(i);

                        int parentID = productModel.getParent_id();
                        int productID = productModel.getId();
                        Intent intent = new Intent(AppController.getInstance().getApplicationContext(), ProductDetailedViewActivity.class);
                        intent.putExtra("imagesArray", (Serializable) productModel);
                        intent.putExtra("parentId", parentID);
                        intent.putExtra("productId", productID);
                        intent.putExtra("isFromMainActivity", true);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);*/
                    }

                }
            });

        }

    }


    @Override
    public int getItemCount() {
        return productDetailsModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgIcon;

        public MyViewHolder(View view) {
            super(view);
            imgIcon = (ImageView) view.findViewById(R.id.image);

        }

    }

    private static DiffUtil.ItemCallback<ProductModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ProductModel>() {
                @Override
                public boolean areItemsTheSame(ProductModel oldItem, ProductModel newItem) {
                    return oldItem.getParent_id() == newItem.getParent_id();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(ProductModel oldItem, ProductModel newItem) {
                    return oldItem.equals(newItem);
                }
            };

}
