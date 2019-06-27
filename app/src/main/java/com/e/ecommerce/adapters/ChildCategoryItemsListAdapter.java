package com.e.ecommerce.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.e.ecommerce.R;
import com.e.ecommerce.app.AppController;
import com.e.ecommerce.model.ProductCategoryModel;

import java.util.ArrayList;


public class ChildCategoryItemsListAdapter extends RecyclerView.Adapter<ChildCategoryItemsListAdapter.MyViewHolder> {

    private final static String TAG = ChildCategoryItemsListAdapter.class.getSimpleName();
    private ArrayList<ProductCategoryModel> productCategoryItemModels;
    private Activity activity;
    private int parentId;

    public ChildCategoryItemsListAdapter(ArrayList<ProductCategoryModel> productCategoryItemModels, Activity activity, int parentId) {
        this.productCategoryItemModels = productCategoryItemModels;
        this.activity = activity;
        this.parentId = parentId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final ProductCategoryModel myLocationsModel = productCategoryItemModels.get(position);

        holder.idChildName.setText(myLocationsModel.getName());
        holder.idChildName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
     /*           Intent intent = new Intent(AppController.getInstance().getApplicationContext(), ProductListActivity.class);
//                intent.putExtra("productid", myLocationsModel.getId());
//                intent.putExtra("productName",myLocationsModel.getName());
                intent.putExtra(PreferManager.PRODUCT_ID,myLocationsModel.getId());
                intent.putExtra(PreferManager.PRODUCT_NAME,myLocationsModel.getName());
                activity.startActivity(intent);
                if(MainActivity.dialogHomeCategory!=null){
                    MainActivity.dialogHomeCategory.dismiss();
                }*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return productCategoryItemModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView idChildName;

        public MyViewHolder(View view) {
            super(view);
            idChildName = view.findViewById(R.id.idChildName);
        }
    }
}
