package com.lokarz.funnycatcompilation.Utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Karlotoy on 1/8/2018.
 */

object ViewUtil {

    val REQ_CODE = 99

    fun showView(show: Boolean, vararg views: View) {

        for (v in views) {
            if (v != null) {
                var visibilityState = View.GONE
                if (show) {
                    visibilityState = View.VISIBLE
                }
                v.visibility = visibilityState
            }
        }
    }

    fun hideViewSlideUp(view: View) {
        if (view.getTag(view.id) == null) {
            view.setTag(view.id, "")


        }
        if (view.height != 0 && isViewsVisible(view) && "".equals(view.getTag(view.id).toString(), ignoreCase = true)) {
            //        if (view.getHeight() != 0 && isViewsVisible(view)){
            view.setTag(view.id, "animating")
            view.animate()
                .translationY((-view.height).toFloat())
                .alpha(0.0f)
                .setDuration(400)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        view.setTag(view.id, "")
                        view.clearAnimation()
                        showView(false, view)
                    }
                })
        }
    }

    fun showViewSlideDown(view: View) {
        if (view.getTag(view.id) == null) {
            view.setTag(view.id, "")

        }

        if (view.height != 0 && !isViewsVisible(view) && "".equals(
                view.getTag(view.id).toString(),
                ignoreCase = true
            )
        ) {
            //        if (view.getHeight() != 0 && !isViewsVisible(view)){
            view.setTag(view.id, "animating")
            showView(true, view)
            view.animate()
                .translationY(0f)
                .alpha(1.0f)
                .setDuration(400)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        view.setTag(view.id, "")
                    }
                })
        }
    }

    fun hideViewSlideDown(view: View) {
        if (view.getTag(view.id) == null) {
            view.setTag(view.id, "")
        }
        if (view.height != 0 && isViewsVisible(view) && "".equals(view.getTag(view.id).toString(), ignoreCase = true)) {
            //        if (view.getHeight() != 0 && isViewsVisible(view)){
            view.setTag(view.id, "animating")
            view.animate()
                .translationY(view.height.toFloat())
                .alpha(0.0f)
                .setDuration(400)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        view.clearAnimation()
                        showView(false, view)
                        view.setTag(view.id, "")

                    }
                })
        }
    }

    fun showViewSlideUp(view: View) {
        if (view.getTag(view.id) == null) {
            view.setTag(view.id, "")
        }
        if (view.height != 0 && !isViewsVisible(view) && "".equals(
                view.getTag(view.id).toString(),
                ignoreCase = true
            )
        ) {
            view.setTag(view.id, "animating")
            showView(true, view)
            view.animate()
                .translationY(0f)
                .alpha(1.0f)
                .setDuration(400)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        view.setTag(view.id, "")

                    }
                })
        }
    }

    fun showViewInvi(show: Boolean, vararg views: View) {

        for (v in views) {
            var visibilityState = View.INVISIBLE
            if (show) {
                visibilityState = View.VISIBLE
            }
            v.visibility = visibilityState
        }
    }

    fun showViews(vararg v: View) {
        showView(true, *v)
    }

    fun toggleViews(vararg views: View) {
        for (view in views) {
            showView(!isViewsVisible(view), view)
        }
    }


    fun isViewsVisible(vararg views: View): Boolean {
        var ret = true

        for (view in views) {
            ret = view.visibility == View.VISIBLE

            if (!ret) {
                break
            }
        }
        return ret
    }

    fun isAViewVisible(vararg views: View): Boolean {
        var ret = false

        for (view in views) {
            ret = view.visibility == View.VISIBLE

            if (ret) {
                break
            }
        }
        return ret
    }

    fun dpToPx(dp: Int, context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun setViewPadding(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        val context = view.context
        view.setPadding(dpToPx(left, context), dpToPx(top, context), dpToPx(right, context), dpToPx(bottom, context))
    }

    fun gotoScreen(context: Context, intent: Intent) {
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        context.startActivity(intent)
    }

    @JvmOverloads
    fun gotoScreen(context: Context, mClazz: Class<*>, args: Bundle? = null) {
        val intent = Intent(context, mClazz)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent.putExtra(ConstantUtil.PREV_ACTIVITY, context.javaClass.simpleName)
        if (args != null) {
            intent.putExtras(args)
        }
        context.startActivity(intent)
    }

    fun popUpScreen(context: Context, mClazz: Class<*>, requestCode: Int) {
        popUpScreen(context, mClazz, null, requestCode)
    }

    @JvmOverloads
    fun popUpScreen(context: Context, mClazz: Class<*>, args: Bundle? = null, requestCode: Int = REQ_CODE) {
        val intent = Intent(context, mClazz)
        intent.putExtra(ConstantUtil.PREV_ACTIVITY, context.javaClass.simpleName)
        if (args != null) {
            intent.putExtras(args)
        }
        try {
            (context as AppCompatActivity).startActivityForResult(intent, requestCode)
        } catch (e: Exception) {
            // do nothing
        }

    }

    @JvmOverloads
    fun popUpScreen(fragment: Fragment, mClazz: Class<*>, args: Bundle?, requestCode: Int = REQ_CODE) {
        val intent = Intent(fragment.context, mClazz)
        val context = fragment.context
        if (context != null) {
            intent.putExtra(ConstantUtil.PREV_ACTIVITY, fragment.context!!.javaClass.simpleName)
        }
        if (args != null) {
            intent.putExtras(args)
        }
        try {
            fragment.startActivityForResult(intent, requestCode)
        } catch (e: Exception) {
            // do nothing
        }

    }

    fun popUpScreen(view: View, mClazz: Class<*>) {
        popUpScreen(view.context, mClazz, null)
    }

    fun addFragment(fm: FragmentManager, fragment: Fragment?, destination: Int) {
        val ft = fm.beginTransaction()
        if (fragment != null) {
            ft.replace(destination, fragment)
            ft.addToBackStack(fragment.javaClass.simpleName)
            try {
                ft.commit()
            } catch (e: Exception) {
                // do nothing
            }

        }
    }

    // for race registration
    fun disableRegisterButton(view: Button, btnLabelId: Int) {
        view.setText(btnLabelId)
        view.backgroundTintList = ColorStateList.valueOf(view.context.resources.getColor(android.R.color.darker_gray))
        view.isEnabled = false
    }

    @SuppressLint("CheckResult")
    fun setRxViewOnClickListener(view: View, viewsOnClickListener: View.OnClickListener) {
        RxView.clicks(view).throttleFirst(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { viewsOnClickListener.onClick(view) }
    }

    fun setViewsOnClickListener(view: AppCompatActivity, viewsOnClickListener: View.OnClickListener, vararg ids: Int) {
        for (id in ids) {
            val targetView = view.findViewById<View>(id)
            if (targetView != null) {
                setRxViewOnClickListener(targetView, viewsOnClickListener)
            }

        }
    }

    fun setViewsOnClickListener(viewsOnClickListener: View.OnClickListener, vararg views: View) {
        for (view in views) {
            setRxViewOnClickListener(view, viewsOnClickListener)
        }
    }

    fun setViewsOnClickListener(view: View, viewsOnClickListener: View.OnClickListener, vararg ids: Int) {
        for (id in ids) {
            val targetView = view.findViewById<View>(id)
            if (targetView != null)
                setRxViewOnClickListener(targetView, viewsOnClickListener)
        }
    }

    fun setSelected(viewGroup: ViewGroup, isSelected: Boolean) {
        viewGroup.isSelected = isSelected
        for (i in 0 until viewGroup.childCount) {
            viewGroup.getChildAt(i).isSelected = isSelected
        }
    }

    fun clearSelected(viewGroup: ViewGroup) {
        for (i in 0 until viewGroup.childCount) {
            viewGroup.getChildAt(i).isSelected = false
        }
    }

    fun setClickableString(onClickListener: View.OnClickListener, clickableString: String, textView: TextView) {
        setClickableString(onClickListener, 0, false, clickableString, textView)
    }

    fun setClickableString(
        onClickListener: View.OnClickListener, @ColorRes color: Int,
        withUnderline: Boolean,
        clickableString: String,
        textView: TextView
    ) {
        val context = textView.context
        val value = textView.text.toString()
        val spannableString = SpannableString(value)
        try {
            spannableString.setSpan(
                object : ClickableSpan() {
                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.isUnderlineText = withUnderline
                    }

                    override fun onClick(widget: View) {
                        onClickListener.onClick(textView)
                    }
                }, value.indexOf(clickableString), value.indexOf(clickableString) + clickableString.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            if (color != 0) {
                spannableString.setSpan(
                    ForegroundColorSpan(context.resources.getColor(color)),
                    value.indexOf(clickableString),
                    value.indexOf(clickableString) + clickableString.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

        } catch (e: Exception) {
            // do nothing
        }

        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }

    fun setText(view: View, text: String? = ""){
        if(view is TextView){
            view.setText(text);
        }
    }

    fun setVisibility(views: View){

    }

}
