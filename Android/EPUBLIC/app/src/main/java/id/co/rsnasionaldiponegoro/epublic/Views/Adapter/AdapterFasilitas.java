package id.co.rsnasionaldiponegoro.epublic.Views.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.Artikel;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.LoadImages;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityArticleList;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityDetailInfo;

/**
 * Created by xsanz on 9/27/2018.
 */

public class AdapterFasilitas extends RecyclerView.Adapter<AdapterFasilitas.MyViewHolder> {
    private ArrayList<Artikel> artikels = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public AdapterFasilitas(Context context, ArrayList<Artikel> artikels) {
        this.artikels = artikels;
        this.context = context;
        try {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception e) {
            Test.look("Error On: ", e.getMessage());
        }
    }

    @Override
    public AdapterFasilitas.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.adapter_fasilitas, parent, false);
        return new AdapterFasilitas.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(AdapterFasilitas.MyViewHolder holder, int position) {
        Artikel artikel = artikels.get(position);
        holder.info_judul.setText(artikel.getJUDUL());

        LoadImages.LoadDataImages(context, artikel.getGAMBAR(),
                R.drawable.ic_image_load,holder.info_img,null,"");

//        Glide.with(context)
//                .load(artikel.getGAMBAR())
//                .asBitmap()
//                .dontAnimate()
//                .centerCrop()
//                .placeholder(R.drawable.ic_profile)
//                .error(R.drawable.ic_profile)
//                .into(holder.info_img);
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
                        myIntent.putExtra("tgl", ""+artikels.get(getAdapterPosition()).getUPDATED_AT());
                        context.startActivity(myIntent);
                    }else{
                        Intent myIntent = new Intent(context, ActivityArticleList.class);
                        myIntent.putExtra("title", "" + artikels.get(getAdapterPosition()).getJUDUL());
                        myIntent.putExtra("desc", "" + artikels.get(getAdapterPosition()).getDESKRIPSI());
                        myIntent.putExtra("img", "" + artikels.get(getAdapterPosition()).getGAMBAR());
//                    myIntent.putExtra("id", ""+infos.get(getAdapterPosition()).getId());
                        myIntent.putExtra("tgl", ""+artikels.get(getAdapterPosition()).getUPDATED_AT());
                        myIntent.putExtra("ID_JUDUL", ""+artikels.get(getAdapterPosition()).getID());
                        context.startActivity(myIntent);
                    }
                }
            });
        }
    }
}
