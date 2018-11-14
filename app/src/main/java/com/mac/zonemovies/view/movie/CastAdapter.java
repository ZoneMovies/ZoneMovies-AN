package com.mac.zonemovies.view.movie;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.mac.zonemovies.R;
import com.mac.zonemovies.data.remote.movieapi.to.credits.Cast;
import com.mac.zonemovies.utils.GlideApp;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    private List<Cast> cast;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie_cast, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        Cast castMember = cast.get(position);
//        Picasso.get()
//                .load(castMember.getProfilePicURL()).error(R.drawable.coming_soon).into(viewHolder.actorPicture);
        Glide.with(viewHolder.context)
                .load(castMember.getProfilePicURL())
                .apply(new RequestOptions().placeholder(R.drawable.coming_soon).error(R.drawable.coming_soon))
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.actorPicture);
        viewHolder.actorName.setText(castMember.getName());
        viewHolder.characterName.setText(castMember.getCharacter());
    }

    @Override
    public int getItemCount() {
        return cast == null ? 0 : cast.size();
    }

    public void updateDataSet(List<Cast> cast) {
        this.cast = cast;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView actorPicture;
        TextView actorName;
        TextView characterName;
        final Context context;

        ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            actorPicture = itemView.findViewById(R.id.actorPicture);
            actorName = itemView.findViewById(R.id.actorName);
            characterName = itemView.findViewById(R.id.characterName);
        }

    }

}
