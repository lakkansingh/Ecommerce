package com.e.ecommerce.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.e.ecommerce.R;
import com.e.ecommerce.adapters.PaginationAdapter;
import com.e.ecommerce.adapters.home.HomeHeaderProuctsListAdapter;
import com.e.ecommerce.helper.ApiInterfaceServiceConnection;
import com.e.ecommerce.helper.PaginationScrollListener;
import com.e.ecommerce.model.ImagesModel;
import com.e.ecommerce.model.ProductModel;
import com.e.ecommerce.network.ApiInterface;
import com.e.ecommerce.receivers.ConnectivityReceiver;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private HomeHeaderProuctsListAdapter homeHeaderProuctsListAdapter;
    private PaginationAdapter paginationAdapter;
    private ConnectivityReceiver connectivityReceiver;
    private ArrayList<ProductModel> productModelArrayList;
    private RecyclerView recyclerView, recycler_special_offers;
    private ProgressBar progressBar;
    private Context mContext;
    private LinearLayoutManager layoutManager;
    private ArrayList<ProductModel> productModelOffersArrayList;
    // Index from which pagination should start (0 is 1st page in our case)
    private static final int PAGE_START = 1;

    // Indicates if footer ProgressBar is shown (i.e. next page is loading)
    private boolean isLoading = false;

    // If current page is the last page (Pagination will stop after this page load)
    private boolean isLastPage = false;

    // total no. of pages to load. Initial load is page 0, after which 2 more pages will load.
    private int TOTAL_PAGES = 10;
    // indicates the current page which Pagination is fetching.
    private int currentPage = PAGE_START;
    private int page_size_initial = 20;
    private int PAGE_SIZE = page_size_initial;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recycler_special_offers = view.findViewById(R.id.recycler_special_offers);
        progressBar = view.findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager layoutManagerOffers = new LinearLayoutManager(getActivity());
        layoutManagerOffers.setOrientation(LinearLayoutManager.HORIZONTAL);
        connectivityReceiver = new ConnectivityReceiver();
        productModelArrayList = new ArrayList<>();
        productModelOffersArrayList = new ArrayList<>();
        recyclerView.setLayoutManager(layoutManager);
        recycler_special_offers.setLayoutManager(layoutManagerOffers);


        fetchHeaderItems();
        fetchSpecialOffers();
        return view;
    }

    private void fetchHeaderItems() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterfaceServiceConnection apiInterfaceService = new ApiInterfaceServiceConnection();
        ApiInterface apiService = apiInterfaceService.apiServiceMethod();
        Call<ArrayList<ProductModel>> call = apiService.getProductsList("publish", "instock", currentPage, PAGE_SIZE);
        call.enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    ArrayList<ProductModel> productCategoryModelArrayList = response.body();
                    if (productCategoryModelArrayList.size() > 0) {
                        for (ProductModel productModel : productCategoryModelArrayList) {
                            if (productModel != null && productModel.getStock_status().equals("instock")) {
                                paginationAdapter = new PaginationAdapter(getContext(), productModelArrayList);
                                productModelArrayList.add(productModel);
                            }
                        }
                        recyclerView.setAdapter(paginationAdapter);
                        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
                            @Override
                            protected void loadMoreItems() {
                                isLoading = true;
                                currentPage += 1;
                                // mocking network delay for API call
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        loadNextPage();
                                    }
                                }, 1000);
                            }

                            @Override
                            public int getTotalPageCount() {
                                return TOTAL_PAGES;
                            }

                            @Override
                            public boolean isLastPage() {
                                return isLastPage;
                            }

                            @Override
                            public boolean isLoading() {
                                return isLoading;
                            }
                        });
                        // mocking network delay for API call
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadFirstPage();
                            }
                        }, 1000);

                        paginationAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void fetchSpecialOffers() {
        progressBar.setVisibility(View.VISIBLE);
        recycler_special_offers.setVisibility(View.VISIBLE);
        ApiInterfaceServiceConnection apiInterfaceServiceConnection = new ApiInterfaceServiceConnection();
        ApiInterface apiService = apiInterfaceServiceConnection.apiServiceMethod();
        Call<ArrayList<ProductModel>> call = apiService.getProductsBasedOnKeyOffers("publish", "date", "asc", "true", 1, 70);
        call.enqueue(new Callback<ArrayList<ProductModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductModel>> call, Response<ArrayList<ProductModel>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    ArrayList<ImagesModel> imagesModels = new ArrayList<>();
                    ImagesModel imagesModel = new ImagesModel();
                    ProductModel productModel1 = new ProductModel();
                    imagesModel.setSrc("https://firebasestorage.googleapis.com/v0/b/e-commerce-84f23.appspot.com/o/E-commerce%2Foffers.jpg?alt=media&token=244a6b39-19e9-438a-9692-46015628a8d8");
                    // imagesModel.setSrc(R.drawable.offers+"");
                    imagesModels.add(0, imagesModel);
                    productModel1.setImagesModelArrayList(imagesModels);
                    productModelOffersArrayList.add(0, productModel1);
                    ArrayList<ProductModel> productModelsResult = response.body();
                    if (productModelsResult.size() > 0) {
                        for (ProductModel productModel : productModelsResult) {
                            if (productModel != null && productModel.getOn_sale().equals("true") && productModel.getStock_status().equals("instock")) {
                                homeHeaderProuctsListAdapter = new HomeHeaderProuctsListAdapter(getContext(), productModelOffersArrayList, true);
                                productModelOffersArrayList.add(productModel);
                            }

                        }
                        recycler_special_offers.setAdapter(homeHeaderProuctsListAdapter);
                        homeHeaderProuctsListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    // Initialise it from onAttach()
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void loadNextPage() {
        PAGE_SIZE += page_size_initial;
        ProductModel productModel = new ProductModel();
        ArrayList<ProductModel> products = productModel.createProducts(PAGE_SIZE);  // 1
        paginationAdapter.removeLoadingFooter();  // 2
        isLoading = false;   // 3
        paginationAdapter.addAll(products);   // 4
        if (currentPage != TOTAL_PAGES) paginationAdapter.addLoadingFooter();  // 5
        else isLastPage = true;
    }

    private void loadFirstPage() {
        // fetching dummy data
        ProductModel productModel = new ProductModel();
        ArrayList<ProductModel> products = productModel.createProducts(paginationAdapter.getItemCount());  // 1
        progressBar.setVisibility(View.GONE);
        paginationAdapter.addAll(products);

        if (currentPage <= TOTAL_PAGES) paginationAdapter.addLoadingFooter();
        else isLastPage = true;
    }
}
