package io.aiico.playground

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xwray.groupie.Group
import io.aiico.playground.model.DayModel
import io.aiico.playground.model.MonthItem
import io.aiico.playground.model.MonthTitleItem
import io.aiico.playground.model.WeekItem
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate

private const val WEEKS_COUNT = 5
private const val DAYS_COUNT = 7
private val START_OF_WEEK = DayOfWeek.MONDAY

class CalendarViewModel : ViewModel() {

    val months = MutableLiveData<List<Group>>()

    init {
        Thread {
            months.postValue(getMonths())
        }.start()
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun getMonths(): List<Group> = buildList {
        val currentDate = LocalDate.now()
        repeat(12) { monthOffset ->
            add(buildMonth(currentDate.plusMonths(monthOffset.toLong())))
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun buildMonth(anchorDate: LocalDate): MonthItem = buildList<Group> {
        add(MonthTitleItem(anchorDate.month.name))

        var startDate = LocalDate.of(anchorDate.year, anchorDate.month.value, 1)
        while (startDate.dayOfWeek != START_OF_WEEK || startDate.month == anchorDate.month) {
            startDate = startDate.minusDays(1)
        }

        List(WEEKS_COUNT) { weekIndex ->
            add(List(DAYS_COUNT) { dayIndex ->
                DayModel(startDate.dayOfMonth.toString(), "1200", startDate)
                    .also { startDate = startDate.plusDays(1) }
            }.let(::WeekItem))
        }
    }.let { MonthItem(it) }
}
