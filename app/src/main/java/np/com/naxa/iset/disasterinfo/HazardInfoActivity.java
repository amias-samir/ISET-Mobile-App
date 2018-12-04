package np.com.naxa.iset.disasterinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.arlib.floatingsearchview.FloatingSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import np.com.naxa.iset.R;
import np.com.naxa.iset.detailspage.MarkerDetailedDisplayAdapter;

public class HazardInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_general)
    Toolbar toolbar;
    @BindView(R.id.floating_search_hazards)
    FloatingSearchView floatingSearchHazards;
    @BindView(R.id.hazardListrecycler)
    RecyclerView hazardListrecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hazard_info);
        ButterKnife.bind(this);

        setupToolBar();
        setupListRecycler();
    }

    private void setupToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hazard Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }


    private void setupListRecycler() {
        HazardListAdapter hazardListAdapter = new HazardListAdapter(R.layout.hazard_list_item_row_layout, null);
        hazardListrecycler.setLayoutManager(new LinearLayoutManager(this));
        hazardListrecycler.setAdapter(hazardListAdapter);

        loadDataToList();
    }

    private void loadDataToList(){

        ArrayList<HazardListModel> hazardListModelArrayList = new ArrayList<HazardListModel>();

        hazardListModelArrayList.add(new HazardListModel("Earthquake"));
        hazardListModelArrayList.add(new HazardListModel("Landslides"));
        hazardListModelArrayList.add(new HazardListModel("Sinkholes"));
        hazardListModelArrayList.add(new HazardListModel("Floods"));
        hazardListModelArrayList.add(new HazardListModel("Heat Waves"));
        hazardListModelArrayList.add(new HazardListModel("Thunderstorms"));
        hazardListModelArrayList.add(new HazardListModel("Earthquake"));
        hazardListModelArrayList.add(new HazardListModel("Landslides"));
        hazardListModelArrayList.add(new HazardListModel("Sinkholes"));
        hazardListModelArrayList.add(new HazardListModel("Floods"));
        hazardListModelArrayList.add(new HazardListModel("Heat Waves"));
        hazardListModelArrayList.add(new HazardListModel("Thunderstorms"));
        ((HazardListAdapter) hazardListrecycler.getAdapter()).replaceData(hazardListModelArrayList);


    }
}
