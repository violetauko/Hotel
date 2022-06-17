//package com.ellah.ellahveehotels.services;
//
//import com.ellah.ellahveehotels.Constants;
//import com.ellah.ellahveehotels.models.Business;
//import com.ellah.ellahveehotels.models.Category;
//import com.ellah.ellahveehotels.models.Coordinates;
//import com.ellah.ellahveehotels.models.Location;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.HttpUrl;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class BookingService {
//    public void findHotels(String location, Callback callback){
//        OkHttpClient client = new OkHttpClient.Builder()
//                .build();
//
//        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BOOKING_BASE_URL).newBuilder();
//        urlBuilder.addQueryParameter(Constants.BOOKING_LOCATION_QUERY_PARAMETER, location);
//        String url = urlBuilder.build().toString();
//
//        Request request = new Request.Builder()
//                .url(url)
//                .header("Authorization", Constants.BOOKING_API_KEY)
//                .build();
//
//        Call call = client.newCall(request);
//        call.enqueue(callback);
//    }
//
//    public ArrayList<Business> processResults(Response response){
//        ArrayList<Business> hotels = new ArrayList<>();
//        try{
//            String jsonData = response.body().string();
//            JSONObject yelpJSON = new JSONObject(jsonData);
//            JSONArray businessesJSON = yelpJSON.getJSONArray("businesses");
//            if (response.isSuccessful()){
//                for (int i = 0; i < businessesJSON.length(); i++){
//                    JSONObject businessJSON = businessesJSON.getJSONObject(i);
//                    String name = businessJSON.getString("name");
//                    String phone = businessJSON.optString("display_phone", "Phone not available");
//                    String website = businessJSON.getString("url");
//                    double rating = businessJSON.getDouble("rating");
//                    String imageUrl = businessJSON.getString("image_url");
//                    double latitude = businessJSON.getJSONObject("coordinates").getDouble("latitude");
//                    double longitude = businessJSON.getJSONObject("coordinates").getDouble("longitude");
//                    ArrayList<String> address = new ArrayList<>();
//                    JSONArray addressJSON = businessJSON.getJSONObject("location").getJSONArray("display_address");
//                    for (int y = 0; y < addressJSON.length(); y++){
//                        address.add(addressJSON.get(y).toString());
//                    }
//                    ArrayList<String> categories = new ArrayList<>();
//                    JSONArray categoriesJSON = businessJSON.getJSONArray("categories");
//                    for (int y = 0; y < categoriesJSON.length(); y++){
//                        categories.add(categoriesJSON.getJSONObject(y).getString("title"));
//                    }
//                    Business business = new Business(name, imageUrl, List< Category > categories, rating, Coordinates coordinates, List<String> transactions, String price, Location location, String phone, String displayPhone, Double distance) {
//
//                        business.add(business);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return restaurants;
//    }
//}
