package np.com.naxa.iset.profile.municipalityprofile;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import np.com.naxa.iset.R;

public class MunicipalitySectionMultipleItemAdapter extends BaseSectionMultiItemQuickAdapter<MunicipalitySectionMultipleItem, BaseViewHolder> {
    /**
     * init SectionMultipleItemAdapter
     * 1. add your header resource layout
     * 2. add some kind of items
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public MunicipalitySectionMultipleItemAdapter(int sectionHeadResId, List data) {
        super(sectionHeadResId, data);

        addItemType(MunicipalitySectionMultipleItem.TEXT, R.layout.item_text_view);
        addItemType(MunicipalitySectionMultipleItem.IMG, R.layout.item_image_view);
        addItemType(MunicipalitySectionMultipleItem.IMG_TEXT, R.layout.item_img_text_view);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final MunicipalitySectionMultipleItem item) {
        // deal with header viewHolder
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
    }

    @Override
    protected void convert(BaseViewHolder helper, MunicipalitySectionMultipleItem item) {
        // deal with multiple type items viewHolder
        helper.addOnClickListener(R.id.card_view);
        switch (helper.getItemViewType()) {
            case MunicipalitySectionMultipleItem.TEXT:
                helper.setText(R.id.tv, item.getMunicipalityProfileModel().getData_key());
                helper.setText(R.id.tv1, item.getMunicipalityProfileModel().getData_value());
                break;
            case MunicipalitySectionMultipleItem.IMG:

                ImageView imageView = helper.getView(R.id.iv);
                Glide
                        .with(imageView.getContext())
                        .load(item.getMunicipalityProfileModel().getImage())
                        .fitCenter()
                        .into(imageView);

                break;
        }
    }
}
