package np.com.naxa.iset.utils;

/**
 * Created by Nishon Tandukar on 16 Jun 2017 .
 *
 * @email nishon.tan@gmail.com
 */


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;

import java.util.ArrayList;

import np.com.naxa.iset.R;
import np.com.naxa.iset.activity.MyCircleProfileActivity;
import np.com.naxa.iset.disasterinfo.HazardListAdapter;
import np.com.naxa.iset.mycircle.ContactModel;
import np.com.naxa.iset.mycircle.MyCircleContactListAdapter;


public final class DialogFactory {

    private ProgressDialog progressDialog;

    public static Dialog createSimpleOkErrorDialog(Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }


    public static Dialog createGenericErrorDialog(Context context, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.RiseUpDialog)
                .setTitle(context.getString(R.string.dialog_error_title))
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }

    public static Dialog createDataSyncErrorDialog(Context context, String message, String code) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.RiseUpDialog)
                .setTitle(context.getString(R.string.dialog_error_title_sync_failed, code))
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }


    public static Dialog createMessageDialog(final Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.RiseUpDialog)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }


    public static AlertDialog.Builder createActionDialog(final Context context, String title, String message) {
        return new AlertDialog.Builder(context, R.style.RiseUpDialog)
                .setTitle(title).setCancelable(false)
                .setMessage(message);
    }

    public static Dialog createGenericErrorDialog(Context context, @StringRes int messageResource) {
        return createGenericErrorDialog(context, context.getString(messageResource));
    }

    public static Dialog createDataSyncErrorDialog(Context context, String responseCode, @StringRes int messageResource) {
        return createDataSyncErrorDialog(context, context.getString(messageResource), responseCode);
    }

    public static ProgressDialog createProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context, R.style.RiseUpDialog);
        progressDialog.setMessage(message);
        return progressDialog;
    }

    public static ProgressDialog createProgressBarDialog(Context context, String title, String message) {

        final ProgressDialog progress = new ProgressDialog(context, R.style.RiseUpDialog);

        DialogInterface.OnClickListener buttonListerns =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                progress.dismiss();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };


        progress.setMessage(message);
        progress.setTitle(title);

        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progress.setButton(DialogInterface.BUTTON_POSITIVE, context.getString(R.string.dialog_action_hide), buttonListerns);
        progress.setIndeterminate(false);
        progress.setProgress(0);
        progress.setCancelable(false);


        return progress;
    }

    public static ProgressDialog createProgressDialog(Context context,
                                                      @StringRes int messageResource) {
        return createProgressDialog(context, context.getString(messageResource));
    }

    public static Dialog createCustomDialog(@NonNull Context context, @NonNull String message, @NonNull CustomDialogListener listener) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog);

        TextView text = (TextView) dialog.findViewById(R.id.tv_message);
        text.setText(message);

        Button dialogButton = (Button) dialog.findViewById(R.id.btn_ok);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.onClick();
            }
        });
        return dialog;
    }

    public interface CustomDialogListener {
        void onClick();
    }


    public static Dialog createBaseLayerDialog(@NonNull Context context, @NonNull CustomBaseLayerDialogListner listner){

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.select_base_layer_custom_dialog_layout);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;


        Button dialogButton = (Button) dialog.findViewById(R.id.btn_closeDialog);

        Switch street = (Switch) dialog.findViewById(R.id.switchStreetView);
        Switch satellite = (Switch) dialog.findViewById(R.id.switchSatelliteView);
        Switch openStreet = (Switch) dialog.findViewById(R.id.switchOpenStreetView);
        Switch municipality = (Switch) dialog.findViewById(R.id.switchMetropolitianBoundary);
        Switch ward = (Switch) dialog.findViewById(R.id.switchWardBoundary);


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        street.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                if(isChecked == true){
                    satellite.setChecked(false);
                    openStreet.setChecked(false);
                    listner.onStreetClick();
                }
            }
        });

        satellite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                if(isChecked == true){
                    street.setChecked(false);
                    openStreet.setChecked(false);
                    listner.onSatelliteClick();

                }
            }
        });

        openStreet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                if(isChecked == true){
                    street.setChecked(false);
                    satellite.setChecked(false);
                    listner.onOpenStreetClick();

                }
            }
        });

        municipality.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                if(isChecked == true){
                    ward.setChecked(false);
                    listner.onMetropolitanClick();

                }
            }
        });

        ward.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                if(isChecked == true){
                    municipality.setChecked(false);
                    listner.onWardClick();
                }
            }
        });




        dialog.getWindow().setAttributes(lp);
        return dialog;
    }


    public interface CustomBaseLayerDialogListner {
        void onStreetClick();
        void onSatelliteClick();
        void onOpenStreetClick();
        void onMetropolitanClick();
        void onWardClick();
    }



    public static Dialog createContactListDialog(@NonNull Context context, ArrayList<ContactModel> contactModelArrayList){


        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_my_circle_contact_list_layout);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        FloatingSearchView floatingSearchView = (FloatingSearchView) dialog.findViewById(R.id.floating_search_contacts_dialog);
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recyclerViewContactsDialog);
        Button dialogButton = (Button) dialog.findViewById(R.id.btnClose);



        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        MyCircleContactListAdapter myCircleContactListAdapter = new MyCircleContactListAdapter(R.layout.contact_dialog_row_item_layout, contactModelArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(myCircleContactListAdapter);

//        ((MyCircleContactListAdapter) recyclerView.getAdapter()).replaceData(contactModelArrayList);


        dialog.getWindow().setAttributes(lp);
        return dialog;
    }


}
