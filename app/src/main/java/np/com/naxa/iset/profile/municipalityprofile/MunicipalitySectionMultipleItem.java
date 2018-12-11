package np.com.naxa.iset.profile.municipalityprofile;


import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.SectionMultiEntity;

public class MunicipalitySectionMultipleItem extends SectionMultiEntity<MunicipalityProfileModel> implements MultiItemEntity {

    public static final int TEXT = 1;
    public static final int IMG = 2;
    public static final int IMG_TEXT = 3;
    private int itemType;
    private boolean isMore;
    private MunicipalityProfileModel municipalityProfileModel;

    public MunicipalitySectionMultipleItem(boolean isHeader, String header, boolean isMore) {
        super(isHeader, header);
        this.isMore = isMore;
    }

    public MunicipalitySectionMultipleItem(int itemType, MunicipalityProfileModel municipalityProfileModel) {
        super(municipalityProfileModel);
        this.municipalityProfileModel = municipalityProfileModel;
        this.itemType = itemType;
    }

    public MunicipalityProfileModel getMunicipalityProfileModel() {
        return municipalityProfileModel;
    }

    public void setMunicipalityProfileModel(MunicipalityProfileModel municipalityProfileModel) {
        this.municipalityProfileModel = municipalityProfileModel;
    }
    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }


    @Override
    public int getItemType() {
        return itemType;
    }


}
