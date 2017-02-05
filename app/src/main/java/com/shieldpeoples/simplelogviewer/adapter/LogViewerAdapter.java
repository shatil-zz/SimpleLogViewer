package com.shieldpeoples.simplelogviewer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.shieldpeoples.simplelogviewer.R;
import com.shieldpeoples.simplelogviewer.model.log.LogViewerModel;
import com.shieldpeoples.simplelogviewer.utils.format.DateTimeFormatUtils;

import java.util.ArrayList;

/**
 * Created by Shahanur on 1/25/2017.
 */

public class LogViewerAdapter extends RecyclerView.Adapter<LogViewerAdapter.ViewHolder> {

    Context context;
    ArrayList<LogViewerModel> dataItems;

    public LogViewerAdapter(Context context, ArrayList<LogViewerModel> items) {
        this.context = context;
        this.dataItems = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvTitle, tvDescription, tvSenderInfo, tvStatus;
        ImageView ivStatus;

        public ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_tag);
            tvDescription = (TextView) v.findViewById(R.id.tv_description);
            tvStatus = (TextView) v.findViewById(R.id.tv_status);
            tvSenderInfo = (TextView) v.findViewById(R.id.tv_sender_info);
            ivStatus = (ImageView) v.findViewById(R.id.iv_status);
        }

    }

    public void reset(ArrayList<LogViewerModel> dataItems) {
        this.dataItems = dataItems;
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<LogViewerModel> dataItems) {
        for (LogViewerModel model : dataItems) {
            this.dataItems.add(model);
        }
        notifyItemRangeChanged(this.dataItems.size() - dataItems.size(), dataItems.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_log_viewer, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LogViewerModel item = getItem(position);
        if (item.getTagName() != null && item.getTagName().length() > 0) {
            holder.tvTitle.setText(item.getTagName());
        } else {
            holder.tvTitle.setText("No tag found");
        }
        if (item.getDescription() != null && item.getDescription().length() > 0) {
            holder.tvDescription.setText(item.getDescription());
        } else {
            holder.tvDescription.setText("No description found");
        }
        if (item.getSenderName() != null && item.getSenderName().length() > 0) {
            holder.tvSenderInfo.setText(Html.fromHtml("Added by <b>" + item.getSenderName()+
                    "</b> at "+ DateTimeFormatUtils.getHumanReadableDateTime(item.getTime())));
        } else {
            holder.tvSenderInfo.setText(Html.fromHtml("<font color='#AA0000'>No name found</font>"));
        }
        holder.ivStatus.setBackgroundColor(item.getColorOfStatus());
        holder.tvStatus.setText(Html.fromHtml(item.getReadableStatus()));
    }

    public LogViewerModel getItem(int position) {
        return dataItems.get(position);
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }
}
