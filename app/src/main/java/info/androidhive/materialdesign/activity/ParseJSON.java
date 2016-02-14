package info.androidhive.materialdesign.activity;


import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Belal on 9/22/2015.
 */
public class ParseJSON {

    public static String[] FullNames;


    public static final String JSON_ARRAY = "ReturnData";
    public static final String KEY_NAME = "FullName";


    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);



            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                FullNames[i] = jo.getString(KEY_NAME);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}