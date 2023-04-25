package com.adhikar.adhikar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adhikar.adhikar.DetailActivity;
import com.adhikar.adhikar.Modal.SubDetailModal;
import com.adhikar.adhikar.R;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;

import java.util.ArrayList;
import java.util.List;

public class SubDetailAdapter extends RecyclerView.Adapter<SubDetailAdapter.ViewHolder> {
        List<SubDetailModal> data_list;

        Context context;

    public SubDetailAdapter(List<SubDetailModal> data_list, Context context) {
        this.data_list = data_list;

        this.context = context;
    }

    @NonNull
    @Override
    public SubDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.sub_detail_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubDetailAdapter.ViewHolder holder, int position) {
        holder.tv_title.setText(data_list.get(position).getTitle());
        holder.tv_benifit.setText(data_list.get(position).getBenifit());
        holder.tv_detail.setText(data_list.get(position).getDetails());
    }

    @Override
    public int getItemCount() {
        return data_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title, tv_benifit, tv_detail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.title);
            tv_benifit = itemView.findViewById(R.id.benifits);
            tv_detail = itemView.findViewById(R.id.details);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("title",data_list.get(getAdapterPosition()).getTitle());
                    intent.putExtra("benifit",data_list.get(getAdapterPosition()).getBenifit());
                    intent.putExtra("process",data_list.get(getAdapterPosition()).getProcess());
                    intent.putExtra("criteria",data_list.get(getAdapterPosition()).getCriteria());
                    intent.putExtra("document",data_list.get(getAdapterPosition()).getDocuments());
                    intent.putExtra("details",data_list.get(getAdapterPosition()).getDetails());
                    intent.putExtra("audio",data_list.get(getAdapterPosition()).getAudio());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
