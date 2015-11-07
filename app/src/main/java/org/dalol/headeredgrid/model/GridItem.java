package org.dalol.headeredgrid.model;

/**
 * @author Filippo Ash
 * @version 1.0.0
 * @date 11/7/2015
 */
public class GridItem extends Item {

    private int mPosition;
    private String mSubTitle;

    public GridItem(String title, int position) {
        super(title);
        mPosition = position;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public void setSubTitle(String subTitle) {
        mSubTitle = subTitle;
    }

    public int getPosition() {
        return mPosition;
    }

    @Override
    public int getItemType() {
        return GRID_ITEM_TYPE;
    }
}