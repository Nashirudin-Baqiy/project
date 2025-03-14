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

import id.co.rsnasionaldiponegoro.epublic.Model.Models.Info;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityDetailPromo;

/**
 * Created by xsanz on 9/26/2018.
 */

public class AdapterPromo extends RecyclerView.Adapter<AdapterPromo.MyViewHolder> {
    private ArrayList<Info> infos = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public AdapterPromo(Context context, ArrayList<Info> infos) {
        this.infos = infos;
        this.context = context;
        try {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception e) {
            Test.look("Error On: ", e.getMessage());
        }
    }

    @Override
    public AdapterPromo.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_info, parent, false);
        return new AdapterPromo.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(AdapterPromo.MyViewHolder holder, int position) {
        Info info = infos.get(position);
        holder.info_judul.setText(info.getNAMA());
//        holder.info_tgl.setText();
        if(info.getDESKRIPSI()!=null){
            if (info.getDESKRIPSI().length() < 25) {
                holder.info_desc.setText(Html.fromHtml(info.getDESKRIPSI() + "..."));
            } else {
                holder.info_desc.setText(Html.fromHtml(info.getDESKRIPSI().substring(0, 25) + "..."));
            }
        }
        if(info.getCREATED_AT()!=null){
            try {
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                Date date = simpleDateFormat.parse(info.getCREATED_AT());
                String patterns = " d MMMM yyyy";
                SimpleDateFormat simpleDateFormats = new SimpleDateFormat(patterns);
                String dates = simpleDateFormats.format(date);
                holder.info_tgl.setText(dates);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Glide.with(context)
                .load(info.getTHUMB_GAMBAR())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .centerCrop()
                .placeholder(R.drawable.ic_profile)
                .error(R.drawable.ic_profile)
                .into(holder.info_img);
    }

    @Override
    public int getItemCount() {
        return infos.size();
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
                    Intent myIntent = new Intent(context, ActivityDetailPromo.class);
                    myIntent.putExtra("ID", "" + infos.get(getAdapterPosition()).getID());
                    myIntent.putExtra("title", "" + infos.get(getAdapterPosition()).getNAMA());
                    myIntent.putExtra("desc", "" + infos.get(getAdapterPosition()).getDESKRIPSI());
                    myIntent.putExtra("img", "" + infos.get(getAdapterPosition()).getFULL_GAMBAR());
//                    myIntent.putExtra("id", ""+infos.get(getAdapterPosition()).getId());


                    if(infos.get(getAdapterPosition()).getCREATED_AT()!=null){
                        try {
                            String pattern = "yyyy-MM-dd";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                            Date date = simpleDateFormat.parse(infos.get(getAdapterPosition()).getCREATED_AT());
                            String patterns = " d MMMM yyyy";
                            SimpleDateFormat simpleDateFormats = new SimpleDateFormat(patterns);
                            String dates = simpleDateFormats.format(date);
                            myIntent.putExtra("tgl", ""+dates);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    context.startActivity(myIntent);
                }
            });
        }
    }
}
