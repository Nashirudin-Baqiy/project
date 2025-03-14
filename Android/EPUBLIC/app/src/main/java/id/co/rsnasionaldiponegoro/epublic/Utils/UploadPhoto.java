package id.co.rsnasionaldiponegoro.epublic.Utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UploadPhoto {
    private Activity act;
    private String url;
    private Session session;
    private String mCurrentPhotoPath = "";

    public UploadPhoto(Activity act){
        this.act = act;
        session = new Session(act);

    }


    public void onClickImage(final String filename, final Integer RequestCodeGallery, final Integer RequestCodeTakeFoto){
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File dir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES+"/Madhang/");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File output =new File(dir, "MDG_"+filename+".jpeg");
                    takePicture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
                    act.startActivityForResult(takePicture, RequestCodeTakeFoto);
                } else if (items[item].equals("Choose from Library")) {
                    Log.d("uploadimage","choose from Library");
                    File dir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES+"/Madhang/");
                    Log.d("uploadimage","dirExists " + dir.exists());
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    act.startActivityForResult(Intent.createChooser(intent, "Select Picture"), RequestCodeGallery);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public String getmCurrentPhotoPath(){
        return  mCurrentPhotoPath;
    }

    public void onClickImages(final String filename){
        final CharSequence[] items = {  "Ambil dari Gallery",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Ambil dari Gallery")) {
                    File dir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES+"/RSND/");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    act.startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = act.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.d("uploadimage","current photo path start " + mCurrentPhotoPath);
        return image;
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex=image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 70
                , baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);
        //Log.e("LOOK", imageEncoded);
        return imageEncoded;
    }
    public static Bitmap decodeBase64(String input) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
    public static void saveFileImage(Bitmap bitmap, Uri lokasi) {
        Log.d("uploadimage","saveFileImage");
        File imageFile = new File(lokasi.getPath());
        float size = (float) (imageFile.length()/1024);
        Log.d("uploadimage","size save" + size);
        Log.d("uploadimage","bitmap " + bitmap + " lokasi " + lokasi.toString());
        OutputStream os;
        try {
            Log.d("uploadimage","try saveFile");
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            Log.d("uploadimage","error saveFile " + new Gson().toJson(e));
//            Log.e("File", "Error writing bitmap", e);
        }
    }

    public Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(
                act.getContentResolver().openInputStream(selectedImage), null, o);

        final int REQUIRED_SIZE = 200;

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        File filenew = new File(selectedImage.getPath());
        float size = (float) (filenew.length()/1024);
        Log.d("uploadimage","size decode" + size);
        return BitmapFactory.decodeStream(
                act.getContentResolver().openInputStream(selectedImage), null, o2);
    }

    public void imageProfilPicture(final String filename, final Integer RequestCodeGallery, final Integer RequestCodeTakeFoto){
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File dir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES+"/Madhang/");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File output =new File(dir, "MDG_"+filename+".jpeg");
                    takePicture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
                    act.startActivityForResult(takePicture, RequestCodeTakeFoto);
                } else if (items[item].equals("Choose from Library")) {
                    Log.d("uploadimage","choose from Library");
                    File dir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES+"/Madhang/");
                    Log.d("uploadimage","dirExists " + dir.exists());
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    act.startActivityForResult(Intent.createChooser(intent, "Select Picture"), RequestCodeGallery);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}
