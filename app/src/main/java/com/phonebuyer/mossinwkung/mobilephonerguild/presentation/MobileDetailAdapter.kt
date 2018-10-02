package com.phonebuyer.mossinwkung.mobilephonerguild.presentation

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.phonebuyer.mossinwkung.mobilephonerguild.R
import com.phonebuyer.mossinwkung.mobilephonerguild.response.MobileDetailImageListResponse

class MobileDetailAdapter(private val context: Context,
                          private val items: ArrayList<MobileDetailImageListResponse>) : PagerAdapter() {

    override fun isViewFromObject(view: View, oj: Any): Boolean {
        return view == oj
    }

    override fun destroyItem(container: ViewGroup, position: Int, oj: Any) {
        container.removeView(oj as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var imageLayout = LayoutInflater.from(context).inflate(R.layout.item_image_detail, container, false)
        var imageDetail = imageLayout.findViewById(R.id.imageDetail) as ImageView
        Glide.with(context).load(items[position].itemImageUrl).into(imageDetail)
        container.addView(imageLayout, 0)
        return imageLayout
    }

    override fun getCount(): Int {
        return items.size
    }


}