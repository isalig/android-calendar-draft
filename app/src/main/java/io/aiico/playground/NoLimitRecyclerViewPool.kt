package io.aiico.playground

import android.util.SparseIntArray
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool


class NoLimitRecycledViewPool : RecycledViewPool() {

    private val scrapCount = SparseIntArray()
    private val maxScrap = SparseIntArray()
    override fun setMaxRecycledViews(viewType: Int, max: Int) {
        maxScrap.put(viewType, max)
        super.setMaxRecycledViews(viewType, max)
    }

    override fun getRecycledView(viewType: Int): RecyclerView.ViewHolder? {
        val r = super.getRecycledView(viewType)
        if (r != null) {
            val count = scrapCount[viewType, -1]
            check(count > 0) { "Not expected here. The #put call must be before" }
            scrapCount.put(viewType, count - 1)
        }
        return r
    }

    override fun putRecycledView(scrap: RecyclerView.ViewHolder) {
        val viewType = scrap.itemViewType
        val count = scrapCount[viewType, 0]
        scrapCount.put(viewType, count + 1)
        var max = maxScrap[viewType, -1]
        if (max == -1) {
            max = DEFAULT_MAX_SIZE
            setMaxRecycledViews(viewType, max)
        }
        if (count + 1 > max) {
            setMaxRecycledViews(viewType, count + 1)
        }
        super.putRecycledView(scrap)
    }

    override fun clear() {
        scrapCount.clear()
        maxScrap.clear()
        super.clear()
    }

    companion object {
        private const val DEFAULT_MAX_SIZE = 5
    }
}
