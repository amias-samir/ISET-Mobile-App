package np.com.naxa.iset.quiz;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Random;

import np.com.naxa.iset.R;
import np.com.naxa.iset.event.EmergenctContactCallEvent;

/**
 * Created by samir on 01/12/18.
 */

public class ItemRecyclerViewQuizAdapter extends RecyclerView.Adapter<ItemRecyclerViewQuizAdapter.ItemViewHolder> {

    String TAG = "EmergencyNoAdapter";

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView itemLabel, quiz_percentage;
        ImageButton item_bg ;
        private CardView cardView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemLabel = (TextView) itemView.findViewById(R.id.item_label);
            item_bg = (ImageButton) itemView.findViewById(R.id.item_bg);
            quiz_percentage = (TextView) itemView.findViewById(R.id.quiz_percentage);
            cardView = (CardView) itemView.findViewById(R.id.item_card);
        }
    }

    private Context context;
    private ArrayList<String> arrayList;

    public ItemRecyclerViewQuizAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom_row_quiz_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

        holder.itemLabel.setText(arrayList.get(position));
        holder.itemLabel.setTextColor(color);

//        DrawableCompat.setTint(holder.item_bg.getDrawable(), color);

//        Drawable icon = ContextCompat.getDrawable(context, R.drawable.circular_non_solid_vector).mutate();
//        TypedValue typedValue = new TypedValue();
//       context.getTheme().resolveAttribute(color, typedValue, true);
//        icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);
//        holder.item_bg.setBackground(icon);

//        Drawable mDrawable = context.getResources().getDrawable(R.drawable.circular_non_solid_vector).mutate();
//        mDrawable.setColorFilter(new
//                PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
//        holder.item_bg.setBackgroundDrawable(mDrawable);

        Drawable mDrawable1 = context.getResources().getDrawable(R.drawable.button_rounded_purple).mutate();
        mDrawable1.setColorFilter(new
                PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
       holder.quiz_percentage.setBackground(mDrawable1);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, arrayList.get(position) + " Clicked", Toast.LENGTH_SHORT).show();
//                startNewActivity(arrayList.get(position));
//                EventBus.getDefault().post(new EmergenctContactCallEvent.ContactItemClick(arrayList.get(position)));

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
