package np.com.naxa.iset.utils.imageutils;

import android.content.Context;

public class LoadImageUtils {

    public static int getImageFromDrawable(Context context, String imageName){
        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }

}
