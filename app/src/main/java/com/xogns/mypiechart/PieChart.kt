package com.xogns.mypiechart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

data class ValueItem(var name: String, var value: Float, var color: Int)

class PieChartView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val WIDTH = 400
    private val HEIGHT = 400

    private var list = ArrayList<ValueItem>()

    fun setValueList(list: ArrayList<ValueItem>) {
        this.list = list
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        drawSlice(canvas)
        drawText(canvas)
    }

    private fun drawSlice(canvas: Canvas?) {
        val total = getTotalSize()
        val dAngle = 360.0F / total

        val centerX = measuredWidth / 2
        val centerY = measuredHeight / 2
        val left = centerX - WIDTH / 2
        val top = centerY - HEIGHT / 2
        val right = centerX + WIDTH / 2
        val bottom = centerY + HEIGHT / 2

        val rectF = RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())

        var fromAngle = 0.0F
        for (item in list) {
            val paint = Paint()
            paint.color = item.color

            val sweepAngle = item.value * dAngle
            val drawArc = canvas?.drawArc(rectF, fromAngle, sweepAngle, true, paint)

            fromAngle += sweepAngle
        }
    }

    private fun drawText(canvas: Canvas?) {
        val total = getTotalSize()
        val dAngle = 360.0F / total

        val centerX = measuredWidth / 2
        val centerY = measuredHeight / 2
        val left = centerX - WIDTH / 2
        val top = centerY - HEIGHT / 2
        val right = centerX + WIDTH / 2
        val bottom = centerY + HEIGHT / 2

        val rectF = RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())
        val rect = Rect(left, top, right, bottom)

        var fromAngle = 0.0F
        for (item in list) {
            val text = item.name
            val sweepAngle = item.value * dAngle
            val angle = (fromAngle + (sweepAngle / 2.0F)) * 0.0174532925F

            val paint = Paint()
            paint.color = Color.BLACK
            paint.textSize = 40F
            paint.textAlign = Paint.Align.CENTER

            canvas?.save()

            paint.getTextBounds(text, 0, text.length, rect)
            var x = rectF.centerX() + cos(angle) * (rectF.height() / 2)
            val y = rectF.centerY() + sin(angle) * (rectF.width() / 2)

            canvas?.drawLine(
                rectF.centerX(),
                rectF.centerY(),
                x,
                y,
                Paint()
            )
            canvas?.drawLine(
                x,
                y,
                if (x > rectF.centerX()) x + 100
                else x - 100,
                y,
                Paint()
            )
            canvas?.drawText(
                text,
                if (x > rectF.centerX()) x + 70
                else x - 70,
                y,
                paint
            )
            canvas?.restore()

            fromAngle += sweepAngle
        }
    }

    private fun getTotalSize(): Float {
        var sum = 0.0F

        for (item in list) {
            sum += item.value
        }

        return sum
    }
}