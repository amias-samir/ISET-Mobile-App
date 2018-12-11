package np.com.naxa.iset.profile.municipalityprofile;

import android.os.Parcel;
import android.os.Parcelable;

public class MunicipalityProfileModel implements Parcelable {

    String image;
    String data_key;
    String data_value;

    public MunicipalityProfileModel(String image, String data_key, String data_value) {
        this.image = image;
        this.data_key = data_key;
        this.data_value = data_value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getData_key() {
        return data_key;
    }

    public void setData_key(String data_key) {
        this.data_key = data_key;
    }

    public String getData_value() {
        return data_value;
    }

    public void setData_value(String data_value) {
        this.data_value = data_value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image);
        dest.writeString(this.data_key);
        dest.writeString(this.data_value);
    }

    protected MunicipalityProfileModel(Parcel in) {
        this.image = in.readString();
        this.data_key = in.readString();
        this.data_value = in.readString();
    }

    public static final Parcelable.Creator<MunicipalityProfileModel> CREATOR = new Parcelable.Creator<MunicipalityProfileModel>() {
        @Override
        public MunicipalityProfileModel createFromParcel(Parcel source) {
            return new MunicipalityProfileModel(source);
        }

        @Override
        public MunicipalityProfileModel[] newArray(int size) {
            return new MunicipalityProfileModel[size];
        }
    };
}
