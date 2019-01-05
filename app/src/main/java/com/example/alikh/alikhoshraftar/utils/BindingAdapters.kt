package com.example.alikh.alikhoshraftar.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.databinding.BindingAdapter
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.alikh.alikhoshraftar.utils.extension.getParentActivity
import com.squareup.picasso.Picasso

@BindingAdapter("adapter")
fun setAdapter(view: androidx.recyclerview.widget.RecyclerView, adapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>) {
    view.setHasFixedSize(true)
    view.adapter = adapter
}

//@BindingAdapter("adapter", "listener")
//fun setAdapterWithListener(view: RecyclerView, adapter: RecyclerView.Adapter<*>, listener: ClickListener) {
//    setAdapter(view, adapter)
//    if (view.adapter is MovieListAdapter) {
//        ((view.adapter) as MovieListAdapter).setClickListener(listener)
//    }
//}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

@BindingAdapter("imageUrl", "error")
fun loadImage(view: ImageView, url: String, error: Drawable) {
    Picasso.get().load(url).error(error).into(view)
}

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    if (imageUrl != null) {
        Picasso.get().load(imageUrl).into(imageView)
    }
}

@BindingAdapter("font")
fun setTypeface(view: TextView, font: String) {
    view.setTypeface(Typeface.createFromAsset(view.context.assets, "fonts/" + font + ".ttf"))
}