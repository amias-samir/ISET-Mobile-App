package np.com.naxa.iset.newhomepage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import np.com.naxa.iset.R;

public class SectionGridHomeActivity extends AppCompatActivity {

    protected static final String RECYCLER_VIEW_TYPE = "recycler_view_type";
    @BindView(R.id.btn_disaster_info)
    Button btnDisasterInfo;
    @BindView(R.id.btn_react_quickly)
    Button btnReactQuickly;
    @BindView(R.id.btn_disaster_news)
    Button btnDisasterNews;
    private RecyclerViewType recyclerViewType;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_grid_home);
        ButterKnife.bind(this);

        //get enum type passed from MainActivity
        recyclerViewType = RecyclerViewType.GRID;
//        setUpToolbarTitle();
        setUpRecyclerView();
        populateRecyclerView();
    }


    //setup recycler view
    private void setUpRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.sectioned_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    //populate recycler view
    private void populateRecyclerView() {
        String[] sectionHeader = {"REACT QUICKLY", "DISASTER INFORMATION"};
        ArrayList<SectionModel> sectionModelArrayList = new ArrayList<>();
        //for loop for sections
        for (int i = 1; i <= 2; i++) {
            ArrayList<String> itemArrayList = new ArrayList<>();
            //for loop for items
            for (int j = 1; j <= 4; j++) {
                itemArrayList.add("Item " + j);
            }

            //add the section and items to array list
            sectionModelArrayList.add(new SectionModel(sectionHeader[i - 1], itemArrayList));

        }

        SectionRecyclerViewAdapter adapter = new SectionRecyclerViewAdapter(this, recyclerViewType, sectionModelArrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.btn_disaster_info, R.id.btn_react_quickly, R.id.btn_disaster_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_disaster_info:
                recyclerView.scrollToPosition(1);
                break;
            case R.id.btn_react_quickly:
                recyclerView.scrollToPosition(0);
                break;
            case R.id.btn_disaster_news:
                break;
        }
    }
}
