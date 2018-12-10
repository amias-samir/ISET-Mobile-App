package np.com.naxa.iset.profile.municipalityprofile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import np.com.naxa.iset.R;
import np.com.naxa.iset.activity.ReportActivity;

public class MunicipalityProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_general)
    Toolbar toolbarGeneral;
    @BindView(R.id.selectWardLBL)
    TextView selectWardLBL;
    @BindView(R.id.spn_ward_no)
    Spinner spnWardNo;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality_profile);
        ButterKnife.bind(this);

        setupToolBar();
        setUpSpinner();
    }


    private void setupToolBar() {
        setSupportActionBar(toolbarGeneral);
        getSupportActionBar().setTitle("Municipality Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setUpSpinner() {
        ArrayAdapter<String> wardAdapter = new ArrayAdapter<String>(MunicipalityProfileActivity.this,
                R.layout.item_spinner_text_black, getResources().getStringArray(R.array.ward_no));
        wardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnWardNo.setAdapter(wardAdapter);

    }
}
