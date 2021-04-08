package io.aiico.playground

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import io.aiico.playground.model.MonthModel
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate

private const val MONTH_CAPACITY = 35
private val START_OF_WEEK = DayOfWeek.MONDAY

class CalendarViewModel : ViewModel() {

    val months = MutableLiveData<List<Item<out GroupieViewHolder>>>()

    init {
        Thread {
            months.postValue(getMonths())
        }.start()
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun getMonths(): List<Item<out GroupieViewHolder>> = buildList {
        val currentDate = LocalDate.now()
        repeat(20) { monthOffset ->
            buildMonth(currentDate.plusMonths(monthOffset.toLong()))
        }
    }

    private fun MutableList<Item<out GroupieViewHolder>>.buildMonth(anchorDate: LocalDate) {
        add(MonthModel(anchorDate.month.name))

        var startDate = LocalDate.of(anchorDate.year, anchorDate.month.value, 1)
        while (startDate.dayOfWeek != START_OF_WEEK || startDate.month == anchorDate.month) {
            startDate = startDate.minusDays(1)
        }

        repeat(MONTH_CAPACITY) {
//            add(DayModel(startDate.dayOfMonth.toString(), "1200", startDate))
            startDate = startDate.plusDays(1)
        }
    }
}
