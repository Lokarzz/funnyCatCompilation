package com.lokarz.funnycatcompilation.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lokarz.funnycatcompilation.R

/**
 * A simple [Fragment] subclass.
 */
class CatsVideoFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (v == null){
            v = inflater.inflate(R.layout.fragment_cats_video, container, false);
        }
        return v;
    }

}
