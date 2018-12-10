package np.com.naxa.iset.disasterinfo;

import android.os.Parcel;
import android.os.Parcelable;

public class HazardListModel implements Parcelable {
    String title;

    public HazardListModel(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
    }

    protected HazardListModel(Parcel in) {
        this.title = in.readString();
    }

    public static final Parcelable.Creator<HazardListModel> CREATOR = new Parcelable.Creator<HazardListModel>() {
        @Override
        public HazardListModel createFromParcel(Parcel source) {
            return new HazardListModel(source);
        }

        @Override
        public HazardListModel[] newArray(int size) {
            return new HazardListModel[size];
        }
    };
}
