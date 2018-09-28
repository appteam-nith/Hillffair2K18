package appteam.nith.hillffair2k18.utils;

import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

/**
 * Created by ThisIsNSH on 9/28/2018.
 */

public interface RecyclerViewClickListener {

    void onClick(View view, String imageId, String roll, boolean isliked, int likes, TextView like_count, LottieAnimationView like);
}