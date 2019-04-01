package a1door.listviewrecyclertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_CREATOR = "creatorName";
    public static final String EXTRA_LIKES = "likeCount";
    public static final String EXTRA_DOWNLOADS = "downloadCount";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Item> myDataset;
    private RequestQueue mRequestQueue;
    private EditText editText;
    private ArrayList<Item> listToshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.textSearch);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        myDataset = new ArrayList<>();
        listToshow = new ArrayList<>();
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //set request
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();

        //search line listener
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //if(charSequence.toString().equals("")){
                    //reset listview
                    initListToShow();
                //}
                //else{
                    //Search item
                    search(charSequence.toString());
                //}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void parseJSON() {
        //Enter link to JSON here
        String url = "https://pixabay.com/api/?key=12056523-a0e86c3cbbaacb292a8844d22&q=dog&image_type=photo&pretty=true";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray =response.getJSONArray("hits");
                            for(int i = 0; i < jsonArray.length();i++){
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String creatorName  = hit.getString("user");
                                String imageUrl  = hit.getString("webformatURL");
                                int likeCount = hit.getInt("likes");
                                int downloadsCount = hit.getInt("downloads");
                                myDataset.add(new Item(imageUrl,creatorName,likeCount,downloadsCount));
                            }
                            listToshow.addAll(myDataset);
                            mAdapter = new MyAdapter(listToshow,MainActivity.this);
                            recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    private void search(String name) {
        for(Item item:myDataset){
            if(!item.getmCreator().toLowerCase().contains(name.toLowerCase())){
                listToshow.remove(item);
            }
        }

        mAdapter.notifyDataSetChanged();

    }
    private void initListToShow() {
        listToshow.clear();
        listToshow.addAll(myDataset);

        mAdapter = new MyAdapter(listToshow,MainActivity.this);
        recyclerView.setAdapter(mAdapter);
    }
}
