package com.kingja.recyclerviewhelper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Description:TODO
 * Create Time:2017/4/14 15:23
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private final RecyclerViewHelper.OnItemCallback listenerAdapter;
    private boolean dragable;
    private boolean swipeable;

    public SimpleItemTouchHelperCallback(RecyclerViewHelper.OnItemCallback listenerAdapter, boolean dragable, boolean
            swipeable) {
        this.listenerAdapter = listenerAdapter;
        this.dragable = dragable;
        this.swipeable = swipeable;
    }


    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /**
     * 设置位置变化方向
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //如果为0则不能拖动或者滑动
        int dragFlags;
        int swipeFlags;
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }

        return ItemTouchHelper.Callback.makeMovementFlags(dragable ? dragFlags : 0, swipeable ? swipeFlags : 0);
    }

    /**
     * 拖动改变位置
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
            target) {
        listenerAdapter.onMove(viewHolder.getAdapterPosition(),
                target.getAdapterPosition());
        return true;
    }

    /**
     * 滑动
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listenerAdapter.onSwipe(viewHolder.getAdapterPosition());
    }

    /**
     * 长按选中时
     *
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setAlpha(0.5f);
        }
    }

    /**
     * 拖拽完成时
     *
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setAlpha(1.0f);
    }
}
