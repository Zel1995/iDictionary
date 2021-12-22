package com.example.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.IdRes
import java.lang.IllegalStateException
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

class ViewByIdDelegate<out T : View>(private val rootGetter: () -> View?, private val viewId: Int) {
    private var rootRef: WeakReference<View>? = null
    private var viewRef: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        var view = viewRef
        val cacheRoot = rootRef?.get()
        val currentRoot = rootGetter()
        if(currentRoot != cacheRoot || view == null){
            if (currentRoot == null){
                throw IllegalStateException("cannot get view")
            }
            view = currentRoot.findViewById(viewId)
            viewRef = view
            rootRef = WeakReference(currentRoot)
        }
        checkNotNull(view){"view ${viewId}not found"}
        return view
    }
}

fun <T:View> Activity.viewById(@IdRes viewId:Int):ViewByIdDelegate<T>{
    return ViewByIdDelegate({window.decorView.findViewById(android.R.id.content)},viewId)
}

fun<T:View> Fragment.viewById(@IdRes viewId:Int):ViewByIdDelegate<T>{
    return ViewByIdDelegate({view},viewId)
}