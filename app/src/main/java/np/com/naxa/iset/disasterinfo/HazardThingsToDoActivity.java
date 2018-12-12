package np.com.naxa.iset.disasterinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import np.com.naxa.iset.R;
import np.com.naxa.iset.profile.municipalityprofile.MunicipalityProfileActivity;
import np.com.naxa.iset.utils.sectionmultiitemUtils.DataServer;
import np.com.naxa.iset.utils.sectionmultiitemUtils.SectionMultipleItem;
import np.com.naxa.iset.utils.sectionmultiitemUtils.SectionMultipleItemAdapter;

public class HazardThingsToDoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_general)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btnBeforeHappens)
    Button btnBeforeHappens;
    @BindView(R.id.btnWhenHappens)
    Button btnWhenHappens;
    @BindView(R.id.btnAfterHappens)
    Button btnAfterHappens;

    HazardListModel hazardListModel;


    private List<SectionMultipleItem> mData;
    private static final String TAG = "HazardThingsToDo";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hazard_things_to_do);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        hazardListModel = intent.getParcelableExtra("OBJ");

        setupToolBar();

        // 1. create entityList which item data extend SectionMultiEntity
        mData = DataServer.getSectionMultiData();
        setupRecyclerView();
    }

    private void setupToolBar() {
        setSupportActionBar(toolbar);
        if (hazardListModel == null) {
            getSupportActionBar().setTitle("Things To Do");
        } else {
            getSupportActionBar().setTitle(hazardListModel.getTitle());
            btnBeforeHappens.setText("Before " + hazardListModel.getTitle());
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @OnClick({R.id.btnBeforeHappens, R.id.btnWhenHappens, R.id.btnAfterHappens})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBeforeHappens:
                // 1. create entityList which item data extend SectionMultiEntity
                mData = DataServer.getThingsToDoBefore();
                setupRecyclerView();
                break;
            case R.id.btnWhenHappens:
                // 1. create entityList which item data extend SectionMultiEntity
                mData = DataServer.getThingsToDoWhenHappens();
                setupRecyclerView();
                break;
            case R.id.btnAfterHappens:
                // 1. create entityList which item data extend SectionMultiEntity
                mData = DataServer.getThingsToDoAfter();
                setupRecyclerView();
                break;
        }
    }

    private void setupRecyclerView(){
        // create adapter which extend BaseSectionMultiItemQuickAdapter provide your headerResId
        Log.d(TAG, "setupRecyclerView: " +mData.size());
        SectionMultipleItemAdapter sectionAdapter = new SectionMultipleItemAdapter(R.layout.def_section_head, mData);
        sectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SectionMultipleItem item = (SectionMultipleItem) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.card_view:
                        if (item.getMultiItemSectionModel() != null) {
                            Toast.makeText(HazardThingsToDoActivity.this, item.getMultiItemSectionModel().getData_key(), Toast.LENGTH_LONG).show();
                        }
                        break;
                    default:
                        Toast.makeText(HazardThingsToDoActivity.this, "OnItemChildClickListener " + position, Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });
        recyclerView.setAdapter(sectionAdapter);
        Log.d(TAG, "setupRecyclerView: setAdapter ");
    }
}
