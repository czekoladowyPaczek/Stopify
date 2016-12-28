package boiled.eu.stopify.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import boiled.eu.stopify.R

class CircularProgressBar : View {
    private val startAngle: Float = -90f
    private val strokeWidth: Float = 10f
    private val rectF: RectF = RectF()
    private val foregroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val backgroundPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var progress: Float = 0f
        get() = progress
        set(value) {
            field = value
            invalidate()
        }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.attr.circularProgressBarStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttrs: Int) : super(context, attrs, defStyleAttrs) {
        foregroundPaint.style = Paint.Style.STROKE
        foregroundPaint.strokeWidth = strokeWidth
        backgroundPaint.style = Paint.Style.STROKE
        backgroundPaint.strokeWidth = strokeWidth

        val typedArray: TypedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CircularProgressBar, defStyleAttrs, 0)
        try {
            progress = typedArray.getFloat(R.styleable.CircularProgressBar_progress, progress)
            foregroundPaint.color = typedArray.getColor(R.styleable.CircularProgressBar_foregroundColor, Color.WHITE)
            backgroundPaint.color = typedArray.getColor(R.styleable.CircularProgressBar_backgroundColor, getAlphaColor(Color.WHITE))
        } finally {
            typedArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawOval(rectF, backgroundPaint)
        val angle = 360 * progress / 100
        canvas.drawArc(rectF, startAngle, angle, false, foregroundPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val min = Math.min(width, height)
        setMeasuredDimension(min, min)

        val offset = strokeWidth / 2
        rectF.set(offset, offset, min - offset, min - offset)
    }

    private fun getAlphaColor(color: Int): Int = Color.argb(200, Color.red(color), Color.green(color), Color.blue(color))
}
