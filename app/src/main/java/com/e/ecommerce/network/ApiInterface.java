package com.e.ecommerce.network;
import com.e.ecommerce.model.AddProductWishListModelRequest;
import com.e.ecommerce.model.AddProductWishListModelResponse;
import com.e.ecommerce.model.AddShippingAddressResponseModel;
import com.e.ecommerce.model.AddressModel;
import com.e.ecommerce.model.AddressModelShipping;
import com.e.ecommerce.model.AddressModelShippingEdit;
import com.e.ecommerce.model.BookAppointmentModelResponse;
import com.e.ecommerce.model.BookAppoitmentRequestModel;
import com.e.ecommerce.model.BookingCancelModel;
import com.e.ecommerce.model.BookingCancelResponseModel;
import com.e.ecommerce.model.BookingDetailsModel;
import com.e.ecommerce.model.CouponModel;
import com.e.ecommerce.model.DeleteAddressReqeusetModel;
import com.e.ecommerce.model.DeleteOrdersModel;
import com.e.ecommerce.model.DeliveryOrderDetailsModel;
import com.e.ecommerce.model.FetchHeaderItemsModel;
import com.e.ecommerce.model.FilterResonseModel;
import com.e.ecommerce.model.GetAllCouponsModel;
import com.e.ecommerce.model.HomeViewPagerModel;
import com.e.ecommerce.model.MinMaxResponseModel;
import com.e.ecommerce.model.MyOrdersResponseModel;
import com.e.ecommerce.model.PlaceOrderModel;
import com.e.ecommerce.model.PrimaryAddressRequestModel;
import com.e.ecommerce.model.ProductCategoryModel;
import com.e.ecommerce.model.ProductModel;
import com.e.ecommerce.model.ProductWishLlistModel;
import com.e.ecommerce.model.RemoveProductFromWishListModel;
import com.e.ecommerce.model.RemoveProductFromWishlistResponseModel;
import com.e.ecommerce.model.UpdateUserName;
import com.e.ecommerce.model.UserDetailsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 */

public interface ApiInterface {

    @POST("wp-json/woo/v1/addwishlistitem")
    Call<AddProductWishListModelResponse> addProductToWishList(@Header("Authorization") String token, @Body AddProductWishListModelRequest addProductWishListModelRequest);
    @POST("wp-json/woo/v1/removewishlistitem")
    Call<RemoveProductFromWishlistResponseModel> removeProductFromWishList(@Header("Authorization") String token, @Body RemoveProductFromWishListModel removeProductFromWishListModel);
    @GET("wp-json/wc/v3/products/categories")
    Call<ArrayList<ProductCategoryModel>> getAllProducts(@Query("status") String status, @Query("parent") int categoryType, @Query("page") int page, @Query("per_page") int pageSize, @Query("orderby") String orderBy, @Query("order") String order);

    @GET("wp-json/wc/v3/products")
    Call<ArrayList<ProductModel>> getProductsList(@Query("status") String status, @Query("stock_status") String stock_status, @Query("page") int page, @Query("per_page") int pageSize);
    @GET("/wp-json/wc/v3/products/categories")
    Call<ArrayList<ProductModel>> getProductCategoryList(@Query("status") String status, @Query("parent") int categoryType);
    //@GET("wp-json/wc/v3/products")
  //  Call<ArrayList<FetchHeaderItemsModel>> getProductsList(@Query("status") String status,@Query("meta_value") String meta_value);
    @GET("wp-json/wc/v3/products")
    Call<ArrayList<ProductModel>> getProductsList(@Query("status") String status, @Query("search") String search, @Query("stock_status") String stock_status, @Query("on_sale") String on_sale);


    @GET("wp-json/woo/v1/firstlevelcategories")
    Call<ArrayList<FetchHeaderItemsModel>> getHeaderProductsItemList(@Header("Authorization") String token, @Query("user_id") String userId);

    @POST("wp-json/woo/v1/checkzipcodeserviceable")
    Call<ArrayList<AddressModel>> checkZipCodeServiceable(@Query("user_id") String userId, @Query("zipcode") String zipCode);


    @GET("wp-json/woo/v1/categories")
    Call<FilterResonseModel> getFilters(@Header("Authorization") String token);


    @GET("wp-json/woo/v1/catminmax")
    Call<MinMaxResponseModel> getMinMaxBasedOnId(@Header("Authorization") String token, @Query("user_id") int user_id, @Query("cat_id") int cat_id);


    @GET("wp-json/woo/v1/listofwishlist")
    Call<ArrayList<ProductWishLlistModel>> getProductWishList(@Header("Authorization") String token, @Query("user_id") int user_id);
    //?orderby=price&order=desc&min_price=234&max_price=1500
    @GET("wp-json/wc/v3/products")
    //Call<ArrayList<ProductModel>> getCategoryProductList(@Query("category") String categoryId,@Query("orderby") String orderBy, @Query("order") String order, @Query("min_price") int minPrice, @Query("max_price") int maxPrice, @Query("search") String searchKey);
    Call<ArrayList<ProductModel>> getCategoryProductList(@Query("status") String status, @Query("category") String categoryId, @Query("page") int pageCount, @Query("per_page") int perPage, @Query("orderby") String orderBy, @Query("order") String order, @Query("min_price") int minPrice, @Query("max_price") int maxPrice, @Query("search") String searchKey);


    //?orderby=price&order=desc&min_price=234&max_price=1500
    @GET("wp-json/wc/v3/products")
    //Call<ArrayList<ProductModel>> getCategoryProductList(@Query("category") String categoryId,@Query("orderby") String orderBy, @Query("order") String order, @Query("min_price") int minPrice, @Query("max_price") int maxPrice, @Query("search") String searchKey);
    Call<ArrayList<ProductModel>> getCategoryProductList(@Query("category") String categoryId, @Query("page") int pageCount, @Query("per_page") int perPage, @Query("orderby") String orderBy, @Query("order") String order, @Query("min_price") int minPrice, @Query("max_price") int maxPrice, @Query("search") String searchKey);

    //@GET("wp-json/woo/v1/searching")
    @GET("wp-json/woo/v1/searching")
    Call<ArrayList<ProductModel>> getProductsBasedOnKey(@Header("Authorization") String token, @Query("status") String status, @Query("search") String searchKey, @Query("category") String category, @Query("orderby") String orderBy, @Query("order") String order, @Query("min_price") int minPrice, @Query("max_price") int maxPrice);



    @GET("wp-json/woo/v1/searching")
    Call<ArrayList<ProductModel>> getProductsBasedOnKey(@Header("Authorization") String token, @Query("search") String searchKey, @Query("category") String category, @Query("orderby") String orderBy, @Query("order") String order, @Query("min_price") int minPrice, @Query("max_price") int maxPrice);

    @GET("wp-json/woo/v1/searching")
    Call<ArrayList<ProductModel>> getProductsBasedOnKeyWithOutCategory(@Header("Authorization") String token, @Query("search") String searchKey, @Query("orderby") String orderBy, @Query("order") String order, @Query("min_price") int minPrice, @Query("max_price") int maxPrice);


    @GET("wp-json/woo/v1/searching")
    Call<ArrayList<ProductModel>> getProductsBasedOnKeyWithOutCategory(@Header("Authorization") String token, @Query("status") String status, @Query("search") String searchKey, @Query("orderby") String orderBy, @Query("order") String order, @Query("min_price") int minPrice, @Query("max_price") int maxPrice);


    @GET("wp-json/woo/v1/validatecoupon")
    Call<CouponModel> getCoupon(@Header("Authorization") String token, @Query("user_id") String user_id, @Query("coupon_code") String coupon_code);


    @GET("wp-json/wc/v3/customers/{id}")
    Call<UserDetailsModel> getUserAddress(@Path("id") String id);

    @PUT("wp-json/wc/v3/customers/{id}")
    Call<UserDetailsModel> sendUserAddresstoServer(@Path("id") String id, @Body UserDetailsModel addressModel);

    @PUT("wp-json/wc/v3/customers/{id}")
    Call<UserDetailsModel> updateUserName(@Path("id") String id, @Body UpdateUserName addressModel);


    @Headers("Content-Type: application/json")
    @POST("wp-json/wc/v3/orders")
    Call<PlaceOrderModel> placeOrder(@Body PlaceOrderModel placeOrderModel);

    @Headers("Content-Type: application/json")
    @POST("wp-json/woo/v1/setlocations")
    Call<AddShippingAddressResponseModel> addShippingAddress(@Header("Authorization") String token, @Body AddressModelShipping placeOrderModel);

    @Headers("Content-Type: application/json")
    @PUT("wp-json/woo/v1/editlocation")
    Call<AddShippingAddressResponseModel> editShippingAddress(@Header("Authorization") String token, @Body AddressModelShippingEdit placeOrderModel);

    @Headers("Content-Type: application/json")
    @POST("wp-json/woo/v1/deletelocation")
    Call<AddShippingAddressResponseModel> deleteShippingAddress(@Header("Authorization") String token, @Body DeleteAddressReqeusetModel placeOrderModel);

    @Headers("Content-Type: application/json")
    @POST("wp-json/woo/v1/makeprimary")
    Call<AddShippingAddressResponseModel> primaryShippingAddress(@Header("Authorization") String token, @Body PrimaryAddressRequestModel placeOrderModel);


    @Headers("Content-Type: application/json")
    @POST("wp-json/woo/v1/cancelappointment")
    Call<BookingCancelResponseModel> cancelBooking(@Header("Authorization") String token, @Body BookingCancelModel placeOrderModel);

    @GET("wp-json/wc/v3/orders")
    Call<ArrayList<MyOrdersResponseModel>> getMyOrders(@Query("customer") int customerId);
    @GET("wp-json/wc/v3/orders")
    Call<MyOrdersResponseModel> getMyOrder(@Query("id") int id);
    @GET("wp-json/woo/v1/salespersonapplist")
    Call<ArrayList<DeliveryOrderDetailsModel>> getDeliveryOrdersList(@Header("Authorization") String token, @Query("user_id") String customerId);

    @GET("wp-json/woo/v1/getdates")
    Call<ArrayList<String>> getAllDates(@Header("Authorization") String token);

    @GET("wp-json/woo/v1/listofappointments")
    Call<ArrayList<BookingDetailsModel>> getUserBookings(@Header("Authorization") String token, @Query("user_id") String userId);
    @POST("wp-json/woo/v1/bookanappointment")
    Call<BookAppointmentModelResponse> bookAppoientment(@Header("Content-Type") String content_type, @Header("Authorization") String token, @Body BookAppoitmentRequestModel timeSlotRequestModel);
    @GET("wp-json/wc/v3/products/{id}")
    Call<ProductModel> getDeliveryManProductToCart(@Path("id") String id);
    @PUT("wp-json/wc/v3/orders/{id}")
    Call<MyOrdersResponseModel>deleteOrders(@Path("id") String id, @Query("customer_note") String customer_note, @Body DeleteOrdersModel deleteOrdersModel);
    @GET("wp-json/woo/v1/events")
    Call<ArrayList<HomeViewPagerModel>> getHomeEvent(@Header("Authorization") String token, @Query("user_id") String userId);
    @GET("wp-json/wc/v3/coupons")
    Call<ArrayList<GetAllCouponsModel>> allCoupuns();
    @GET("wp-json/wc/v3/products")
    Call<ArrayList<ProductModel>>getProductListByCatId(@Query("category") String category);
    @GET("wp-json/wc/v3/products")
    Call<ArrayList<ProductModel>>getProductListByCatId(@Query("status") String status, @Query("on_sale") String on_sale);
    @GET("wp-json/wc/v3/products")
    Call<ArrayList<ProductModel>>getSaleProductList(@Query("on_sale") String on_sale, @Query("page") int page, @Query("per_page") int per_page);
//    @GET("wp-json/wc/v3/products")
//    Call<ArrayList<ProductModel>> getProductsBasedOnKeyOffers(@Query("status") String status, @Query("search") String searchKey, @Query("category") String category, @Query("orderby") String orderBy, @Query("order") String order, @Query("min_price") int minPrice, @Query("max_price") int maxPrice,@Query("on_sale") String on_sale);
//    @GET("wp-json/wc/v3/products")
//    Call<ArrayList<ProductModel>> getProductsBasedOnKeyWithOutCategoryOffers(@Query("status") String status, @Query("search") String searchKey, @Query("orderby") String orderBy, @Query("order") String order, @Query("min_price") int minPrice, @Query("max_price") int maxPrice,@Query("on_sale") String on_sale);
    @GET("wp-json/woo/v1/singleevent")
    Call<HomeViewPagerModel> getHomeEventDetail(@Header("Authorization") String token, @Query("user_id") String userId, @Query("event_id") int event_id);
    @GET("wp-json/wc/v3/products")
    Call<ArrayList<ProductModel>> getProductsBasedOnKeyOffers(@Query("status") String status, @Query("search") String searchKey, @Query("category") String category, @Query("orderby") String orderBy, @Query("order") String order, @Query("min_price") int minPrice, @Query("max_price") int maxPrice, @Query("on_sale") String on_sale);
    @GET("wp-json/wc/v3/products")
    Call<ArrayList<ProductModel>> getProductsBasedOnKeyOffers(@Query("status") String status, @Query("orderby") String orderBy, @Query("order") String order, @Query("on_sale") String on_sale, @Query("page") int page, @Query("per_page") int per_page);
    @GET("wp-json/wc/v3/products")
    Call<ArrayList<ProductModel>> getProductsBasedOnKeyWithOutCategoryOffers(@Query("status") String status, @Query("search") String searchKey, @Query("orderby") String orderBy, @Query("order") String order, @Query("min_price") int minPrice, @Query("max_price") int maxPrice, @Query("on_sale") String on_sale);



}