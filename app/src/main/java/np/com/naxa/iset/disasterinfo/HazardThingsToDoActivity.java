package np.com.naxa.iset.disasterinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import np.com.naxa.iset.R;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hazard_things_to_do);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        hazardListModel = intent.getParcelableExtra("OBJ");

        setupToolBar();
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
                break;
            case R.id.btnWhenHappens:
                break;
            case R.id.btnAfterHappens:
                break;
        }
    }
}
