package com.lokarz.funnycatcompilation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.RecyclerView
import com.lokarz.funnycatcompilation.R
import com.lokarz.funnycatcompilation.models.CatVideoData

class RvCatVideoAdapter(catVideoDatas: List<CatVideoData>) : RecyclerView.Adapter<RvCatVideoAdapter.ViewHolder>() {

    private var mCatVideoData: List<CatVideoData> = emptyList();

    init {
        this.mCatVideoData = catVideoDatas;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_cat_video, parent, false);

        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return mCatVideoData.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var catVideoData: CatVideoData = mCatVideoData.get(position);
        var view: View = holder.itemView;

        var vvHowItWorks: WebView = view.findViewById(R.id.wv_video);
        vvHowItWorks.setWebViewClient(object : WebViewClient() {});

        vvHowItWorks.setWebChromeClient(object : WebChromeClient() {});

        vvHowItWorks.getSettings().setDomStorageEnabled(true);
        vvHowItWorks.getSettings().setDatabaseEnabled(true);
        vvHowItWorks.getSettings().setJavaScriptEnabled(true);

        var url = String.format("https://www.youtube.com/embed/%s?loop=1&controls=1&playlist=%s", catVideoData.ytId, catVideoData.ytId);

        vvHowItWorks.loadUrl(url);
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }


}