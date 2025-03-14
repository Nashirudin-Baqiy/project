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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.Artikel;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityArticleList;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityDetailInfo;

/**
 * Created by xsanz on 9/26/2018.
 */

public class AdapterArtikel extends RecyclerView.Adapter<AdapterArtikel.MyViewHolder> {
    private ArrayList<Artikel> artikels = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public AdapterArtikel(Context context, ArrayList<Artikel> artikels) {
        this.artikels = artikels;
        this.context = context;
        try {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception e) {
            Test.look("Error On: ", e.getMessage());
        }
    }

    @Override
    public AdapterArtikel.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_info, parent, false);
        return new AdapterArtikel.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(AdapterArtikel.MyViewHolder holder, int position) {
        Artikel artikel = artikels.get(position);
        holder.info_judul.setText(artikel.getJUDUL());


        if(artikel.getUPDATED_AT()!=null){
            try {
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                Date date = simpleDateFormat.parse(artikel.getUPDATED_AT());
                String patterns = " d MMMM yyyy";
                SimpleDateFormat simpleDateFormats = new SimpleDateFormat(patterns);
                String dates = simpleDateFormats.format(date);
                holder.info_tgl.setText(dates);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        if(artikel.getDESKRIPSI()!=null){
            holder.info_desc.setText(Html.fromHtml(artikel.getDESKRIPSI() + "..."));
        }

        Glide.with(context)
                .load(artikel.getGAMBAR())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .centerCrop()
                .placeholder(R.drawable.ic_profile)
                .error(R.drawable.ic_profile)
                .into(holder.info_img);
    }

    @Override
    public int getItemCount() {
        return artikels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView info_judul;
        private TextView info_tgl;
        private TextView info_desc;
        private ImageView info_img;

        private MyViewHolder(View itemView) {
            super(itemView);
            info_judul = itemView.findViewById(R.id.info_judul);
            info_desc = itemView.findViewById(R.id.info_desc);
            info_tgl = itemView.findViewById(R.id.info_tgl);
            info_img = itemView.findViewById(R.id.info_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!artikels.get(getAdapterPosition()).getISPARENT()){
                        Intent myIntent = new Intent(context, ActivityDetailInfo.class);
                        myIntent.putExtra("title", "" + artikels.get(getAdapterPosition()).getJUDUL());
                        myIntent.putExtra("desc", "" + artikels.get(getAdapterPosition()).getDESKRIPSI());
                        myIntent.putExtra("img", "" + artikels.get(getAdapterPosition()).getGAMBAR());
//                    myIntent.putExtra("id", ""+infos.get(getAdapterPosition()).getId());
                       if(artikels.get(getAdapterPosition()).getUPDATED_AT()!=null){
                           try {
                               String pattern = "yyyy-MM-dd";
                               SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                               Date date = simpleDateFormat.parse(artikels.get(getAdapterPosition()).getUPDATED_AT());
                               String patterns = " d MMMM yyyy";
                               SimpleDateFormat simpleDateFormats = new SimpleDateFormat(patterns);
                               String dates = simpleDateFormats.format(date);
                               myIntent.putExtra("tgl", ""+dates);
                           } catch (ParseException e) {
                               e.printStackTrace();
                           }
                       }


                        context.startActivity(myIntent);
                    }else{
                        Intent myIntent = new Intent(context, ActivityArticleList.class);
                        myIntent.putExtra("title", "" + artikels.get(getAdapterPosition()).getJUDUL());
                        myIntent.putExtra("desc", "" + artikels.get(getAdapterPosition()).getDESKRIPSI());
                        myIntent.putExtra("img", "" + artikels.get(getAdapterPosition()).getGAMBAR());
//                    myIntent.putExtra("id", ""+infos.get(getAdapterPosition()).getId());
                        if(artikels.get(getAdapterPosition()).getUPDATED_AT()!=null){
                            try {
                                String pattern = "yyyy-MM-dd";
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                                Date date = simpleDateFormat.parse(artikels.get(getAdapterPosition()).getUPDATED_AT());
                                String patterns = " d MMMM yyyy";
                                SimpleDateFormat simpleDateFormats = new SimpleDateFormat(patterns);
                                String dates = simpleDateFormats.format(date);
                                myIntent.putExtra("tgl", ""+dates);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                        myIntent.putExtra("ID_JUDUL", ""+artikels.get(getAdapterPosition()).getID());
                        context.startActivity(myIntent);

                    }
                }
            });
        }
    }
}
