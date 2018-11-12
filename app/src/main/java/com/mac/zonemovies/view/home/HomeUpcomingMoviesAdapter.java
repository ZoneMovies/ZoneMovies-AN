package com.mac.zonemovies.view.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mac.zonemovies.R;
import com.mac.zonemovies.data.remote.movieapi.to.common.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeUpcomingMoviesAdapter extends RecyclerView.Adapter<HomeUpcomingMoviesAdapter.ViewHolder> {

    private final HomeContract.View homeView;

    private List<Result> upcomingMovies;

    HomeUpcomingMoviesAdapter(HomeContract.View homeView) {
        this.homeView = homeView;
    }

    public void updateDataSet(List<Result> upcomingMovies) {
        this.upcomingMovies = upcomingMovies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie_upcoming, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Result movie = upcomingMovies.get(position);
        viewHolder.title.setText(movie.getTitle());
        Picasso.get().load(movie.getBackdropURL()).into(viewHolder.backdrop);
    }

    @Override
    public int getItemCount() {
        return upcomingMovies == null ? 0 : upcomingMovies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView backdrop;
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            backdrop = itemView.findViewById(R.id.backdrop);
            title = itemView.findViewById(R.id.movieTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            homeView.navigateToMovie(upcomingMovies.get(getAdapterPosition()).getId());
        }

    }
}
