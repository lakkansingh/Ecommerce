package com.e.ecommerce.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.e.ecommerce.R;
import com.e.ecommerce.adapters.HomeProductCategoryListParentAdapater;
import com.e.ecommerce.app.AppConfig;

public class CategoriesFragment extends Fragment {
    private RecyclerView recyclerView;
    private HomeProductCategoryListParentAdapater homeProductCategoryListParentAdapater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.activity_category_list,container,false);
       /* if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Category");
        }
 */       recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_productcategorylist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeProductCategoryListParentAdapater = new HomeProductCategoryListParentAdapater(AppConfig.productCategoryParentModels, getActivity());
        homeProductCategoryListParentAdapater.setIsFromMain(true);
        recyclerView.setAdapter(homeProductCategoryListParentAdapater);

        return view;
    }
}
