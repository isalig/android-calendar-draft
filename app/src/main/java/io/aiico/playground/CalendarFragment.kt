package io.aiico.playground

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.xwray.groupie.GroupieAdapter
import io.aiico.playground.databinding.CalendarFragmentBinding
import io.aiico.playground.model.MonthItem

class CalendarFragment : Fragment(R.layout.calendar_fragment) {

    private val binding by viewBinding(CalendarFragmentBinding::bind)
    private lateinit var viewModel: CalendarViewModel

    private val recycledViewPool = NoLimitRecycledViewPool()
    private val adapter = GroupieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CalendarViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            calendarRecycler.setHasFixedSize(true)
            (calendarRecycler.layoutManager as LinearLayoutManager).apply {
                recycleChildrenOnDetach = true
            }
            viewModel.months.observe(viewLifecycleOwner) { items ->
                items
                    .mapNotNull { it as? MonthItem }
                    .forEach { it.recycledViewPool = recycledViewPool }
                adapter.updateAsync(items)
                calendarRecycler.adapter ?: let { calendarRecycler.adapter = adapter }
            }
        }
    }

    companion object {
        fun newInstance() = CalendarFragment()
    }
}
