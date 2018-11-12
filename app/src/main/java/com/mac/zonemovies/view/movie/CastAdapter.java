package com.mac.zonemovies.view.movie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mac.zonemovies.R;
import com.mac.zonemovies.data.remote.movieapi.to.credits.Cast;
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Cast castMember = cast.get(position);
        Picasso.get().load(castMember.getProfilePicURL()).into(viewHolder.actorPicture);
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

        ViewHolder(View itemView) {
            super(itemView);
            actorPicture = itemView.findViewById(R.id.actorPicture);
            actorName = itemView.findViewById(R.id.actorName);
            characterName = itemView.findViewById(R.id.characterName);
        }

    }

}
