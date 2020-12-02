package com.superherosquadmaker.core.ui

import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setDivider(drawable: Int, orientation: Int = DividerItemDecoration.VERTICAL) {
    val divider = DividerItemDecoration(context, orientation)
    AppCompatResources.getDrawable(context, drawable)?.let {
        divider.setDrawable(it)
    }
    addItemDecoration(divider)
}

fun <T> autoNotify(
    old: List<T>,
    new: List<T>,
    compare: (T, T) -> Boolean
): DiffUtil.DiffResult {
    return DiffUtil.calculateDiff(object : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return compare(old[oldItemPosition], new[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition] == new[newItemPosition]
        }

        override fun getOldListSize() = old.size

        override fun getNewListSize() = new.size
    })
}