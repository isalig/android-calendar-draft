package io.aiico.playground.decoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupieAdapter
import io.aiico.playground.R
import io.aiico.playground.model.MonthTitleItem

class CalendarMonthSpaceDecoration(
    context: Context,
    private val adapter: GroupieAdapter
) : RecyclerView.ItemDecoration() {

    private val space = context.resources.getDimensionPixelSize(R.dimen.indent_m)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildViewHolder(view).adapterPosition
        if (position == RecyclerView.NO_POSITION) return

        when (adapter.getItem(position)) {
            is MonthTitleItem -> decorateTitleView(outRect)
//            is DayModel -> decorateDayView(outRect, position)
        }
    }

    private fun decorateDayView(outRect: Rect, position: Int) {

    }

    private fun decorateTitleView(outRect: Rect) {
        outRect.top = space
        outRect.right = space
        outRect.bottom = space
        outRect.left = space
    }
}
