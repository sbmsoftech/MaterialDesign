package info.androidhive.materialdesign.activity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import  com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import info.androidhive.materialdesign.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewCustomerFragment extends Fragment {
    private static final String REGISTER_URL="http://test.furnitia.in/api/Customer/RegisterCustomer";
    private Button buttonSubmit;
    private EditText editTextFullName;

    private EditText editTextAddLine1;

    private EditText editTextAddLine2;

    private EditText editTextCity;

    private EditText editTextState;

    private EditText editTextPinCode;

    private EditText editTextContactNumber;

    private ProgressDialog pd;
    private String mStatus;

    public NewCustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_new_customer, container, false);

        pd = new ProgressDialog(this.getActivity(), ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);


        buttonSubmit = (Button) rootView.findViewById(R.id.buttonSubmit);

        editTextFullName = (EditText) rootView.findViewById(R.id.editTextFullName);
        editTextAddLine1 = (EditText) rootView.findViewById(R.id.editTextAddLine1);
        editTextAddLine2 = (EditText) rootView.findViewById(R.id.editTextAddLine2);
        editTextCity = (EditText) rootView.findViewById(R.id.editTextCity);
        editTextState = (EditText) rootView.findViewById(R.id.editTextState);
        editTextPinCode = (EditText) rootView.findViewById(R.id.editTextPinCode);
        editTextContactNumber = (EditText) rootView.findViewById(R.id.editTextContactNumber);

        buttonSubmit.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something
                if(v == buttonSubmit){
                   RegisterUser();


                }
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    private void RegisterUser(){
        final String fullname = editTextFullName.getText().toString().trim();
        final String addline1 = editTextAddLine1.getText().toString().trim();
        final String addline2 = editTextAddLine2.getText().toString().trim();
        final String cityname = editTextCity.getText().toString().trim();
        final String statename = editTextState.getText().toString().trim();
        final String pincode = editTextPinCode.getText().toString().trim();
        final String contactnum = editTextContactNumber.getText().toString().trim();

        Map<String,String> params = new HashMap<String, String>();
        params.put("FullName",fullname);
        params.put("AddressLine1",addline1);
        params.put("AddressLine2",addline2);
        params.put("City",cityname);
        params.put("State",statename);
        params.put("Zipcode", pincode);
        params.put("ContactNumber", contactnum);

       // pd.show(this.getActivity(), "NEW REGISTRATION", "Submitting...");
       // pd.setCancelable(true);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, REGISTER_URL,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        Toast.makeText(getActivity(),"SUCCESS",Toast.LENGTH_LONG).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                       // pd.dismiss();
                    }
                });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

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
