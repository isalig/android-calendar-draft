package io.aiico.playground

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupieAdapter

class CalendarFragment : Fragment(R.layout.calendar_fragment) {

    private lateinit var viewModel: CalendarViewModel

    private lateinit var calendarRecycler: RecyclerView

    private val adapter = GroupieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CalendarViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarRecycler = view.findViewById(R.id.calendar_recycler_view)
        calendarRecycler.setHasFixedSize(true)
        (calendarRecycler.layoutManager as LinearLayoutManager).apply {
            recycleChildrenOnDetach = true
            initialPrefetchItemCount = 20
        }
        viewModel.months.observe(viewLifecycleOwner) { items ->
            adapter.update(items)
            calendarRecycler.adapter ?: let { calendarRecycler.adapter = adapter }
        }
    }

    companion object {
        fun newInstance() = CalendarFragment()
    }
}
