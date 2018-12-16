package np.com.naxa.iset.newhomepage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import np.com.naxa.iset.R;
import np.com.naxa.iset.activity.EmergencyActivity;
import np.com.naxa.iset.activity.MyCircleProfileActivity;
import np.com.naxa.iset.activity.ReportActivity;
import np.com.naxa.iset.disasterinfo.HazardInfoActivity;
import np.com.naxa.iset.drr_dictionary.data_glossary.GlossaryListActivity;
import np.com.naxa.iset.home.HomeActivity;
import np.com.naxa.iset.quiz.QuizHomeActivity;
import np.com.naxa.iset.quiz.QuizTestActivity;
import np.com.naxa.iset.utils.DialogFactory;
import np.com.naxa.iset.youtubeplayer.YoutubeVideoListActivity;

/**
 * Created by samir on 01/12/18.
 */

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemViewHolder> {

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView itemLabel;
        private ImageView itemImage;
        private CardView cardView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemLabel = (TextView) itemView.findViewById(R.id.item_label);
            itemImage = (ImageView) itemView.findViewById(R.id.item_imageView);
            cardView = (CardView) itemView.findViewById(R.id.item_card);
        }
    }

    private Context context;
    private ArrayList<String> arrayList;

    public ItemRecyclerViewAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom_row_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.itemLabel.setText(arrayList.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, arrayList.get(position) + " Clicked", Toast.LENGTH_SHORT).show();
                startNewActivity(arrayList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    private void startNewActivity(String gridItemTitle) {
        switch (gridItemTitle) {
            case "FIND OPEN SPACE":
                break;

            case "ASK FOR HELP":
                DialogFactory.createCustomDialog(context,
                        "If you want to submit a detailed report, fill the following form.",
                        new DialogFactory.CustomDialogListener() {
                            @Override
                            public void onClick() {
                                context.startActivity(new Intent(context, ReportActivity.class));
                            }
                        }).show();
                break;
            case "Report":
                context.startActivity(new Intent(context, ReportActivity.class));
                break;

            case "NOTIFY OTHERS":
                context.startActivity(new Intent(context, EmergencyActivity.class));
                break;

            case "My Circle":
                context.startActivity(new Intent(context, MyCircleProfileActivity.class));
                break;

            case "HAZARD INFO":
                context.startActivity(new Intent(context, HazardInfoActivity.class));
                break;

            case "DRR QUIZ":
                context.startActivity(new Intent(context, QuizHomeActivity.class));
                break;

            case "DRR Dictionary":
                context.startActivity(new Intent(context, GlossaryListActivity.class));
                break;

            case "MAP":
                DialogFactory.createBaseLayerDialog(context, new DialogFactory.CustomBaseLayerDialogListner() {
                    @Override
                    public void onStreetClick() {

                    }

                    @Override
                    public void onSatelliteClick() {

                    }

                    @Override
                    public void onOpenStreetClick() {

                    }

                    @Override
                    public void onMetropolitianClick() {

                    }

                    @Override
                    public void onWardClick() {

                    }
                }).show();
//                context.startActivity(new Intent(context, HomeActivity.class));
                break;
        }

    }


}
