package com.mac.zonemovies.view.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mac.zonemovies.R;
import com.mac.zonemovies.data.remote.movieapi.to.Result;
import com.mac.zonemovies.view.movie.MovieActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private static final String TAG = "HomeAdapter_TAG";

    private List<Result> results;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView =
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_movies_showing, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Result result = results.get(position);
        viewHolder.movieTitle.setText(result.getTitle());
        Picasso.get().load(result.getPosterURL()).into(viewHolder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();
    }

    public void updateDataSet(List<Result> results) {
        if(this.results!=null) {
            this.results.addAll(results);
        } else {
            this.results = results;
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final Context context;
        ImageView moviePoster;
        TextView movieTitle;

        ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            moviePoster = itemView.findViewById(R.id.moviePoster);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: clicked " + getAdapterPosition());
            context.startActivity(MovieActivity.startMovieActivity(itemView.getContext()));
        }
    }

}
