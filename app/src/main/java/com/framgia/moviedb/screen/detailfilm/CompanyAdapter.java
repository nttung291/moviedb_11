package com.framgia.moviedb.screen.detailfilm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.Company;
import com.framgia.moviedb.screen.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class CompanyAdapter extends BaseRecyclerViewAdapter<CompanyAdapter.ViewHolder> {
    private ArrayList<Company> mCompanies = new ArrayList<>();
    private ItemCompanyClickListener mItemCompanyClickListener;
    private LayoutInflater mLayoutInflater;

    public CompanyAdapter(Context context, ItemCompanyClickListener itemClickListener) {
        super(context);
        mItemCompanyClickListener = itemClickListener;
    }

    public void replaceData(@NonNull List<Company> companies){
        if (companies != null){
            mCompanies = (ArrayList<Company>) companies;
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null){
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        View itemview = mLayoutInflater.inflate(R.layout.item_rv_company_genres_detail,parent,false);
        return new ViewHolder(itemview,mItemCompanyClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mCompanies.get(position));
    }

    @Override
    public int getItemCount() {
        return mCompanies != null ? mCompanies.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewCompany;
        private Company mCompany;
        ViewHolder(View itemView,final ItemCompanyClickListener itemCompanyClickListener) {
            super(itemView);
            textViewCompany = itemView.findViewById(R.id.text_detail_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemCompanyClickListener.onItemCompanyClicked(mCompany);
                }
            });
        }

        public void bindData(Company company) {
           if (company != null){
               mCompany = company;
               textViewCompany.setText(company.getName());
           }
        }
    }

    /**
     * ItemClickListener
     */
    public interface ItemCompanyClickListener {
        void onItemCompanyClicked(Company company);
    }
}
