package com.framgia.moviedb.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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

    public void replaceData(@NonNull List<Movie> movies){
        if (mMovies == null) {
            mMovies = (ArrayList<Movie>) movies;
        } else {
            mMovies.addAll(movies);
        }
        notifyDataSetChanged();
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
        private ImageView mImageViewPoster;
        private TextView mTextViewTitle;
        private TextView mTextViewVote;
        private TextView mTextViewRelease;
        private TextView mTextViewRate;
        ViewHolder(View itemView) {
            super(itemView);
            mImageViewPoster = itemView.findViewById(R.id.image_poster);
            mTextViewTitle = itemView.findViewById(R.id.text_filmtitle_item);
            mTextViewRelease = itemView.findViewById(R.id.text_release_item);
            mTextViewVote = itemView.findViewById(R.id.text_vote_count_item);
            mTextViewRate = itemView.findViewById(R.id.text_rate_item);
        }

        public void bindData(Movie movie) {
           if (movie != null){
               Glide.with(getContext())
                       .load(Constant.IMG_URL + movie.getPosterPath())
                       .into(mImageViewPoster);
               mTextViewTitle.setText(movie.getTitle());
               mTextViewRelease.setText(Utils.dateFormat(movie.getReleaseDate()));
               mTextViewVote.setText(movie.getVoteCount());
               mTextViewRate.setText(Double.toString(movie.getVoteAverage()) + Constant.IMDB_RATE);
           }
        }
    }
}
