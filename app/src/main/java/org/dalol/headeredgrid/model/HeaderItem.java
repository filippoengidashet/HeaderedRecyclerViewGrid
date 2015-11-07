package org.dalol.headeredgrid.model;

/**
 * @author Filippo Ash
 * @version 1.0.0
 * @date 11/7/2015
 */
public class HeaderItem extends Item {

    public HeaderItem(String title) {
        super(title);
    }

    @Override
    public int getItemType() {
        return HEADER_ITEM_TYPE;
    }
}
