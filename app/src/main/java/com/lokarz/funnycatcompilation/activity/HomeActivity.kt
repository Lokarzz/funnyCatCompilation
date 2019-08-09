package com.lokarz.funnycatcompilation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lokarz.funnycatcompilation.R
import com.lokarz.funnycatcompilation.Utils.ConstantUtil
import com.lokarz.funnycatcompilation.Utils.Listener
import com.lokarz.funnycatcompilation.Utils.ViewUtil
import com.lokarz.funnycatcompilation.adapters.RvCatThumbnailAdapter
import com.lokarz.funnycatcompilation.models.CatVideoData
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class HomeActivity : AppCompatActivity(){


    private var databaseReference = FirebaseDatabase.getInstance().getReference("videos");
    private var catData = ArrayList<CatVideoData>();
    private var rvCatVideoAdapter = RvCatThumbnailAdapter(catData);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        initView()
        getCatVideos()
    }

    fun initView() {
        var rvCatVideos = findViewById(R.id.rv_cat_videos) as RecyclerView;
        rvCatVideos.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        rvCatVideoAdapter.mListener = onItemSelected()
        rvCatVideos.adapter = rvCatVideoAdapter;

    }


    fun onItemSelected(): Listener.OnItemClick<CatVideoData>? {
        return object : Listener.OnItemClick<CatVideoData>{
            override fun onItemSelected(t: CatVideoData) {
                var args = Bundle();
                args.putString(ConstantUtil.URL, t.ytId)
                ViewUtil.popUpScreen(this@HomeActivity, VideoActivity::class.java, args)
            }

        }
    }

    @SuppressLint("CheckResult")
    fun getCatVideos() {
        var relay = PublishRelay.create<Int>();
        relay.throttleLast(2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                rvCatVideoAdapter.notifyDataSetChanged();
            });
//        databaseReference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(data: DataSnapshot) {
//                catData.clear();
//                for (datasnapshot in data.children) {
//                    var catVideoData = datasnapshot.getValue(CatVideoData::class.java);
//                    catVideoData?.let { catData.add(it) }
//                }
//                relay.accept(1);
//
//                databaseReference.removeEventListener(this);
//            }
//
//            override fun onCancelled(p0: DatabaseError) {
//                Log.w("Asdf", p0.message);
//            }
//
//        });

        databaseReference.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(datasnapshot: DataSnapshot, p1: String?) {
                var catVideoData = datasnapshot.getValue(CatVideoData::class.java);
                catVideoData?.let { catData.add(0, it) }
                relay.accept(1);
            }

            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }

        })
    }

}
