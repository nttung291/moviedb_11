package com.framgia.moviedb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.framgia.moviedb.R;
import com.framgia.moviedb.model.Movie;
import com.framgia.moviedb.screen.BaseRecyclerViewAdapter;
import com.framgia.moviedb.untils.Constant;
import com.framgia.moviedb.untils.Utils;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends BaseRecyclerViewAdapter<MovieAdapter.ViewHolder> {
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private LayoutInflater mLayoutInflater;

    public MovieAdapter(@NonNull Context context) {
        super(context);
    }

    public void replaceData(@NonNull List<Movie> movies) {
        if(movies != null){
            mMovies.clear();
            mMovies.addAll(movies);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null){
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        View itemview = mLayoutInflater.inflate(R.layout.item_rv_mainfilm,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewPoster;
        private TextView textViewTitle;
        private TextView textViewVote;
        private TextView textViewRelease;
        private TextView textViewRate;
        ViewHolder(View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.iv_poster);
            textViewTitle = itemView.findViewById(R.id.tv_filmname_item);
            textViewRelease = itemView.findViewById(R.id.tv_release_item);
            textViewVote = itemView.findViewById(R.id.tv_votecount_item);
            textViewRate = itemView.findViewById(R.id.tv_rate_item);
        }

        public void bindData(Movie movie) {
            Glide.with(getContext())
                    .load(Constant.IMG_URL + movie.getPosterPath())
                    .into(imageViewPoster);
            textViewTitle.setText(movie.getTitle());
            textViewRelease.setText(Utils.dateFormat(movie.getReleaseDate()));
            textViewVote.setText(movie.getVoteCount());
            textViewRate.setText(Double.toString(movie.getVoteAverage()) + Constant.IMDB_RATE);
        }
    }
}
