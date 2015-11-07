package org.dalol.headeredgrid.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.dalol.headeredgrid.R;
import org.dalol.headeredgrid.model.GridItem;
import org.dalol.headeredgrid.model.Item;
import org.dalol.headeredgrid.view.Holder;

import java.util.List;

/**
 * @author Filippo Ash
 * @version 1.0.0
 * @date 11/7/2015
 */
public class GridListAdapter extends RecyclerView.Adapter<Holder> {

    private final int mDefaultSpanCount;
    private List<Item> mItemList;

    public GridListAdapter(List<Item> itemList, GridLayoutManager gridLayoutManager, int defaultSpanCount) {
        mItemList = itemList;
        mDefaultSpanCount = defaultSpanCount;
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return isHeaderType(position) ? mDefaultSpanCount : 1;
            }
        });
    }

    private boolean isHeaderType(int position) {
        return mItemList.get(position).getItemType() == Item.HEADER_ITEM_TYPE ? true : false;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view;

        if(viewType == 0) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_type_layout, viewGroup, false);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_type_layout, viewGroup, false);
        }

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if(isHeaderType(position)) {
            bindHeaderItem(holder, position);
        } else {
            bindGridItem(holder, position);
        }
    }

    /**
     * This method is used to bind grid item value
     *
     * @param holder
     * @param position
     */
    private void bindGridItem(Holder holder, int position) {

        View container = holder.itemView;

        TextView title = (TextView) container.findViewById(R.id.gridTitle);
        TextView count = (TextView) container.findViewById(R.id.gridCount);

        final GridItem item = (GridItem) mItemList.get(position);

        title.setText(item.getItemTitle());
        count.setText(Integer.toString(item.getPosition()));

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "You clicked on the " + item.getItemTitle() + " at Position -> " + item.getPosition() + "!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This method is used to bind the header with the corresponding item position information
     *
     * @param holder
     * @param position
     */
    private void bindHeaderItem(Holder holder, int position) {
        TextView title = (TextView) holder.itemView.findViewById(R.id.headerTitle);
        title.setText(mItemList.get(position).getItemTitle());
    }

    @Override
    public int getItemViewType(int position) {
        return mItemList.get(position).getItemType() == Item.HEADER_ITEM_TYPE ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    /**
     * This method is used to add an item into the recyclerview list
     *
     * @param item
     */
    public void addItem(Item item) {
        mItemList.add(item);
        notifyDataSetChanged();
    }

    /**
     * This method is used to remove items from the list
     *
     * @param item {@link Item}
     */
    public void removeItem(Item item) {
        mItemList.remove(item);
        notifyDataSetChanged();
    }
}
