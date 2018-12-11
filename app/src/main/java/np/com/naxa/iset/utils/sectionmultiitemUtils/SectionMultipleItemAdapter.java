package np.com.naxa.iset.utils.sectionmultiitemUtils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import np.com.naxa.iset.R;

public class SectionMultipleItemAdapter extends BaseSectionMultiItemQuickAdapter<SectionMultipleItem, BaseViewHolder> {
    /**
     * init SectionMultipleItemAdapter
     * 1. add your header resource layout
     * 2. add some kind of items
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SectionMultipleItemAdapter(int sectionHeadResId, List data) {
        super(sectionHeadResId, data);

        addItemType(SectionMultipleItem.TEXT, R.layout.item_text_view);
        addItemType(SectionMultipleItem.IMG, R.layout.item_image_view);
        addItemType(SectionMultipleItem.IMG_TEXT, R.layout.item_img_text_view);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final SectionMultipleItem item) {
        // deal with header viewHolder
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionMultipleItem item) {
        // deal with multiple type items viewHolder
        helper.addOnClickListener(R.id.card_view);
        switch (helper.getItemViewType()) {
            case SectionMultipleItem.TEXT:
                helper.setText(R.id.tv, item.getMultiItemSectionModel().getData_key());
                helper.setText(R.id.tv1, item.getMultiItemSectionModel().getData_value());
                break;
            case SectionMultipleItem.IMG:

                ImageView imageView = helper.getView(R.id.iv);
                Glide
                        .with(imageView.getContext())
                        .load(item.getMultiItemSectionModel().getImage())
                        .fitCenter()
                        .into(imageView);

                break;
        }
    }
}
