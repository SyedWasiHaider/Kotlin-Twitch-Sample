package com.wasi.twitching.Views.Listeners

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import com.wasi.twitching.Views.Adapters.MyAdapter

class RecyclerScrollViewListener(private var mLinearLayoutManager: StaggeredGridLayoutManager,private val loadMore : (Int, RecyclerScrollViewListener) -> Unit, private val thresHold : Int = 10) : RecyclerView.OnScrollListener() {

    var loading = false
    private var current_page = 1

    constructor( mLinearLayoutManager: StaggeredGridLayoutManager, loadMore : (Int, RecyclerScrollViewListener) -> Unit, setThreshold : Boolean, threshold : Int) : this(mLinearLayoutManager, loadMore, threshold){

    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        var lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPositions(null).last()
        var count = (recyclerView.adapter as MyAdapter).mDataset.count()
        count -= thresHold
        var lol = !loading && lastVisibleItem >= count
        if (lol) {
            loading = true
            current_page++
            loadMore(current_page, this);
        }
    }
}
