package net.dgg.lib.base.imageloader

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes


/**
 * Created by dgg on 2017/11/3.
 */
class ImageConfiguration private constructor(builder: Builder) {
    // 边框宽度
    private var mStrokeWidth: Int
    // 边框颜色
    private val mStrokeColor: Int

    // 圆角
    private val mRound: Int
    // 圆图
    private val circleCrop: Boolean

    // 占位图
    private val placeholderRes: Int
    // 错误占位图
    private val errorHolderRes: Int
    // 占位图
    private val placeholderDrawable: Drawable?
    // 错误占位图
    private val errorHolderDrawable: Drawable?

    fun createSimple(): ImageConfiguration {
        return Builder().build()
    }

    init {
        this.mStrokeColor = builder.mStrokeColor
        this.mStrokeWidth = builder.mStrokeWidth
        this.mRound = builder.mRound
        this.circleCrop = builder.circleCrop
        this.placeholderRes = builder.placeholderRes
        this.errorHolderRes = builder.errorHolderRes
        this.placeholderDrawable = builder.placeholderDrawable
        this.errorHolderDrawable = builder.errorHolderDrawable
    }

    fun getPlaceholderDrawable(resources: Resources): Drawable? {
        return if (placeholderRes != 0) getDrawable(resources, placeholderRes) else placeholderDrawable
    }

    private fun getDrawable(resources: Resources, drawableRes: Int): Drawable? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            resources.getDrawable(drawableRes, null)
        } else {
            resources.getDrawable(drawableRes)
        }
    }

    fun getErrorHolderDrawable(resources: Resources): Drawable? {
        return if (errorHolderRes != 0) getDrawable(resources, errorHolderRes) else errorHolderDrawable
    }

    fun isCircleCrop(): Boolean {
        return circleCrop
    }

    fun getRound(): Int {
        return mRound
    }

    fun hasRound(): Boolean {
        return mRound > 0
    }

    fun hasStroke(): Boolean {
        return mStrokeWidth > 0
    }

    fun getStrokeColor(): Int {
        return mStrokeColor
    }

    fun getStrokeWidth(): Int {
        return mStrokeWidth
    }

    class Builder {
        // 边框宽度
        var mStrokeWidth: Int = 0
        // 边框颜色
        var mStrokeColor: Int = 0

        // 圆角
        var mRound: Int = 0
        // 圆图
        var circleCrop: Boolean = false

        // 占位图
        var placeholderRes: Int = 0
        // 错误占位图
        var errorHolderRes: Int = 0
        // 占位图
        var placeholderDrawable: Drawable? = null
        // 错误占位图
        var errorHolderDrawable: Drawable? = null

        fun setStrokeWidth(mStrokeWidth: Int): Builder {
            this.mStrokeWidth = mStrokeWidth
            return this
        }

        fun setStrokeColor(mStrokeColor: Int): Builder {
            this.mStrokeColor = mStrokeColor
            return this
        }

        fun round(mRound: Int): Builder {
            this.mRound = mRound
            return this
        }

        fun circleCrop(): Builder {
            this.circleCrop = true
            return this
        }

        fun placeholder(@DrawableRes placeholderRes: Int): Builder {
            this.placeholderRes = placeholderRes
            return this
        }

        fun errorholder(@DrawableRes errorHolderRes: Int): Builder {
            this.errorHolderRes = errorHolderRes
            return this
        }

        fun placeholder(placeholderDrawable: Drawable): Builder {
            this.placeholderDrawable = placeholderDrawable
            return this
        }

        fun errorholder(errorHolderDrawable: Drawable): Builder {
            this.errorHolderDrawable = errorHolderDrawable
            return this
        }

        fun stroke(width: Int, @ColorInt color: Int): Builder {
            setStrokeWidth(width)
            setStrokeColor(color)
            return this
        }

        fun build(): ImageConfiguration {
            return ImageConfiguration(this)
        }
    }
}