package com.wasi.twitching.Views.Adapters

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.JsonArray
import com.squareup.picasso.Picasso
import com.wasi.twitching.Data.TwitchGameModel
import com.wasi.twitching.Views.Activities.DetailActivity
import com.wasi.twitching.Views.Activities.MainActivity

import com.wasi.twitching.R


class MyAdapter// Provide a suitable constructor (depends on the kind of dataset)
(public var mDataset: MutableList<TwitchGameModel>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder : RecyclerView.ViewHolder, View.OnClickListener {

        override fun onClick(p0: View) {
            var intent : Intent = Intent(p0.context, DetailActivity::class.java)
            model?.putToIntent(intent)
            val sharedView1 = Pair(main_imageview_game as View, "main_imageview_game")
            val sharedView2 = Pair(main_tv_title as View, "main_tv_title")
            val sharedView3 = Pair(main_tv_views as View, "main_tv_views")

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(p0.context as MainActivity, sharedView1, sharedView2, sharedView3);
            p0.context.startActivity(intent, options.toBundle())
        }

        var smallImageUrl: String = ""
        var largeImageUrl: String = ""
        var gbid: String = ""
        var model : TwitchGameModel?

        val main_tv_title : TextView by lazy {
            view?.findViewById(R.id.main_tv_title) as TextView
        }
        val main_imageview_game : ImageView by lazy {
            view?.findViewById(R.id.main_imageview_game) as ImageView
        }

        val main_tv_views : TextView by lazy {
            view?.findViewById(R.id.main_tv_views) as TextView
        }

        val view : View?
        constructor(view : View, model : TwitchGameModel?) : super(view){
            this.view = view
            this.model = model
            this.view.setOnClickListener (this)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.ViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context).inflate(R.layout.my_text_layout, parent, false)
        // set the view's size, margins, paddings and layout parameters
        val vh = MyAdapter.ViewHolder(v, null)
        return vh
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.model = mDataset[position]
        holder.main_tv_title.text = mDataset[position].name
        holder.main_tv_views.text = mDataset[position].viewers.toString()
        holder.smallImageUrl = mDataset[position].artSmallUrl
        holder.largeImageUrl = mDataset[position].artLargeUrl
        holder.gbid = mDataset[position].gbid.toString()
        Picasso.with(holder.main_imageview_game.context)
                .load(holder.smallImageUrl)
                .into(holder.main_imageview_game)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mDataset.count()
    }
}