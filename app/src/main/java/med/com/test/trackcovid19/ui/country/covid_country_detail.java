package med.com.test.trackcovid19.ui.country;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkEventListener;

import med.com.test.trackcovid19.MainActivity;
import med.com.test.trackcovid19.R;
import med.com.test.trackcovid19.ui.home.HomeFragment;

public class covid_country_detail extends AppCompatActivity {
    private SwipeRefreshLayout Srl;

    TextView TvCountryName, TvTotalConfiremed, TvTodayConfirmed, TvTotalDeaths, TvTodayDeaths, TvTotalRecovered, TvTodayRecovred;
    SparkButton sparkButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_country_detail);
        Srl = (SwipeRefreshLayout)findViewById(R.id.refrech_layout);
        TvCountryName=(TextView)findViewById(R.id.tvCountryName);
        TvTodayConfirmed = (TextView) findViewById(R.id.TvTodayConfirmedDetails);
        TvTotalConfiremed = (TextView) findViewById(R.id.TvTotalCofirmedDetails);
        TvTotalDeaths = (TextView) findViewById(R.id.TvTotalDeathDetail);
        TvTodayDeaths = (TextView) findViewById(R.id.TvTodayDeathsDetails);
        TvTotalRecovered = (TextView) findViewById(R.id.TvTotalRecovredDetail);
        TvTodayRecovred = (TextView) findViewById(R.id.TvTodayRecovredDeatail);
        //sparkbutton
        sparkButton=(SparkButton)findViewById(R.id.spark_button);


        //Call Covid Countrie
        CovidCountry covidCountry = getIntent().getParcelableExtra("EXTRA_COVID");

        // set text view
        TvCountryName.setText(covidCountry.getMcovidcountry());
        TvTotalConfiremed.setText(covidCountry.getmCases());
        TvTodayConfirmed.setText(covidCountry.getmTodyCases());
        TvTotalDeaths.setText(covidCountry.getmDeath());
        TvTodayDeaths.setText(covidCountry.getmToDayDeath());
        TvTotalRecovered.setText(covidCountry.getmRecovred());
        TvTodayRecovred.setText(covidCountry.getmTodayrecovred());


        //refreshlayouttest

        Srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Srl.setRefreshing(false);


            }
        });


        sparkButton.setEventListener(new SparkEventListener(){
            @Override
            public void onEvent(ImageView button, boolean buttonState) {
                if (buttonState) {
                    // Button is active

                } else {
                    // Button is inactive
                }
            }

            @Override
            public void onEventAnimationEnd(ImageView button, boolean buttonState) {
                sparkButton.setChecked(false);

            }

            @Override
            public void onEventAnimationStart(ImageView button, boolean buttonState) {

            }
        });


    }



}
