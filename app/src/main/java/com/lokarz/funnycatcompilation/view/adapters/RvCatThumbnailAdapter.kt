package com.lokarz.funnycatcompilation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lokarz.funnycatcompilation.R
import com.lokarz.funnycatcompilation.Utils.Listener
import com.lokarz.funnycatcompilation.Utils.ViewUtil
import com.lokarz.funnycatcompilation.models.CatVideoData

class RvCatThumbnailAdapter(catVideoDatas: List<CatVideoData>) :
    RecyclerView.Adapter<RvCatThumbnailAdapter.ViewHolder>() {

    private var mCatVideoData: List<CatVideoData> = emptyList()
    var mListener: Listener.OnItemClick<CatVideoData>? = null;

    init {
        this.mCatVideoData = catVideoDatas;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_cat_thumbnail, parent, false);

        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return mCatVideoData.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var catVideoData: CatVideoData = mCatVideoData.get(position);
        var view: View = holder.itemView;

        var ivCatThumbnail = view.findViewById(R.id.iv_cat_thumbnail) as ImageView;

        var url = String.format("https://img.youtube.com/vi/%s/mqdefault.jpg", catVideoData.ytId);
        ViewUtil.setText(view.findViewById(R.id.tv_title), catVideoData.ytTitle)


        Glide.with(view.context)
            .load(url)
            .into(ivCatThumbnail);

        view.setOnClickListener {
            mListener?.onItemSelected(catVideoData)
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }


}