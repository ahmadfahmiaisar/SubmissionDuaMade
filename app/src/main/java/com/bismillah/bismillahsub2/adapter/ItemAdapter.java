package com.bismillah.bismillahsub2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bismillah.bismillahsub2.BuildConfig;
import com.bismillah.bismillahsub2.R;
import com.bismillah.bismillahsub2.model.ItemResponseMovie;
import com.bismillah.bismillahsub2.view.DetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.EXTRA_SUBJECT;
import static android.content.Intent.EXTRA_TEXT;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private Context context;
    private List<ItemResponseMovie> movieArrayList;

    public ItemAdapter(Context context, ArrayList<ItemResponseMovie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvOverview, tvRelease;
        ImageView ivMovie;
        Button btnDetail, btnShare;
        //CardView cvMovie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            tvRelease = itemView.findViewById(R.id.tv_releaseDate);
            ivMovie = itemView.findViewById(R.id.image_poster);
            btnDetail = itemView.findViewById(R.id.btn_detail);
            btnShare = itemView.findViewById(R.id.btn_share);
            //cvMovie = itemView.findViewById(R.id.cardView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.tvTitle.setText(movieArrayList.get(i).getTitle());
        viewHolder.tvOverview.setText(movieArrayList.get(i).getOverview());
        viewHolder.tvRelease.setText(movieArrayList.get(i).getRelease());
        Glide.with(context)
                .load(BuildConfig.Image + movieArrayList.get(i).getPoster())
                .into(viewHolder.ivMovie);

        viewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                //intent.putExtra(Helper.POSTER, "http://image.tmdb.org/t/p/w185" + movieArrayList.get(i).getPoster());
                intent.putExtra(Helper.BACKDROP, BuildConfig.Image + movieArrayList.get(i).getBackdrop());
                intent.putExtra(Helper.TITLE, viewHolder.tvTitle.getText().toString());
                intent.putExtra(Helper.OVERVIEW, movieArrayList.get(i).getOverview());
                intent.putExtra(Helper.RELEASE_DATE, viewHolder.tvRelease.getText().toString());
                context.startActivity(intent);
            }
        });
        viewHolder.btnShare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
             /*   StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());*/
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                //intent.setType("image/jpg");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                String shareOverview = movieArrayList.get(i).getOverview();
                String shareTitle = viewHolder.tvTitle.getText().toString().trim();
                intent.putExtra(EXTRA_SUBJECT, context.getString(R.string.Subject));
                intent.putExtra(EXTRA_TEXT, shareTitle + "\n" + shareOverview);
                //String image = "http://image.tmdb.org/t/p/w185";
                //intent.putExtra() Uri.parse(Uri.encode("http://image.tmdb.org/t/p/w185"+movieArrayList.get(i).getPoster())));
                //context.startActivity(gambar);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

}
