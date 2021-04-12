package io.aiico.playground.model

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.Group
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.viewbinding.BindableItem
import io.aiico.playground.R
import io.aiico.playground.databinding.ItemMonthBinding

class MonthItem(
    private val nestedItems: List<Group>
) : BindableItem<ItemMonthBinding>() {

    var recycledViewPool: RecyclerView.RecycledViewPool? = null

    override fun getLayout(): Int = R.layout.item_month

    override fun initializeViewBinding(view: View) = ItemMonthBinding
        .bind(view)
        .also { binding -> binding.initNestedRecycler() }

    private fun ItemMonthBinding.initNestedRecycler() {
        (monthRecycler.layoutManager as LinearLayoutManager).recycleChildrenOnDetach = true
        recycledViewPool?.let(monthRecycler::setRecycledViewPool)
        monthRecycler.adapter = GroupieAdapter()
    }

    override fun bind(viewBinding: ItemMonthBinding, position: Int) {
        with(viewBinding) {
            (monthRecycler.layoutManager as LinearLayoutManager).initialPrefetchItemCount = 5
            (monthRecycler.adapter as GroupieAdapter).update(nestedItems)
        }
    }
}
