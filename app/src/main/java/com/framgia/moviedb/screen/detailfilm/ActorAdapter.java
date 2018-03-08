package com.framgia.moviedb.screen.detailfilm;

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
import com.framgia.moviedb.data.model.Actor;
import com.framgia.moviedb.screen.BaseRecyclerViewAdapter;
import com.framgia.moviedb.untils.Constant;
import java.util.ArrayList;
import java.util.List;

public class ActorAdapter extends BaseRecyclerViewAdapter<ActorAdapter.ViewHolder> {
    private ArrayList<Actor> mActors = new ArrayList<>();
    private ItemActorClickListener mItemActorClickListener;
    private LayoutInflater mLayoutInflater;

    public ActorAdapter(Context context, ItemActorClickListener itemActorClickListener) {
        super(context);
        mItemActorClickListener = itemActorClickListener;
    }

    public void replaceData(@NonNull List<Actor> actors){
        if (actors != null){
            mActors = (ArrayList<Actor>) actors;
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null){
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        View itemview = mLayoutInflater.inflate(R.layout.item_rv_actor_detail,parent,false);
        return new ViewHolder(itemview,mItemActorClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mActors.get(position));
    }

    @Override
    public int getItemCount() {
        return mActors != null ? mActors.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewActorName;
        private ImageView imageViewProfile;
        private Actor mActor;
        ViewHolder(View itemView,final ItemActorClickListener itemActorClickListener) {
            super(itemView);
            textViewActorName = itemView.findViewById(R.id.text_actor_name_detail);
            imageViewProfile = itemView.findViewById(R.id.image_actor_profile_detail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemActorClickListener.onItemActorClicked(mActor);
                }
            });
        }

        public void bindData(Actor actor) {
           if (actor != null){
               mActor = actor;
               textViewActorName.setText(actor.getName());
               if (actor.getProfilePath() !=null){
                   Glide.with(getContext())
                           .load(Constant.IMG_URL + actor.getProfilePath())
                           .into(imageViewProfile);
               }
           }
        }
    }

    /**
     * ItemClickListener
     */
    public interface ItemActorClickListener {
        void onItemActorClicked(Actor actor);
    }
}
