package boiled.eu.stopify.view

import android.content.Context
import android.support.annotation.AttrRes
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView

class TimerView : FrameLayout {
    private val progressBar: CircularProgressBar
    private val timerText: TextView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        progressBar = CircularProgressBar(context, attrs, defStyleAttr)
        timerText = TextView(context, attrs, defStyleAttr)
    }
}
