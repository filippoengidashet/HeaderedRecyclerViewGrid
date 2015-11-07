package org.dalol.headeredgrid.model;

/**
 * @author Filippo Ash
 * @version 1.0.0
 * @date 11/7/2015
 */
public abstract class Item {

    public static final int HEADER_ITEM_TYPE = 0;
    public static final int GRID_ITEM_TYPE = 1;
    private String mItemTitle;

    public Item(String title) {
        mItemTitle = title;
    }

    public String getItemTitle() {
        return mItemTitle;
    }

    public abstract int getItemType();
}
