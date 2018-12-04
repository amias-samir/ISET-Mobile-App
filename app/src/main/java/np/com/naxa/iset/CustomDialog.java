package np.com.naxa.iset;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomDialog extends Dialog {

    @BindView(R.id.btn_ok)
    Button btnOk;

    private Context context;

    public CustomDialog(@NonNull Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
    }


    @OnClick(R.id.btn_ok)
    public void onViewClicked() {
        super.dismiss();
    }
}
