package com.mac.zonemovies.view.home;

import android.content.Context;
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

public class HomeMoviesAdapter extends RecyclerView.Adapter<HomeMoviesAdapter.ViewHolder> {

    private final HomeContract.View homeView;

    private List<Result> results;

    public HomeMoviesAdapter(HomeContract.View homeView) {
        this.homeView = homeView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView =
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_movies_home, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Result result = results.get(position);
        viewHolder.movieTitle.setText(result.getTitle());
        viewHolder.movieUserVote.setText(getFormattedVotePercentage(viewHolder.movieUserVote.getContext(),
                                                                       result.getUserVotePercentage()));
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

    private String getFormattedVotePercentage(final Context context, String percentage) {
        return context.getString(R.string.userVote, percentage);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView moviePoster;
        TextView movieTitle;
        TextView movieUserVote;

        ViewHolder(View itemView) {
            super(itemView);
            moviePoster = itemView.findViewById(R.id.moviePoster);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieUserVote = itemView.findViewById(R.id.movieUserVote);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            homeView.navigateToMovie(results.get(getAdapterPosition()).getId());
        }
    }

}
