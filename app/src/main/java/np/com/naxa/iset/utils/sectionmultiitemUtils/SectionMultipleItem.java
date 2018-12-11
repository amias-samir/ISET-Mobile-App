package np.com.naxa.iset.utils.sectionmultiitemUtils;


import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.SectionMultiEntity;

public class SectionMultipleItem extends SectionMultiEntity<MultiItemSectionModel> implements MultiItemEntity {

    public static final int TEXT = 1;
    public static final int IMG = 2;
    public static final int IMG_TEXT = 3;
    private int itemType;
    private boolean isMore;
    private MultiItemSectionModel multiItemSectionModel;

    public SectionMultipleItem(boolean isHeader, String header, boolean isMore) {
        super(isHeader, header);
        this.isMore = isMore;
    }

    public SectionMultipleItem(int itemType, MultiItemSectionModel multiItemSectionModel) {
        super(multiItemSectionModel);
        this.multiItemSectionModel = multiItemSectionModel;
        this.itemType = itemType;
    }

    public MultiItemSectionModel getMultiItemSectionModel() {
        return multiItemSectionModel;
    }

    public void setMultiItemSectionModel(MultiItemSectionModel multiItemSectionModel) {
        this.multiItemSectionModel = multiItemSectionModel;
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
