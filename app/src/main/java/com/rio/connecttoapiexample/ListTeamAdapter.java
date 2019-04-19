package com.rio.connecttoapiexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListTeamAdapter extends RecyclerView.Adapter<ListTeamAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Team> listTeam;

    public ListTeamAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Team> getListTeam() {
        return listTeam;
    }

    public void setListTeam(ArrayList<Team> listTeam) {
        this.listTeam = listTeam;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListTeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTeamAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tvNameTeam.setText(getListTeam().get(i).getNameTeam());
        Glide.with(context)
                .load(getListTeam().get(i).getLogoTeam())
                .apply(new RequestOptions().override(60,60))
                .into(viewHolder.ivLogoTeam);
    }

    @Override
    public int getItemCount() {
        return getListTeam().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameTeam;
        ImageView ivLogoTeam;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameTeam = itemView.findViewById(R.id.tv_item_name);
            ivLogoTeam = itemView.findViewById(R.id.iv_item_photo);

        }
    }
}
