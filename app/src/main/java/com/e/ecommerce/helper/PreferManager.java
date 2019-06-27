package com.e.ecommerce.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.e.ecommerce.model.AddressModel;
import com.e.ecommerce.model.ProductModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PreferManager {
    public static final String PROFILE_PASSWORD = "Profile_password";
    public static final String PARENT_ID = "parentId";
    public static final String PRODUCT_ID = "productid";
    public static final String PRODUCT_NAME = "productName";
    public static final String PREF = "pref";
    public static final String ADDRESS_FIRST_NAME = "ADDRESS_FIRST_NAME";
    public static final String ADDRESS_LAST_NAME = "ADDRESS_LAST_NAME";
    public static final String ADDRESS_MOBILE_NUMBER = "ADDRESS_MOBILE_NUMBER";
    public static final String ADDRESS_FIRST_ADDRESS = "ADDRESS_FIRST_ADDRESS";
    public static final String ADDRESS_SECOND_ADDRESS = "ADDRESS_SECOND_ADDRESS";
    public static final String ADDRESS_CITY_NAME = "ADDRESS_CITY_NAME";
    public static final String ADDRESS_STATE_NAME = "ADDRESS_STATE_NAME";
    public static final String ADDRESS_PINCODE_NAME = "ADDRESS_PINCODE_NAME";
    public static final String KEY_ENABLE_CHECKBOX = "KEY_ENABLE_CHECKBOX";



    // Shared preferences file name
    private static final String PREF_NAME = "AndroidHive";
    private static final String TAG = PreferManager.class.getSimpleName();
    // All Shared Preferences Keys
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USER_NAME = "username";
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";
    private static final String KEY_API_KEY = "apiKey";
    private static final String KEY_MOBILE_NUMBER = "mobileNumber";
    private static final String KEY_ROLE = "role";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_NOTIFICATIONS_COUNT = "notificationsCount";
    private static final String KEY_NOTIFICATIONS = "notifications";
    private static final String IS_NOTIFICATIONS_ON = "IsNotificationON";
    private static final String IS_OUTSTATION_ACTIVATED = "IsOutstationActivated";
    private static final String KEY_PUSHY_REGISTER_ID = "pushy_register_id";
    private static final String IS_FCM_TOKEN_SEND_TO_SERVER = "isFCMTokenSendToServer";
    private static final String KEY_APP_VERSION_CODE = "appVersionCode";

    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Shared Preferences
    private SharedPreferences pref;
    // Editor for Shared preferences
    private SharedPreferences.Editor editor;
    // Context
    private Context _context;


    public PreferManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLogin(String password, String email, String mobile) {
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_MOBILE_NUMBER, mobile);
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> profile = new HashMap<>();
        profile.put("password", pref.getString(KEY_PASSWORD, null));
        profile.put("email", pref.getString(KEY_EMAIL, null));
        profile.put("mobile", pref.getString(KEY_MOBILE_NUMBER, null));
        return profile;
    }

    public void storeProductDetails() {

    }

    public void addNotification(String notification) {
        // get old notifications
        String oldNotifications = getNotifications();
        if (oldNotifications != null) {
            oldNotifications += "|" + notification;
        } else {
            oldNotifications = notification;
        }
        editor.putString(KEY_NOTIFICATIONS, oldNotifications);
        editor.commit();
    }

    public String getNotifications() {
        return pref.getString(KEY_NOTIFICATIONS, null);
    }

    public void clearSession() {
        editor.clear();
        editor.commit();
    }

    public User getUser() {
        if (pref.getString(KEY_USER_ID, null) != null) {
            String userName, email, mobile, apiKey, userID, fisrtName, lastName;
            int role;
            apiKey = pref.getString(KEY_API_KEY, null);
            userName = pref.getString(KEY_USER_NAME, null);
            email = pref.getString(KEY_EMAIL, null);
            mobile = pref.getString(KEY_MOBILE_NUMBER, null);
            fisrtName = pref.getString(KEY_FIRST_NAME, null);
            lastName = pref.getString(KEY_LAST_NAME, null);
            userID = pref.getString(KEY_USER_ID, null);
            role = pref.getInt(KEY_ROLE, 0);
            return new User(userName, email, mobile, apiKey, userID, fisrtName, lastName, role);
        }
        return null;
    }

    public void storeUser(User user) {
        editor.putString(KEY_API_KEY, user.getApiKey());
        editor.putString(KEY_USER_NAME, user.getUserName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_MOBILE_NUMBER, user.getMobile());
        editor.putString(KEY_USER_ID, user.getUserId());
        editor.putString(KEY_FIRST_NAME, user.getUserFisrtName());
        editor.putString(KEY_LAST_NAME, user.getUserLastName());
        editor.putInt(KEY_ROLE, user.getRole());
        editor.commit();
        Log.e(TAG, "User is stored in shared preferences. " + user.getName() + ", " + user.getEmail());
    }

    public boolean isNotificationON() {
        return pref.getBoolean(IS_NOTIFICATIONS_ON, true);
    }

    public void setNotificationON(boolean isNotificationOn) {
        editor.putBoolean(IS_NOTIFICATIONS_ON, isNotificationOn);
        editor.commit();
    }

    public void setIsFCMTokenSendToServer(boolean isFCMTokenSend) {
        editor.putBoolean(IS_OUTSTATION_ACTIVATED, isFCMTokenSend);
        editor.commit();
    }

    public boolean isFCMTokenSendToServer() {
        return pref.getBoolean(IS_FCM_TOKEN_SEND_TO_SERVER, false);
    }

    public String getKeyPushyRegisterId() {
        return pref.getString(KEY_PUSHY_REGISTER_ID, null);
    }

    public void setKeyPushyRegisterId(String keyPushyRegisterId) {
        editor.putString(KEY_PUSHY_REGISTER_ID, keyPushyRegisterId);
        editor.commit();
    }

    public int getKeyNotificationsCount() {
        return pref.getInt(KEY_NOTIFICATIONS_COUNT, 0);
    }

    public void setKeyNotificationsCount(int notificationsCount) {
        pref = PreferenceManager.getDefaultSharedPreferences(_context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(KEY_NOTIFICATIONS_COUNT, notificationsCount);
        editor.apply();
    }

    public void setAppVersionCode(String strAppVersionCode) {
        editor.putString(KEY_APP_VERSION_CODE, strAppVersionCode);
        editor.commit();
    }


    public void storeCartList(ArrayList<ProductModel> addtoCartArrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(addtoCartArrayList);
        editor.putString("CartList", json);
        editor.commit();
    }


    public ArrayList<ProductModel> getCartList() {
        ArrayList<ProductModel> getCartList;
        Gson gson = new Gson();
        String json = pref.getString("CartList", "");
        if (json.isEmpty()) {
            getCartList = new ArrayList<ProductModel>();
        } else {
            Type type = new TypeToken<List<ProductModel>>() {
            }.getType();
            getCartList = gson.fromJson(json, type);
        }
        return getCartList;
    }

    public void storeInterestList(ArrayList<ProductModel> addtoInterestList) {
        Gson gson = new Gson();
        String json = gson.toJson(addtoInterestList);
        editor.putString("InterestList", json);
        editor.commit();
    }

    public ArrayList<ProductModel> getInterestList() {
        ArrayList<ProductModel> getCartList;
        Gson gson = new Gson();
        String json = pref.getString("InterestList", "");
        if (json.isEmpty()) {
            getCartList = new ArrayList<ProductModel>();
        } else {
            Type type = new TypeToken<List<ProductModel>>() {
            }.getType();
            getCartList = gson.fromJson(json, type);
        }
        return getCartList;
    }

    public void storeAddressList(ArrayList<AddressModel> addToAddressList) {
        Gson gson = new Gson();
        String json = gson.toJson(addToAddressList);
        editor.putString("AddressList", json);
        editor.commit();
    }

    public ArrayList<AddressModel> getAddressList() {
        ArrayList<AddressModel> getAddressList;
        Gson gson = new Gson();
        String json = pref.getString("AddressList", "");
        if (json.isEmpty()) {
            getAddressList = new ArrayList<AddressModel>();
        } else {
            Type type = new TypeToken<List<AddressModel>>() {
            }.getType();
            getAddressList = gson.fromJson(json, type);
        }
        return getAddressList;
    }
}
