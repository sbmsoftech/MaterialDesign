package info.androidhive.materialdesign.activity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import info.androidhive.materialdesign.R;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import  com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerListFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items;
    public CustomerListFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_customer_list, container, false);

       // GetCustomerList();



         listView=(ListView)rootView.findViewById(R.id.listv);
        items=new ArrayList<String>();
        adapter=new ArrayAdapter(getActivity(), R.layout.item_layout,R.id.txtName,items);
        listView.setAdapter(adapter);
        GetCustomerList();
        return rootView;
    }

    public void GetCustomerList(){


        //  Create json array request
        JsonObjectRequest  jsonObjectRequest=new JsonObjectRequest (Request.Method.GET,"http://test.furnitia.in/api/Customer/ListAllCustomer",
                new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject response){

                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("ReturnData");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int i=0;i<jsonArray.length();i++){
                    try {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        items.add(jsonObject.getString("FullName"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Error", "Unable to parse json array");
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // add json array request to the request queue

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }


    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        CustomList cl = new CustomList(getActivity(), ParseJSON.FullNames);
        listView.setAdapter(cl);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
