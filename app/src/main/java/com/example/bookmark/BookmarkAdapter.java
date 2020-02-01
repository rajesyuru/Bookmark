package com.example.bookmark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {
    private Context mContext;
    private int mResource;
    private ArrayList<Bookmark> mBookmark;
    private SetClick mSetClick;

    public BookmarkAdapter(Context context, int resource, ArrayList<Bookmark> bookmarks, SetClick setClick) {
        mContext = context;
        mResource = resource;
        mBookmark = bookmarks;
        mSetClick = setClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(mResource, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Bookmark bookmark = mBookmark.get(position);

        holder.bookmarkTitle.setText(bookmark.getTitle());
        holder.bookmarkDescription.setText(bookmark.getDesc());

        holder.EditPencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetClick.onEdit(holder.getAdapterPosition());
            }
        });

        holder.DeleteCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetClick.onDelete(holder.getAdapterPosition());
            }
        });

        holder.textArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetClick.linkClick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBookmark.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookmarkTitle;
        TextView bookmarkDescription;
        ImageView EditPencil;
        ImageView DeleteCircle;
        LinearLayout textArea;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookmarkTitle = itemView.findViewById(R.id.bookmarkTitle);
            bookmarkDescription = itemView.findViewById(R.id.bookmarkDescription);
            EditPencil = itemView.findViewById(R.id.EditPencil);
            DeleteCircle = itemView.findViewById(R.id.DeleteCircle);
            textArea = itemView.findViewById(R.id.textArea);

        }
    }

    public interface SetClick {
        void linkClick (int position);
        void onEdit (int position);
        void onDelete (int position);
    }
}
