package com.e.ecommerce.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.e.ecommerce.R;
import com.e.ecommerce.model.ProductModel;
import java.util.ArrayList;

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private ArrayList<ProductModel> productModelArrayList;

    private Context context;

    private boolean isLoadingAdded = false;

    public PaginationAdapter(Context context,ArrayList<ProductModel> productModelArrayList) {
        this.context = context;
        this.productModelArrayList=productModelArrayList;
    }

    public ArrayList<ProductModel> getMovies() {
        return productModelArrayList;
    }

    public void setMovies(ArrayList<ProductModel> movies) {
        this.productModelArrayList = movies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.home_header_products_list_item, parent, false);
        viewHolder = new MyViewHolder(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ProductModel productModel = productModelArrayList.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                MyViewHolder productVH = (MyViewHolder) holder;

                productVH.productName.setText(productModel.getName());
                try {
                    Glide.with(context)
                            .load(productModel.getImagesModelArrayList().get(0).getSrc()).placeholder(R.drawable.ic_image_place_holder)
                            .skipMemoryCache(true)
                            .error(R.drawable.ic_image_place_holder)
                            .into(productVH.productImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case LOADING:
//                Do nothing
                break;
        }

    }

    @Override
    public int getItemCount() {
        return productModelArrayList == null ? 0 : productModelArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == productModelArrayList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(ProductModel mc) {
        productModelArrayList.add(mc);
        notifyItemInserted(productModelArrayList.size() - 1);
    }

    public void addAll(ArrayList<ProductModel> mcList) {
        for (ProductModel mc : mcList) {
            add(mc);
        }
    }

    public void remove(ProductModel city) {
        int position = productModelArrayList.indexOf(city);
        if (position > -1) {
            productModelArrayList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new ProductModel());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = productModelArrayList.size() - 1;
        ProductModel item = getItem(position);

        if (item != null) {
            productModelArrayList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public ProductModel getItem(int position) {
        return productModelArrayList.get(position);
    }


   /*
   View Holders
   _________________________________________________________________________________________________
    */

    /**
     * Main list's content ViewHolder
     */
    protected class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView productName;
        private ImageView productImage;

        public MyViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.imageIcon);
            productName = view.findViewById(R.id.idTitle);
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }

}
