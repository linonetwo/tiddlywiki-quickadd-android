package com.example.twquickadd;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twquickadd.room.Tiddler;

import java.util.ArrayList;
import java.util.List;

public class TiddlerListAdaptor extends RecyclerView.Adapter<TiddlerListAdaptor.ViewHolder> {

    private List<Tiddler> mDataList = new ArrayList<Tiddler>();
    private Context mContext;

    public TiddlerListAdaptor(@NonNull DiffUtil.ItemCallback<Tiddler> diffCallback, Context mContext) {
        super();
        this.mContext = mContext;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mListItemTextView.setText(mDataList.get(position).getContent());
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public void submitList(List<Tiddler> list) {
        mDataList = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mListItemTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mListItemTextView = itemView.findViewById(R.id.cache_text);
        }
    }

    static class TiddlerDiff extends DiffUtil.ItemCallback<Tiddler> {

        @Override
        public boolean areItemsTheSame(@NonNull Tiddler oldItem, @NonNull Tiddler newItem) {
            return oldItem.getTitle() == newItem.getTitle();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Tiddler oldItem, @NonNull Tiddler newItem) {
            return oldItem.getContent().equals(newItem.getContent());
        }
    }
}
