package com.sunil.multityperecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunil on 11/26/16.
 */

public class MultiTypeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemModel> itemModels;
    private Context context;
    private int HEADER_TYPE =0;
    private int FOOTER_TYPE = 1;
    private int ONE_COLUMN_ROW_TYPE = 2;
    private int TWO_COLUMN_ROW_TYPE =3;

    public MultiTypeRecyclerAdapter(Context context, List<ItemModel> wallTalls) {
        this.itemModels = wallTalls;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return itemModels.size()+2;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0){
            return HEADER_TYPE;
        }else if (position == itemModels.size()+1){
            return FOOTER_TYPE;
        }else if (position % 2 == 0){
            return ONE_COLUMN_ROW_TYPE;
        }else{
            return TWO_COLUMN_ROW_TYPE;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = null;
        if (viewType == HEADER_TYPE){
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_view, viewGroup, false);
            return new HeaderViewHolder(itemView);
        }else if (viewType == FOOTER_TYPE){
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.footer_view, viewGroup, false);
            return new FooterViewHolder(itemView);
        }else if (viewType == ONE_COLUMN_ROW_TYPE){
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_one_column, viewGroup, false);
            return new OneColumnViewHolder(itemView);
        }else if (viewType == TWO_COLUMN_ROW_TYPE){
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_two_column, viewGroup, false);
            return new TwoColumnViewHolder(itemView);
        }else{
            // default one column row
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_one_column, viewGroup, false);
            return new OneColumnViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeaderViewHolder){

        }else if (holder instanceof FooterViewHolder){


        }else if (holder instanceof OneColumnViewHolder){

            ItemModel model = itemModels.get(position-1);
            String imageUrl = model.getImagePath();
            if (imageUrl != null && !imageUrl.isEmpty()){
                Glide.with(context)
                        .load(imageUrl)
                        // .placeholder(R.drawable.i)
                        .into(((OneColumnViewHolder)holder).imageView);

            }
            ((OneColumnViewHolder)holder).name.setText(model.getName());

        }else if (holder instanceof TwoColumnViewHolder){

            ItemModel model = itemModels.get(position-1);
            String imageUrl = model.getImagePath();
            if (imageUrl != null && !imageUrl.isEmpty()){
                Glide.with(context)
                        .load(imageUrl)
                        .into(((TwoColumnViewHolder)holder).imageView);

                Glide.with(context)
                        .load(imageUrl)
                        .into(((TwoColumnViewHolder)holder).imageView1);


            }
            ((TwoColumnViewHolder)holder).name.setText(model.getName());
            ((TwoColumnViewHolder)holder).name1.setText(model.getName());

        }else{

            ItemModel model = itemModels.get(position-1);
            String imageUrl = model.getImagePath();
            if (imageUrl != null && !imageUrl.isEmpty()){
                Glide.with(context)
                        .load(imageUrl)
                        // .placeholder(R.drawable.i)
                        .into(((OneColumnViewHolder)holder).imageView);

            }
            ((OneColumnViewHolder)holder).name.setText(model.getName());
        }

    }


    public static class OneColumnViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.imageView)
        ImageView imageView;

        public OneColumnViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class TwoColumnViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.imageView)
        ImageView imageView;

        @BindView(R.id.name1)
        TextView name1;
        @BindView(R.id.imageView1)
        ImageView imageView1;

        public TwoColumnViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.headerView)
        TextView header;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public static class FooterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.footerView)
        TextView footer;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
