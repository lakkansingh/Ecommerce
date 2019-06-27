package com.e.ecommerce.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.e.ecommerce.R;
import com.e.ecommerce.adapters.HomeProductCategoryListParentAdapater;
import com.e.ecommerce.app.AppConfig;
import com.e.ecommerce.app.AppController;
import com.e.ecommerce.helper.ApiInterfaceServiceConnection;
import com.e.ecommerce.helper.OAuthInterceptor;
import com.e.ecommerce.helper.PreferManager;
import com.e.ecommerce.model.EventBusShowCatModel;
import com.e.ecommerce.model.ProductCategoryModel;
import com.e.ecommerce.model.ProductCategoryParentModel;
import com.e.ecommerce.network.ApiInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsCategoryListFragment extends Fragment {
    private HomeProductCategoryListParentAdapater homeProductCategoryListParentAdapater;
    private ProgressBar progressBar;
    private LinearLayout errorResponseLayout;
    private Button btnRetry;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Spinner spinner;
    private boolean isSpinnerClicked = false;
    private ArrayList<String> parentCat;

    public ProductsCategoryListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_products_category_list, container, false);
        EventBus.getDefault().register(this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView_productcategorylist);
        progressBar = rootView.findViewById(R.id.progressBar);
        spinner = rootView.findViewById(R.id.idCatListSpinner);
        errorResponseLayout = rootView.findViewById(R.id.idLayoutErrorResponse);
        btnRetry = rootView.findViewById(R.id.idBtnRetry);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPrimary));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeProductCategoryListParentAdapater = new HomeProductCategoryListParentAdapater(AppConfig.productCategoryParentModels, getActivity());
        recyclerView.setAdapter(homeProductCategoryListParentAdapater);
        parentCat = new ArrayList<>();
        getAppParentProductsCategoryList();
/*

        recyclerView.addOnItemTouchListener(new ApartmentWiseReportsActivity.RecyclerTouchListener(AppController.getInstance().getApplicationContext(), recyclerView, new ApartmentWiseReportsActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(AppController.getInstance().getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getContext(), ProductListActivity.class));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
*/

        btnRetry.setOnClickListener(view -> {
            errorResponseLayout.setVisibility(View.GONE);
            getAppParentProductsCategoryList();
        });

        swipeRefreshLayout.setOnRefreshListener(() -> new Handler().postDelayed(() -> {
            errorResponseLayout.setVisibility(View.GONE);
            getAppParentProductsCategoryList();

        }, 1000));



        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                isSpinnerClicked = true;
                return false;
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.BLACK);
                Toast.makeText(AppController.getInstance().getApplicationContext(), AppConfig.productCategoryParentModels.get(position).getTitle(), Toast.LENGTH_LONG).show();
                if (isSpinnerClicked) {
                    /*Intent intent = new Intent(AppController.getInstance().getApplicationContext(), ProductListActivity.class);
                    intent.putExtra(PreferManager.PRODUCT_ID, AppConfig.productCategoryParentModels.get(position).getParentId());
                    System.out.println("ProductsCategoryId:"+AppConfig.productCategoryParentModels.get(position).getParentId());
                    assert getActivity() != null;
                    getActivity().startActivity(intent);*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return rootView;
    }

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshFilters(EventBusShowCatModel event) {
       /* if(spinner.getVisibility()==View.VISIBLE){
            spinner.setVisibility(View.GONE);
        }else{
            spinner.setVisibility(View.VISIBLE);
        }*/

        startActivity(new Intent(AppController.getInstance().getApplicationContext(), CategoriesFragment.class));
    }


    /**
     * method fetches all parent category list from server
     * example type of all products dresses,shirts and etc.
     */
    private void getAppParentProductsCategoryList() {
        AppConfig.productCategoryParentModels.clear();
        parentCat.clear();
        progressBar.setVisibility(View.VISIBLE);
        OAuthInterceptor oauth1Woocommerce = new OAuthInterceptor.Builder()
                .consumerKey(AppConfig.CONSUMER_KEY)
                .consumerSecret(AppConfig.CONSUMER_SECRET_KEY)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(oauth1Woocommerce)
                .build();

        ApiInterface apiService = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL_USER).addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(ApiInterface.class);

        Call<ArrayList<ProductCategoryModel>> call = apiService.getAllProducts("publish",0,1,70, "name", "asc");
        Log.d("url is", call.request().url().toString());
        call.enqueue(new Callback<ArrayList<ProductCategoryModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductCategoryModel>> call, @NonNull retrofit2.Response<ArrayList<ProductCategoryModel>> response) {
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                if (response.body() != null) {
                    if (response.code() == 200) {
                        ArrayList<ProductCategoryModel> productCategoryModelArrayList = response.body();
                        if (productCategoryModelArrayList.size() > 0) {
                            recyclerView.setVisibility(View.VISIBLE);
                            for (ProductCategoryModel productCategoryModel : productCategoryModelArrayList) {
                                if (productCategoryModel != null) {
                                    fetchSubCategory(productCategoryModel);
                                    parentCat.add(productCategoryModel.getName());
                                }
                            }
                            if (parentCat.size() > 0) {
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, parentCat);
                                spinner.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }

                        }
                    }
                }

                if (response.errorBody() != null) {
                    progressBar.setVisibility(View.GONE);
                    errorResponseLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductCategoryModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                errorResponseLayout.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void fetchSubCategory(final ProductCategoryModel productCategoryModel) {
        OAuthInterceptor oauth1Woocommerce = new OAuthInterceptor.Builder()
                .consumerKey(AppConfig.CONSUMER_KEY)
                .consumerSecret(AppConfig.CONSUMER_SECRET_KEY)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(oauth1Woocommerce)
                .build();

        ApiInterface apiService = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL_USER).addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(ApiInterface.class);

        Call<ArrayList<ProductCategoryModel>> call = apiService.getAllProducts("publish",productCategoryModel.getId(),1,70, "name", "asc");
        Log.d("url is", call.request().url().toString());

        final ProductCategoryParentModel productCategoryParentModel = new ProductCategoryParentModel();
        final ArrayList<ProductCategoryModel> productCategoryItemModels = new ArrayList<>();
        productCategoryParentModel.setTitle(productCategoryModel.getName());
        productCategoryParentModel.setParentId(productCategoryModel.getId());
        productCategoryParentModel.setProductCategoryItemModels(productCategoryItemModels);
        AppConfig.productCategoryParentModels.add(productCategoryParentModel);

        homeProductCategoryListParentAdapater.notifyDataSetChanged();
        call.enqueue(new Callback<ArrayList<ProductCategoryModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductCategoryModel>> call, @NonNull retrofit2.Response<ArrayList<ProductCategoryModel>> response) {
                productCategoryParentModel.setDataFetched(true);
                if (response.body() != null) {
                    if (response.code() == 200) {
                        ArrayList<ProductCategoryModel> productCategoryModels = response.body();
                        if (productCategoryModels != null && productCategoryModels.size() > 0) {
                            productCategoryItemModels.addAll(productCategoryModels);
                        }
                    }
                }

                if (response.errorBody() != null) {

                }

                homeProductCategoryListParentAdapater.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<ProductCategoryModel>> call, Throwable t) {
                productCategoryParentModel.setDataFetched(true);
                homeProductCategoryListParentAdapater.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
