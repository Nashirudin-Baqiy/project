package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Screenshot;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;

public class ActivityPendaftaranWeb extends AppCompatActivity {
    private static final int INPUT_FILE_REQUEST_CODE = 1;
    private static final int FILECHOOSER_RESULTCODE = 1;
    private static final String TAG = ActivityPendaftaranWeb.class.getSimpleName();
    private WebView webView;
    private WebSettings webSettings;
    private ValueCallback<Uri> mUploadMessage;
    private Uri mCapturedImageURI = null;
    private ValueCallback<Uri[]> mFilePathCallback;
    private String mCameraPhotoPath;
    private TextView version;
    private TextView title_bar;
    private LinearLayout btn_back;
    private Session session;
    private TextView btn_ss;
    private String imageFilePath;
    private Loading loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran_web);
        session = new Session(ActivityPendaftaranWeb.this);

        webView = (WebView) findViewById(R.id.tampilan);
        title_bar = (TextView) findViewById(R.id.title_bar);
        btn_ss = (TextView) findViewById(R.id.btn_ss);
        btn_back = (LinearLayout) findViewById(R.id.btn_back);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        webView.setWebViewClient(new PQClient());
        webView.setWebChromeClient(new PQChromeClient());
        loading=new Loading(ActivityPendaftaranWeb.this);
//        version          = (TextView) findViewById(R.id.version);
//        version.setText("V."+BuildConfig.VERSION_NAME);
        btn_ss.setVisibility(View.GONE);
        title_bar.setText("Suara Konsumen");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //if SDK version is greater of 19 then activate hardware acceleration otherwise activate software acceleration
        if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT >= 11 && Build.VERSION.SDK_INT < 19) {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        Log.v("url", session.getSessionString("URL_SUARA_KONSUMEN",""));
        webView.loadUrl(""+ session.getSessionString("URL_SUARA_KONSUMEN",""));
//        if(session.getSessionUser().getNORM()!=null&&session.getSessionUser().getNORM()!=null || session.getSessionUser().getNORM().equals("0") && session.getSessionUser().getTGLLAHIR().equals("0")){
//            String[] parts = session.getSessionUser().getTGLLAHIR().split("-");
//            //parts[2]+"-"+parts[1]+"-"+parts[0]
//            webView.loadUrl(""+ BuildConfig.URL_PENDAFTARAN+"login2/"+session.getSessionUser().getNORM().trim()+"/"+session.getSessionUser().getTGLLAHIR().trim());
////            Test.look(""+ BuildConfig.URL_PENDAFTARAN+"login2/"+session.getSessionUser().getNORM()+"/"+parts[2]+"-"+parts[1]+"-"+parts[0]);
//        }else{
//            webView.loadUrl(""+ BuildConfig.URL_PENDAFTARAN);
////            Test.look(BuildConfig.URL_PENDAFTARAN);
//        }


        btn_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                View v1 = webView.getRootView();
//                v1.setDrawingCacheEnabled(true);
//                Bitmap bm = v1.getDrawingCache();
                loading.show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int permissionCheck = ContextCompat.checkSelfPermission(ActivityPendaftaranWeb.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    int permissionCheck2 = ContextCompat.checkSelfPermission(ActivityPendaftaranWeb.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE);

                    if (permissionCheck == PackageManager.PERMISSION_GRANTED && permissionCheck2 == PackageManager.PERMISSION_GRANTED) {
                        Log.e("MainActivity ", "P granted");
                        Screenshot.takeScreenshot(ActivityPendaftaranWeb.this);

                    } else {
                        ActivityCompat.requestPermissions(ActivityPendaftaranWeb.this,
                                new String[]{
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                }, 1);
                    }
                    loading.hide();
                } else {
                    loading.hide();
                    Log.e("MainActivity", "Lower Than MarshMallow");
                    Screenshot.takeScreenshot(ActivityPendaftaranWeb.this);
                }

            }


        });
    }

//    private File createImageFile() throws IOException {
//        String timeStamp =
//                new SimpleDateFormat("yyyyMMdd_HHmmss",
//                        Locale.getDefault()).format(new Date());
//        String imageFileName = "IMG_" + timeStamp + "_";
//        File storageDir =
//                getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        imageFilePath = image.getAbsolutePath();
//        return image;
//    }
    public void storeScreenshot(Bitmap bitmap) {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "SS_PENDAFTARAN_" + timeStamp + "_";
        String path = Environment.getExternalStorageDirectory().toString() + "/" + imageFileName;
        OutputStream out = null;
        File imageFile = new File(path);

        try {
            out = new FileOutputStream(imageFile);
            // choose JPEG format
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
        } catch (FileNotFoundException e) {
            // manage exception ...
        } catch (IOException e) {
            // manage exception ...
        } finally {

            try {
                if (out != null) {
                    out.close();
                }

            } catch (Exception exc) {
            }

        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return imageFile;
    }

    public class PQChromeClient extends WebChromeClient {

        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            AlertDialog dialog = new AlertDialog.Builder(view.getContext()).
                    setMessage(message).
                    setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            result.confirm();
                        }
                    }).setCancelable(false).create();
            dialog.show();
            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
            AlertDialog dialog = new AlertDialog.Builder(view.getContext()).
                    setMessage(message).
                    setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            result.confirm();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            result.cancel();
                        }
                    }).setCancelable(false).create();
            dialog.show();
            return true;
        }


        // For Android 5.0
        public boolean onShowFileChooser(WebView view, ValueCallback<Uri[]> filePath, WebChromeClient.FileChooserParams fileChooserParams) {
            // Double check that we don't have any existing callbacks
            if (mFilePathCallback != null) {
                mFilePathCallback.onReceiveValue(null);
            }
            mFilePathCallback = filePath;

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                    takePictureIntent.putExtra("PhotoPath", mCameraPhotoPath);
                } catch (IOException ex) {
                    // Error occurred while creating the File
                    Log.e(TAG, "Unable to create Image File", ex);
                }

                // Continue only if the File was successfully created
                if (photoFile != null) {
                    mCameraPhotoPath = "file:" + photoFile.getAbsolutePath();
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(photoFile));
                } else {
                    takePictureIntent = null;
                }
            }

            Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
            contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
            contentSelectionIntent.setType("image/*");

            Intent[] intentArray;
            if (takePictureIntent != null) {
                intentArray = new Intent[]{takePictureIntent};
            } else {
                intentArray = new Intent[0];
            }

            Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
            chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
            chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);

            startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);

            return true;

        }

        // openFileChooser for Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {

            mUploadMessage = uploadMsg;
            // Create AndroidExampleFolder at sdcard
            // Create AndroidExampleFolder at sdcard

            File imageStorageDir = new File(
                    Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES)
                    , "AndroidExampleFolder");

            if (!imageStorageDir.exists()) {
                // Create AndroidExampleFolder at sdcard
                imageStorageDir.mkdirs();
            }

            // Create camera captured image file path and name
            File file = new File(
                    imageStorageDir + File.separator + "IMG_"
                            + String.valueOf(System.currentTimeMillis())
                            + ".jpg");

            mCapturedImageURI = Uri.fromFile(file);

            // Camera capture image intent
            final Intent captureIntent = new Intent(
                    android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);

            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");

            // Create file chooser intent
            Intent chooserIntent = Intent.createChooser(i, "Image Chooser");

            // Set camera intent to file chooser
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS
                    , new Parcelable[]{captureIntent});

            // On select image call onActivityResult method of activity
            startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE);


        }

        // openFileChooser for Android < 3.0
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            openFileChooser(uploadMsg, "");
        }

        //openFileChooser for other Android versions
        public void openFileChooser(ValueCallback<Uri> uploadMsg,
                                    String acceptType,
                                    String capture) {

            openFileChooser(uploadMsg, acceptType);
        }

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && (webView.getUrl().equals("http://43.245.185.162:8080/form_pendaftaran/public") || webView.getUrl().equals("http://103.245.17.2/form_pendaftaran/public/login"))) {
//            finish();
//            return true;
//        }
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }

//        if ((keyCode == KeyEvent.KEYCODE_BACK) && !webView.canGoBack()) {
//            finish();
//            return true;
//        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)

        return super.onKeyDown(keyCode, event);
    }

    public class PQClient extends WebViewClient {
        ProgressDialog progressDialog;

        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            // If url contains mailto link then open Mail Intent
            if (url.contains("mailto:")) {

                // Could be cleverer and use a regex
                //Open links in new browser
                view.getContext().startActivity(
                        new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

                // Here we can open new activity

                return true;

            } else {

                // Stay within this webview and load url
                view.loadUrl(url);
                return true;
            }
        }

        //Show loader on url load
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            // Then show progress  Dialog
            // in standard case YourActivity.this
//            findViewById(R.id.gambar).setVisibility(View.VISIBLE);
            findViewById(R.id.progressBar1).setVisibility(View.VISIBLE);

//            if (progressDialog == null) {
//                progressDialog = new ProgressDialog(MainActivity.this);
//                progressDialog.setMessage("Harap Tunggu...");
//                progressDialog.show();
//            }
        }

        // Called when all page resources loaded
        public void onPageFinished(WebView view, String url) {
//            findViewById(R.id.gambar).setVisibility(View.GONE);
            findViewById(R.id.progressBar1).setVisibility(View.GONE);
            findViewById(R.id.tampilan).setVisibility(View.VISIBLE);

            webView.loadUrl("javascript:(function(){ " +
                    "document.getElementById('android-app').style.display='none';})()");
            try {
                // Close progressDialog
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        @Override
        public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
            //Clearing the WebView
            try {
                webView.stopLoading();
            } catch (Exception e) {
            }
            try {
                webView.clearView();
            } catch (Exception e) {
            }
            if (webView.canGoBack()) {
                webView.goBack();
            }
            webView.loadUrl("about:blank");

            //Showing and creating an alet dialog
            AlertDialog alertDialog = new AlertDialog.Builder(ActivityPendaftaranWeb.this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("No internet connection was found!");
            alertDialog.setButton("Again", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    startActivity(getIntent());
                }
            });
            try {
                alertDialog.show();
            } catch (Exception ex) {
            }
            //Don't forget to call supper!
            super.onReceivedError(webView, errorCode, description, failingUrl);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            if (requestCode != INPUT_FILE_REQUEST_CODE || mFilePathCallback == null) {
                super.onActivityResult(requestCode, resultCode, data);
                return;
            }

            Uri[] results = null;

            // Check that the response is a good one
            if (resultCode == Activity.RESULT_OK) {
                if (data == null) {
                    // If there is not data, then we may have taken a photo
                    if (mCameraPhotoPath != null) {
                        results = new Uri[]{Uri.parse(mCameraPhotoPath)};
                    }
                } else {
                    String dataString = data.getDataString();
                    if (dataString != null) {
                        results = new Uri[]{Uri.parse(dataString)};
                    }
                }
            }

            mFilePathCallback.onReceiveValue(results);
            mFilePathCallback = null;

        } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            if (requestCode != FILECHOOSER_RESULTCODE || mUploadMessage == null) {
                super.onActivityResult(requestCode, resultCode, data);
                return;
            }

            if (requestCode == FILECHOOSER_RESULTCODE) {

                if (null == this.mUploadMessage) {
                    return;

                }

                Uri result = null;

                try {
                    if (resultCode != RESULT_OK) {

                        result = null;

                    } else {

                        // retrieve from the private variable if the intent is null
                        result = data == null ? mCapturedImageURI : data.getData();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "activity :" + e,
                            Toast.LENGTH_LONG).show();
                }

                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;

            }
        }

        return;
    }
}
