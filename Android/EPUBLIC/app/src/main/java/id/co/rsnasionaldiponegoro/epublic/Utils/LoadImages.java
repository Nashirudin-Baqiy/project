package id.co.rsnasionaldiponegoro.epublic.Utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import id.co.rsnasionaldiponegoro.epublic.R;

/**
 * Created by xsanz on 9/23/2018.
 */

public final class LoadImages {
    public static void LoadDataImages(Context context, String url, int load, ImageView imgview, CircleImageView circle, String condition) {
        // get drawable context.getResources().getDrawable(int)
        if (imgview == null) {
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(context.getResources().getDrawable(load))
                    .error(R.drawable.logo_main)
                    .fitCenter()
                    .dontAnimate()
                    .into(circle);
        } else {
            if (condition.equals("FULL")) {
                Glide.with(context)
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(context.getResources().getDrawable(load))
                        .error(R.drawable.logo_main)
                        .dontAnimate()
                        .into(imgview);
            }else if (condition.equals("CENTERCROP")) {
                Glide.with(context)
                        .load(url)
                        .asBitmap()
                        .dontAnimate()
                        .centerCrop()
                        .placeholder(R.drawable.ic_profile)
                        .error(R.drawable.ic_profile)
                        .into(imgview);
            } else {
                Glide.with(context)
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(context.getResources().getDrawable(load))
                        .error(R.drawable.logo_main)
                        .fitCenter()
                        .dontAnimate()
                        .into(imgview);
            }
        }
    }

    public static void LoadDataImagesUri(Context context, Uri url, int load, ImageView imageView, CircleImageView circleImageView){

        if(imageView != null){

            Glide.with(context)
                    .load(new File(url.getPath()))
                    .placeholder(context.getResources().getDrawable(load))
                    .error(R.drawable.logo_rsnd)
                    .fitCenter()
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }else {
            Glide.with(context)
                    .load(new File(url.getPath()))
                    .placeholder(context.getResources().getDrawable(load))
                    .error(R.drawable.logo_rsnd)
                    .fitCenter()
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(circleImageView);
        }
    }
}
