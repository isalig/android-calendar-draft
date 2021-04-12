package io.aiico.playground.model

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import io.aiico.playground.R
import io.aiico.playground.databinding.ItemMonthHeaderBinding

class MonthTitleItem(private val title: String) : BindableItem<ItemMonthHeaderBinding>() {

    override fun getLayout(): Int = R.layout.item_month_header

    override fun initializeViewBinding(view: View) = ItemMonthHeaderBinding.bind(view)

    override fun bind(viewBinding: ItemMonthHeaderBinding, position: Int) {
        viewBinding.titleTextView.text = title
    }
}
