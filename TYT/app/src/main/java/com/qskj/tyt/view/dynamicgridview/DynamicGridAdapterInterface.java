package com.qskj.tyt.view.dynamicgridview;

/**
 *
 * Created by 赵鑫 on 2015/8/25.
 */

/**
 * Any adapter used with DynamicGridView must implement DynamicGridAdapterInterface.
 * Adapter implementation also must has stable items id.
 * See {@link org.askerov.dynamicgrid.AbstractDynamicGridAdapter} for stable id implementation example.
 */

public interface DynamicGridAdapterInterface {

    /**
     * Determines how to reorder items dragged from <code>originalPosition</code> to <code>newPosition</code>
     */
    void reorderItems(int originalPosition, int newPosition);

    /**
     * @return return columns number for GridView. Need for compatibility
     * (@link android.widget.GridView#getNumColumns() requires api 11)
     */
    int getColumnCount();

    /**
     * Determines whether the item in the specified <code>position</code> can be reordered.
     */
    boolean canReorder(int position);

}
