package br.com.animes.core.custom.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import br.com.animes.base_feature.R
import br.com.animes.base_feature.databinding.WidgetLoadingButtonBinding


class LoadingButton : ConstraintLayout {
    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initAttrs(attrs, defStyleAttr)
    }

    private val binding = WidgetLoadingButtonBinding.inflate(LayoutInflater.from(context), this)

    var text: String? = null
        set(value) {
            binding.widgetLoadingButton.text = value
            field = value
        }

    var isLoading = false
        set(value) {
            if (value) startLoading() else stopLoading()
            field = value
        }

    private fun initAttrs(
        attrs: AttributeSet?,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
    ) {
        attrs ?: return
        val attributeValues = context.obtainStyledAttributes(attrs, R.styleable.LoadingButton, defStyleAttr, defStyleRes)
        with(attributeValues) {
            try {
                isLoading = getBoolean(R.styleable.LoadingButton_loading, false)
                text = getString(R.styleable.LoadingButton_android_text).orEmpty()
            } finally {
                recycle()
            }
        }
    }

    override fun performClick(): Boolean {
        binding.widgetLoadingButton.performClick()
        return super.performClick()
    }

    override fun setOnClickListener(l: OnClickListener?) {
        binding.widgetLoadingButton.setOnClickListener(l)
    }

    private fun startLoading() {
        binding.apply {
            widgetLoadingButtonProgressBar.isVisible = true
            widgetLoadingButton.isClickable = false
            widgetLoadingButton.text = ""
        }
    }

    private fun stopLoading() {
        binding.apply {
            widgetLoadingButtonProgressBar.isVisible = false
            widgetLoadingButton.isClickable = true
            widgetLoadingButton.text = text
        }
    }
}