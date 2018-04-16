package com.example.isabelmartin.astropicture;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isabelmartin.astropicture.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>  {

    private ArrayList<Photo> photos;

    public RecyclerAdapter(ArrayList<Photo> photosList) {
        photos = photosList;
    }

    @Override
    public RecyclerAdapter.PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_row, parent, false);

        return new PhotoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.PhotoHolder holder, int position) {
        Photo itemPhoto = photos.get(position);
        holder.bindPhoto(itemPhoto);
    }

    @Override
    public int getItemCount() {
        return photos != null ? photos.size() : 0;
    }

    public class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View view;
        private Photo photo = null;

        public PhotoHolder(View itemView) {
            super(itemView);

            view = itemView;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent showPhotoIntent = new Intent(context, PhotoActivity.class);
            showPhotoIntent.putExtra(Photo.LOGTAG, photo);
            context.startActivity(showPhotoIntent);
        }

        public void bindPhoto(Photo photo){

            this.photo = photo;
            Picasso.with(view.getContext()).load(photo.getUrl()).into((ImageView) view.findViewById(R.id.itemImage));
            ((TextView)view.findViewById(R.id.itemDescription)).setText(photo.getDescription());
        }
    }
}
