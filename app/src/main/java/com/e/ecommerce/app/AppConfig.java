package com.e.ecommerce.app;


import com.e.ecommerce.model.ProductCategoryParentModel;

import java.util.ArrayList;

/**
 * Created by lsn developer on 19/6/19.
 */

public class AppConfig {

    // Development Build
    public static final ArrayList<ProductCategoryParentModel> productCategoryParentModels = new ArrayList<>();
    public final static String BASE_URL_USER = "https://woocommerce01.4devlab.net/";
    public static final String CONSUMER_KEY = "ck_5a09376577c43c1e150981990581860413fe7804";
    public static final String CONSUMER_SECRET_KEY = "cs_c05dfea041f95fc33bf58a3c87dfdcc782c4031b";
    //public new_login static String BASE_URL_USER="http://smiley.inventbird.in/VendorApi/";
    public static final String URL_CHECK_APP_VERSION = BASE_URL_USER + "checkVersionCode";
    public final static String URL_DOWNLOAD_REPORTS = BASE_URL_USER + "tomorrowReports/3";
}
