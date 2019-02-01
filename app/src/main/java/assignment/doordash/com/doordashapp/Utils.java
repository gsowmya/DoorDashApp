package assignment.doordash.com.doordashapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import assignment.doordash.com.doordashapp.activity.dataholders.ListDataHolder;
import assignment.doordash.com.doordashapp.activity.dataholders.RestaurantListItem;

public class Utils {

    private static String key_like = "like";
    private static String key_unlike = "unlike";
    private static String PREF_NAME = "UserPreference";
    private static String key = "key";


    public static void saveRestaurrentValues(Context context, ListDataHolder listDataHolder){
        SharedPreferences.Editor editor;
        Gson gson = new Gson();
        String json = gson.toJson(listDataHolder.getRestaurantListItemList());
        editor = getSharedPreferences(context).edit();
        editor.remove(key).commit();
        editor.putString(key, json);
        editor.commit();
    }

    public static List<RestaurantListItem> getRestaurentDataHolder(Context context){
        Gson gson = new Gson();
        String response=getSharedPreferences(context).getString(key , null);
        ArrayList<RestaurantListItem> lstArrayList = gson.fromJson(response,
                new TypeToken<List<RestaurantListItem>>(){}.getType());
        return lstArrayList;
    }

    private static SharedPreferences getSharedPreferences(Context context){
        SharedPreferences shref;

        shref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return shref;
    }

    public static void saveLikePreference(Context context,String value){
        SharedPreferences.Editor editor;
        editor = getSharedPreferences(context).edit();
        editor.putString(key_like,value).commit();
    }

    public static void saveUnLikePreference(Context context,String value){
        SharedPreferences.Editor editor;
        editor = getSharedPreferences(context).edit();
        editor.putString(key_unlike,value).commit();
    }

    public static String  getLikeList(Context context){
        return getSharedPreferences(context).getString(key_like,"");
    }

    public static String  getUnLikeList(Context context){
        return getSharedPreferences(context).getString(key_unlike,"");
    }


}
