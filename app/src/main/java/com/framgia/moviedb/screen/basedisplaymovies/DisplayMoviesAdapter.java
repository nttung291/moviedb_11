package com.framgia.moviedb.screen.basedisplaymovies;

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
import com.framgia.moviedb.data.model.Movie;
import com.framgia.moviedb.screen.BaseRecyclerViewAdapter;
import com.framgia.moviedb.untils.Constant;

import java.util.ArrayList;
import java.util.List;

public class DisplayMoviesAdapter extends BaseRecyclerViewAdapter<DisplayMoviesAdapter.ViewHolder> {
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private ItemClickListener mItemClickListener;
    private LayoutInflater mLayoutInflater;

    public DisplayMoviesAdapter(Context context, ItemClickListener itemClickListener) {
        super(context);
        mItemClickListener = itemClickListener;
    }

    public void replaceData(@NonNull List<Movie> movies) {
        if (mMovies == null) {
            mMovies = (ArrayList<Movie>) movies;
        } else {
            mMovies.addAll(movies);
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        View itemview = mLayoutInflater.inflate(R.layout.item_rv_movie, parent, false);
        return new ViewHolder(itemview, mItemClickListener);
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
        private Movie mMovie;

        ViewHolder(View itemView, final ItemClickListener mItemClickListener) {
            super(itemView);
            mImageViewPoster = itemView.findViewById(R.id.image_movie_display);
            mTextViewTitle = itemView.findViewById(R.id.text_title_movie_display);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClicked(mMovie);
                }
            });
        }

        public void bindData(Movie movie) {
            if (movie != null) {
                mMovie = movie;
                Glide.with(getContext())
                        .load(Constant.IMG_URL + movie.getPosterPath())
                        .into(mImageViewPoster);
                mTextViewTitle.setText(movie.getTitle());
            }
        }
    }

    /**
     * ItemClickListener
     */
    public interface ItemClickListener {
        void onItemClicked(Movie movie);
    }
}
