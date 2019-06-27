package com.e.ecommerce.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.e.ecommerce.R;
import com.e.ecommerce.app.AppController;
import com.e.ecommerce.helper.PreferManager;
import com.e.ecommerce.model.ProductCategoryParentModel;

import java.util.ArrayList;

public class HomeProductCategoryListParentAdapater extends RecyclerView.Adapter<HomeProductCategoryListParentAdapater.MyViewHolder> {
    private ArrayList<ProductCategoryParentModel> productCategoryParentModels;
    private Activity activity;
    private final static String TAG = HomeProductCategoryListParentAdapater.class.getSimpleName();

    private boolean isFromMain;


    public void setIsFromMain(boolean b) {
        this.isFromMain = b;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lblCategoryTitle;
        public RelativeLayout idHeader;
        public RecyclerView recyclerView;
        public ImageView imgOutofStock;
        public ProgressBar progressBar;
        public ToggleButton idToggleExpand;

        public MyViewHolder(View view) {
            super(view);
            lblCategoryTitle = (TextView) view.findViewById(R.id.idTextCategoryTitle);
            idHeader=view.findViewById(R.id.idHeader);
            imgOutofStock = view.findViewById(R.id.idImagOutofStock);
            recyclerView = view.findViewById(R.id.recyclerView_productcategoryitems);
            progressBar = view.findViewById(R.id.progressBar);
            idToggleExpand = view.findViewById(R.id.idToggleExpand);

        }
    }


    public HomeProductCategoryListParentAdapater(ArrayList<ProductCategoryParentModel> productCategoryParentModels, Activity activity) {
        this.productCategoryParentModels = productCategoryParentModels;
        this.activity = activity;
    }
    public HomeProductCategoryListParentAdapater(ArrayList<ProductCategoryParentModel> productCategoryParentModels) {
        this.productCategoryParentModels = productCategoryParentModels;

    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_category_listiems, parent, false);

        return new MyViewHolder(itemView);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        ProductCategoryParentModel productCategoryParentModel = productCategoryParentModels.get(position);
        System.out.println("HomeProductCategoryListParentAdapater.onBindViewHolder size:"+productCategoryParentModel);
        holder.lblCategoryTitle.setText(productCategoryParentModel.getTitle());
        holder.idHeader.setVisibility(View.VISIBLE);


        /**
         * base on the isfromMain we handle the adapter view
         *  if is from main screen we are going to hide toggle button and disply title and sub category recyvler view
         *  if is from category screen we are diaplsy toggle button and hide image recyclerview and dialpy text recyclerview
         */
        if (!isFromMain) {
            // holder.idToggleExpand.setVisibility(View.GONE);
            CategoryItemsListAdapter categoryItemsListAdapter = new CategoryItemsListAdapter(productCategoryParentModel.getProductCategoryItemModels(), activity, productCategoryParentModel.getParentId());
            LinearLayoutManager layoutManager = new LinearLayoutManager(AppController.getInstance().getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.recyclerView.setLayoutManager(layoutManager);
            holder.recyclerView.setAdapter(categoryItemsListAdapter);
            if (productCategoryParentModel.isDataFetched()) {
                if (productCategoryParentModel.getProductCategoryItemModels().size() > 0) {
                    holder.recyclerView.setVisibility(View.VISIBLE);
                    holder.imgOutofStock.setVisibility(View.GONE);
                } else {
                    holder.imgOutofStock.setVisibility(View.VISIBLE);
                    holder.recyclerView.setVisibility(View.GONE);
                }
                holder.progressBar.setVisibility(View.GONE);
            } else {
                holder.progressBar.setVisibility(View.VISIBLE);
            }
        } else {
            if (productCategoryParentModel.getProductCategoryItemModels().size() > 0) {
                holder.idToggleExpand.setVisibility(View.VISIBLE);
            } else {
                holder.idToggleExpand.setVisibility(View.GONE);
            }

            ChildCategoryItemsListAdapter categoryItemsListAdapter = new ChildCategoryItemsListAdapter(productCategoryParentModel.getProductCategoryItemModels(), activity, productCategoryParentModel.getParentId());
            LinearLayoutManager layoutManager = new LinearLayoutManager(AppController.getInstance().getApplicationContext());
            holder.recyclerView.setLayoutManager(layoutManager);
            holder.recyclerView.setAdapter(categoryItemsListAdapter);

            holder.idToggleExpand.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        holder.recyclerView.setVisibility(View.VISIBLE);
                    } else {
                        holder.recyclerView.setVisibility(View.GONE);

                    }
                }
            });

            holder.lblCategoryTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
               //     Intent intent = new Intent(AppController.getInstance().getApplicationContext(), ProductListActivity.class);
                 //   intent.putExtra(PreferManager.PRODUCT_ID, productCategoryParentModel.getParentId());
                   // intent.putExtra(PreferManager.PRODUCT_NAME,productCategoryParentModel.getTitle());
                    //activity.startActivity(intent);
                   /* if(MainActivity.dialogHomeCategory!=null){
                        MainActivity.dialogHomeCategory.dismiss();
                    }*/
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return productCategoryParentModels.size();
    }
}
