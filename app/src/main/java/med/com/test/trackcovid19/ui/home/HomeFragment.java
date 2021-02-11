package med.com.test.trackcovid19.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import med.com.test.trackcovid19.R;

public class HomeFragment extends Fragment {

    private SwipeRefreshLayout Srl;
    private ImageView CallSoSImageBare;
    private TextView TvTotalCOonfirmed, TvTotalDeaths, TvTotalRecovrer, TvNewConfirmed;
    private ProgressBar progressBar;
    private ImageView imganime;
    Animation frombuttom;
    ViewFlipper vf;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // callView
        Srl = root.findViewById(R.id.refrech_layout);
        TvTotalCOonfirmed = root.findViewById(R.id.TvTotalCofirmed);
        TvTotalDeaths = root.findViewById(R.id.TvTotalDeath);
        TvTotalRecovrer = root.findViewById(R.id.TvTotalRecovred);
        progressBar = root.findViewById(R.id.progress_Circular_Home);
        TvNewConfirmed = root.findViewById(R.id.TvNewConfirmed);
        CallSoSImageBare = root.findViewById(R.id.imageView15);


        //refreshlayouttest

        Srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //CallVolley
                getData();
                Srl.setRefreshing(false);


            }
        });


        //CallVolley
        getData();



        //call SoS
        CallSoSImageBare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CallSoS = new Intent(Intent.ACTION_DIAL);
                CallSoS.setData(Uri.parse("tel: 0522989898"));
                startActivity(CallSoS);

            }
        });


        return root;
    }


    public void flipimage(int image) {

        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);
        vf.addView(imageView);
        vf.setFlipInterval(4000);
        vf.setAutoStart(true);


        //put animation

        vf.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        vf.setOutAnimation(getActivity(), android.R.anim.slide_out_right);

    }


    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://corona.lmao.ninja/v2/all";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    TvTotalCOonfirmed.setText(jsonObject.getString("cases"));
                    TvTotalDeaths.setText(jsonObject.getString("deaths"));
                    TvTotalRecovrer.setText(jsonObject.getString("recovered"));
                    TvNewConfirmed.setText(jsonObject.getString("todayCases"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.d("error response", error.toString());

            }
        });

        queue.add(stringRequest);


    }
}
