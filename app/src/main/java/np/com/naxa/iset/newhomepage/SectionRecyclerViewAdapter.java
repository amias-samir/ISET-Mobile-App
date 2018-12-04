package np.com.naxa.iset.newhomepage;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import np.com.naxa.iset.R;

/**
 * Created by samir on 01/12/18..
 */

public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<SectionRecyclerViewAdapter.SectionViewHolder> {


    class SectionViewHolder extends RecyclerView.ViewHolder {
        private TextView sectionLabel, showAllButton;
        private ImageView dottedViewLine, dottedViewHead;
        private RecyclerView itemRecyclerView;

        public SectionViewHolder(View itemView) {
            super(itemView);
            dottedViewLine = (ImageView) itemView.findViewById(R.id.dottedView);
            dottedViewHead = (ImageView) itemView.findViewById(R.id.dottedLineHead);
            sectionLabel = (TextView) itemView.findViewById(R.id.section_label);
            showAllButton = (TextView) itemView.findViewById(R.id.section_show_all_button);
            itemRecyclerView = (RecyclerView) itemView.findViewById(R.id.item_recycler_view);
        }
    }

    private Context context;
    private RecyclerViewType recyclerViewType;
    private ArrayList<SectionModel> sectionModelArrayList;

    public SectionRecyclerViewAdapter(Context context, RecyclerViewType recyclerViewType, ArrayList<SectionModel> sectionModelArrayList) {
        this.context = context;
        this.recyclerViewType = recyclerViewType;
        this.sectionModelArrayList = sectionModelArrayList;
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_custom_row_layout, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        final SectionModel sectionModel =
                sectionModelArrayList.get(position);


        if(position==1){
                holder.dottedViewLine.setBackground(holder.dottedViewLine.getContext().getResources().getDrawable(R.drawable.dotted_line_green));
                holder.showAllButton.setVisibility(View.GONE);
            holder.dottedViewHead.setBackground(holder.dottedViewHead.getContext().getResources().getDrawable(R.drawable.dotted_line_green_head));
        }

        holder.sectionLabel.setText(sectionModel.getSectionLabel());

        //recycler view for items
        holder.itemRecyclerView.setHasFixedSize(true);
        holder.itemRecyclerView.setNestedScrollingEnabled(false);

        /* set layout manager on basis of recyclerview enum type */
        switch (recyclerViewType) {
            case LINEAR_VERTICAL:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                holder.itemRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            case LINEAR_HORIZONTAL:
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                holder.itemRecyclerView.setLayoutManager(linearLayoutManager1);
                break;
            case GRID:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
                holder.itemRecyclerView.setLayoutManager(gridLayoutManager);
                break;
        }
        ItemRecyclerViewAdapter adapter = new ItemRecyclerViewAdapter(context, sectionModel.getItemArrayList());
        holder.itemRecyclerView.setAdapter(adapter);



        //show toast on click of show all button
        holder.showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked on Show All of : " + sectionModel.getSectionLabel(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return sectionModelArrayList.size();
    }


}
