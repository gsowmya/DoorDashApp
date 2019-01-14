package assignment.doordash.com.doordashapp.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import assignment.doordash.com.doordashapp.R;
import assignment.doordash.com.doordashapp.activity.dataholders.ListDataHolder;
import assignment.doordash.com.doordashapp.activity.dataholders.RestaurantListItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sowmya on 1/12/19.
 */

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantListViewHolder> {
    Context context;
    ListDataHolder listDataHolder;

    @Inject
    public RestaurantListAdapter(Context context,ListDataHolder listDataHolder){
        this.context = context;
        this.listDataHolder = listDataHolder;
    }

    @Override
    public int getItemCount() {
        return listDataHolder.getRestaurantListItemList().size();
    }

    public ListDataHolder getListDataHolder(){
        return listDataHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantListViewHolder holder, int position) {
        final RestaurantListItem restaurantListItem = listDataHolder.getRestaurantListItemList().get(position);
        holder.imageViewCoverUrl.setImageResource(0);
        Picasso.with(context)
                .load(restaurantListItem.getImageURL())
                .into(holder.imageViewCoverUrl);

        holder.txtRestaurantName.setText(restaurantListItem.getName());
        holder.txtRestaurantDescription.setText(restaurantListItem.getDescription());
        holder.txtStatus.setText(restaurantListItem.getWaitTime());
    }

    @Override
    public RestaurantListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurent_item_adapter_row, parent, false);
        return new RestaurantListViewHolder(itemView);
    }

    public class RestaurantListViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imgCoverUrl)
        ImageView imageViewCoverUrl;

        @BindView(R.id.txtRestaurantName)
        TextView txtRestaurantName;

        @BindView(R.id.txtRestaurantDescription)
        TextView txtRestaurantDescription;

        @BindView(R.id.txtStatus)
        TextView txtStatus;

        public RestaurantListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
