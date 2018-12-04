package np.com.naxa.iset.disasterinfo;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import np.com.naxa.iset.R;
import np.com.naxa.iset.detailspage.MarkerDetailsKeyValue;

public class HazardListAdapter extends BaseQuickAdapter<HazardListModel, BaseViewHolder> {

    public HazardListAdapter(int layoutResId, @Nullable List<HazardListModel> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, HazardListModel item) {
        LinearLayout relativeLayout = helper.getView(R.id.hazard_list_item_row_layout);
        helper.setText(R.id.tv_hazard_list_title,item.getTitle());
        helper.setText(R.id.tv_hazard_list_title,item.getTitle());
    }
}
