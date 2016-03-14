package com.wasi.twitching.Views.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import com.wasi.twitching.API.TwitchAPIService
import com.wasi.twitching.Constants
import com.wasi.twitching.R
import com.wasi.twitching.Views.Adapters.MyAdapter
import com.wasi.twitching.Views.Listeners.RecyclerScrollViewListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        my_recycler_view.setHasFixedSize(true);

        // use a linear layout manager
        val mLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        my_recycler_view.setLayoutManager(mLayoutManager);

        //Load more things as the user scrolls down
        my_recycler_view.addOnScrollListener(RecyclerScrollViewListener(mLayoutManager,
                {
                    index, listener ->
                    TwitchAPIService.GetTopGameStreams({
                        data ->
                        val adapter = (my_recycler_view.getAdapter() as MyAdapter)
                        adapter.mDataset.addAll(data)
                        adapter.notifyDataSetChanged()
                        //this is an ugly hack.
                        listener.loading = false
                    }
                            , Offset = Constants.ITEMS_PER_PAGE * (index + 1), Limit = Constants.ITEMS_PER_PAGE)
                }, Constants.ITEMS_PER_PAGE / 4
        ))
        //Get data from service and specify an adapter
        TwitchAPIService.GetTopGameStreams(
                {wtf->
                    val mAdapter = MyAdapter(wtf);
                    my_recycler_view.setAdapter(mAdapter);
                }, Limit = Constants.ITEMS_PER_PAGE
        )

    }
}
