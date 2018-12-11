package np.com.naxa.iset.utils.sectionmultiitemUtils;

import android.os.Parcel;
import android.os.Parcelable;

public class MultiItemSectionModel implements Parcelable {

    String image;
    String data_key;
    String data_value;

    public MultiItemSectionModel(String image, String data_key, String data_value) {
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

    protected MultiItemSectionModel(Parcel in) {
        this.image = in.readString();
        this.data_key = in.readString();
        this.data_value = in.readString();
    }

    public static final Parcelable.Creator<MultiItemSectionModel> CREATOR = new Parcelable.Creator<MultiItemSectionModel>() {
        @Override
        public MultiItemSectionModel createFromParcel(Parcel source) {
            return new MultiItemSectionModel(source);
        }

        @Override
        public MultiItemSectionModel[] newArray(int size) {
            return new MultiItemSectionModel[size];
        }
    };
}
