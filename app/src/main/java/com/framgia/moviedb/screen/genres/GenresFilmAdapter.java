package com.framgia.moviedb.screen.genres;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.Genres;
import com.framgia.moviedb.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nttungg on 3/8/18.
 */

public class GenresFilmAdapter extends BaseRecyclerViewAdapter<GenresFilmAdapter.ViewHolder> {
  private ArrayList<Genres> mGenres = new ArrayList<>();
  private ItemGenresClickListener mItemGenresClickListener;
  private LayoutInflater mLayoutInflater;

  public GenresFilmAdapter(Context context,ItemGenresClickListener itemClickListener) {
    super(context);
    mItemGenresClickListener = itemClickListener;
  }

  public void replaceData(@NonNull List<Genres> genres){
    if (genres != null){
       mGenres = (ArrayList<Genres>) genres;
    }
    notifyDataSetChanged();
  }

  @Override
  public GenresFilmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (mLayoutInflater == null){
       mLayoutInflater = LayoutInflater.from(parent.getContext());
    }
    View itemview = mLayoutInflater.inflate(R.layout.item_rv_genres,parent,false);
    return new ViewHolder(itemview,mItemGenresClickListener);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.bindData(mGenres.get(position));
  }

  @Override
  public int getItemCount() {
    return mGenres != null ? mGenres.size() : 0;
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewGenres;
    private Genres mGenres;
    ViewHolder(View itemView,final ItemGenresClickListener itemGenresClickListener) {
      super(itemView);
      textViewGenres = itemView.findViewById(R.id.text_title_genres);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          itemGenresClickListener.onItemGenresClicked(mGenres);
        }
      });
    }

    public void bindData(Genres genres) {
      if (genres != null){
        mGenres = genres;
        textViewGenres.setText(genres.getName());
      }
    }
  }

  /**
   * ItemClickListener
   */
  public interface ItemGenresClickListener {
    void onItemGenresClicked(Genres genres);
  }
}
