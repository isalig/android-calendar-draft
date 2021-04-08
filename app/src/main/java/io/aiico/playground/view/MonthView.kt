package io.aiico.playground.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.CalendarView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import io.aiico.playground.R

typealias MonthWeekIndex = Int
typealias WeekDayViews = List<DayView>

private const val WEEKS_COUNT = 5
private const val WEEK_DAYS_COUNT = 7

class MonthView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val titleTextView: TextView
    private val daysGrid: MutableMap<MonthWeekIndex, WeekDayViews> = HashMap()

    init {
        inflate(context, R.layout.view_month, this)
        titleTextView = findViewById(R.id.title_text_view)
        addDaysGrid()
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun addDaysGrid() {
        var topBarrierView: View = titleTextView
        repeat(WEEKS_COUNT) { weekIndex ->
            val dayViews = buildList(WEEK_DAYS_COUNT) {
                var prevDayView: DayView? = null
                repeat(WEEK_DAYS_COUNT) { weekDayIndex ->
                    val dayView = createDayView(weekDayIndex, topBarrierView, prevDayView)
                    prevDayView = dayView
                    add(dayView)
                }
            }
            dayViews.forEach(::addView)
            topBarrierView = dayViews.first()
            daysGrid[weekIndex] = dayViews
        }
    }

    private fun createDayView(weekDayIndex: Int, viewAbove: View, viewBefore: View?) =
        DayView(context).apply {
            id = View.generateViewId()
            layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                topToBottom = viewAbove.id
                if (weekDayIndex == 0) {
                    startToStart = ConstraintSet.PARENT_ID
                }
                if (weekDayIndex == WEEK_DAYS_COUNT - 1) {
                    endToEnd = ConstraintSet.PARENT_ID
                }
                if (viewBefore != null) {
                    startToEnd = viewBefore.id
                }
            }
        }
}
