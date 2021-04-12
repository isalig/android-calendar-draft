package io.aiico.playground.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity.CENTER
import android.view.LayoutInflater
import android.widget.LinearLayout
import io.aiico.playground.databinding.ViewDayBinding
import io.aiico.playground.model.DayModel

class DayView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ViewDayBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        orientation = VERTICAL
        gravity = CENTER
    }

    fun bind(dayModel: DayModel) {
        with(binding) {
            dayNumberTextView.text = dayModel.dayText
            priceTextView.text = dayModel.priceText
        }
    }
}
