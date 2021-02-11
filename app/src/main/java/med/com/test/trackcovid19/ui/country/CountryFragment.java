package med.com.test.trackcovid19.ui.country;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import med.com.test.trackcovid19.R;

public class CountryFragment extends Fragment {


    private RecyclerView rvCovidCountry;
    private ProgressBar progressBar;
    private ArrayList<CovidCountry> covidCountries;
    private static final String TAG = CountryFragment.class.getSimpleName();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_country, container, false);
        //CallView
        rvCovidCountry = root.findViewById(R.id.rvCovidCountry);
        progressBar = root.findViewById(R.id.progress_circular_country);

        rvCovidCountry.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(rvCovidCountry.getContext(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.line_divider));
        rvCovidCountry.addItemDecoration(dividerItemDecoration);


        //CallMethod Volley
        getDataFromServer();


        return root;
    }


    private void showRecyclerView() {
        CovidCountryAdapter covidCountryAdapter = new CovidCountryAdapter(covidCountries);
        rvCovidCountry.setAdapter(covidCountryAdapter);

        ItemClickSupport.addTo(rvCovidCountry).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showselectetcovidcountry(covidCountries.get(position));
            }
        });
    }


    private void showselectetcovidcountry(CovidCountry covidCountry) {
        Intent tocoviddetail = new Intent(getActivity(), covid_country_detail.class);
        tocoviddetail.putExtra("EXTRA_COVID", covidCountry);
        startActivity(tocoviddetail);
    }

    private void getDataFromServer() {
         String url ="https://corona.lmao.ninja/v2/countries";


        covidCountries = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse" + response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            covidCountries.add(new CovidCountry(data.getString("country")
                                    , data.getString("cases")
                                    , data.getString("todayCases")
                                    , data.getString("deaths")
                                    , data.getString("todayDeaths")
                                    , data.getString("recovered")
                                    , data.getString("tests")));
                        }
                        showRecyclerView();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, "onResponse" + error);

            }
        });
        Volley.newRequestQueue(getActivity()).add(stringRequest);


    }
}
