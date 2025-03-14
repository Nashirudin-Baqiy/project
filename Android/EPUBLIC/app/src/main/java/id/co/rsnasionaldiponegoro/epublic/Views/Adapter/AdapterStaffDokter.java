package id.co.rsnasionaldiponegoro.epublic.Views.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.BuildConfig;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.Staff;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.LoadImages;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityDetailDokter;

/**
 * Created by xsanz on 9/27/2018.
 */

public class AdapterStaffDokter extends RecyclerView.Adapter<AdapterStaffDokter.MyViewHolder> {
    private ArrayList<Staff> staff = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public AdapterStaffDokter(Context context, ArrayList<Staff> staff){
        this.staff = staff;
        this.context         = context;
        try {
            inflater        = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }catch (Exception e){
            Test.look(e.getMessage());
        }
    }

    @Override
    public AdapterStaffDokter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_staff_dokter, parent, false);
        return new AdapterStaffDokter.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(AdapterStaffDokter.MyViewHolder holder, int position) {
        Staff staff = this.staff.get(position);
        holder.dokter_nama.setText(staff.getNAMA());
//        holder.dokter_caption.setText(Html.fromHtml(staff.getDESKRIPSI()+"..."));
        if(staff.getCAPTION()!=null){
            holder.dokter_motto.setText(Html.fromHtml(staff.getCAPTION()));
        }

       if(staff.getGAMBAR()!=null){
           try {
               if(staff.getMAPPINGDOKTER()!=null){
                   LoadImages.LoadDataImages(context, BuildConfig.BASE_URL_API+"storage/img/dokter/"+ URLEncoder.encode(staff.getGAMBAR(),"UTF-8")
                           .replace("+", "%20")
                           .replace("%2C", ","),R.drawable.ic_image_load,holder.dokter_photo,null,"FULL");
               }else{
                   LoadImages.LoadDataImages(context, BuildConfig.BASE_URL_API+"storage/img/staff/"+ URLEncoder.encode(staff.getGAMBAR(),"UTF-8")
                           .replace("+", "%20")
                           .replace("%2C", ","),R.drawable.ic_image_load,holder.dokter_photo,null,"FULL");
               }

               Test.look(BuildConfig.BASE_URL_API+"storage/img/staff/"+URLEncoder.encode(staff.getGAMBAR(), "UTF-8")
                       .replace("+", "%20").replace("%2C", ","));
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
               Test.look(e.getMessage());
           }
       }else{
           LoadImages.LoadDataImages(context, BuildConfig.BASE_URL_API+"storage/img/"+ staff.getGAMBAR()+".jpg",R.drawable.ic_image_load,holder.dokter_photo,null,"FULL");
       }
    }

    @Override
    public int getItemCount() {
        return staff.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView dokter_photo;
        private TextView dokter_nama;
        private TextView dokter_motto;
        private TextView dokter_caption;

        private MyViewHolder(View itemView) {
            super(itemView);
            dokter_photo    = itemView.findViewById(R.id.dokter_photo);
            dokter_nama     = itemView.findViewById(R.id.dokter_nama);
            dokter_motto     = itemView.findViewById(R.id.dokter_motto);
            dokter_caption      = itemView.findViewById(R.id.dokter_caption);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent myIntent = new Intent(context, ActivityDetailDokter.class);
                if(staff.get(getAdapterPosition()).getGAMBAR()!=null){
                    if(staff.get(getAdapterPosition()).getMAPPINGDOKTER()!=null){
                        myIntent.putExtra("idDokter", "" + staff.get(getAdapterPosition()).getMAPPINGDOKTER());
                        myIntent.putExtra("img", "" + BuildConfig.BASE_URL_API+"storage/img/dokter/"+ staff.get(getAdapterPosition()).getGAMBAR());
                    }else{
                        myIntent.putExtra("img", "" + BuildConfig.BASE_URL_API+"storage/img/staff/"+ staff.get(getAdapterPosition()).getGAMBAR());
                    }
                }else{
                    myIntent.putExtra("img", "" + BuildConfig.BASE_URL_API+"storage/img/"+ staff.get(getAdapterPosition()).getGAMBAR()+".jpg");
                }
                myIntent.putExtra("nama", "" + staff.get(getAdapterPosition()).getNAMA());
                myIntent.putExtra("desc", "" + staff.get(getAdapterPosition()).getDESKRIPSI());
                myIntent.putExtra("motto", "" + staff.get(getAdapterPosition()).getCAPTION());

//                Test.look(""+BuildConfig.BASE_URL_API+"storage/img/staff/"+ staff.get(getAdapterPosition()).getGAMBAR());
                context.startActivity(myIntent);



                }
            });
        }
    }
}
