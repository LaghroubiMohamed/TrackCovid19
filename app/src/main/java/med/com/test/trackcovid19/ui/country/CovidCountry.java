package med.com.test.trackcovid19.ui.country;

import android.os.Parcel;
import android.os.Parcelable;

public class CovidCountry implements Parcelable {

      private String mcovidcountry , mCases , mTodyCases , mDeath , mToDayDeath ,mRecovred ,mTodayrecovred ;

    public CovidCountry(String mcovidcountry, String mCases, String mTodyCases, String mDeath, String mToDayDeath, String mRecovred, String mTodayrecovred) {
        this.mcovidcountry = mcovidcountry;
        this.mCases = mCases;
        this.mTodyCases = mTodyCases;
        this.mDeath = mDeath;
        this.mToDayDeath = mToDayDeath;
        this.mRecovred = mRecovred;
        this.mTodayrecovred = mTodayrecovred;

    }

    public String getMcovidcountry() {
        return mcovidcountry;
    }

    public String getmCases() {
        return mCases;
    }

    public String getmTodyCases() {
        return mTodyCases;
    }

    public String getmDeath() {
        return mDeath;
    }

    public String getmToDayDeath() {
        return mToDayDeath;
    }

    public String getmRecovred() {
        return mRecovred;
    }

    public String getmTodayrecovred() {
        return mTodayrecovred;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mcovidcountry);
        dest.writeString(this.mCases);
        dest.writeString(this.mTodyCases);
        dest.writeString(this.mDeath);
        dest.writeString(this.mToDayDeath);
        dest.writeString(this.mRecovred);
        dest.writeString(this.mTodayrecovred);

    }

    protected CovidCountry(Parcel in) {
        this.mcovidcountry = in.readString();
        this.mCases = in.readString();
        this.mTodyCases = in.readString();
        this.mDeath = in.readString();
        this.mToDayDeath = in.readString();
        this.mRecovred = in.readString();
        this.mTodayrecovred = in.readString();

    }

    public static final Parcelable.Creator<CovidCountry> CREATOR = new Parcelable.Creator<CovidCountry>() {
        @Override
        public CovidCountry createFromParcel(Parcel source) {
            return new CovidCountry(source);
        }

        @Override
        public CovidCountry[] newArray(int size) {
            return new CovidCountry[size];
        }
    };
}
