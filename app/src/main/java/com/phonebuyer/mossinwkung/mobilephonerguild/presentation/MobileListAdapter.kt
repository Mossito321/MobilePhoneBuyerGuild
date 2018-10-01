package com.phonebuyer.mossinwkung.mobilephonerguild.presentation

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.phonebuyer.mossinwkung.mobilephonerguild.R
import com.phonebuyer.mossinwkung.mobilephonerguild.response.MobileListResponse

class MobileListAdapter(private val context: Context, private val items: List<MobileListResponse>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var currentTab: MobileListTab = MobileListTab.LIST


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (currentTab == MobileListTab.LIST) {
            var itemLayoutView = LayoutInflater.from(context).inflate(
                    R.layout.item_mobile, null)
            var viewHolder = MobileListSectionViewHolder(itemLayoutView)
            viewHolder
        } else {
            var itemLayoutView = LayoutInflater.from(context).inflate(
                    R.layout.item_mobile_fav, null)
            var viewHolder = MobileListFavoriteSectionViewHolder(itemLayoutView)
            viewHolder
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MobileListSectionViewHolder) {
            val item = items[position] as MobileListResponse

            holder.itemMobileName?.text = item?.itemName ?: ""
            holder.itemMobileDes?.text = item?.description ?: ""
            holder.itemMobilePrice?.text = "Price:$${item?.itemPrice}"
            holder.itemMobileRating?.text = "Rating:${item?.itemRating}"
            Glide.with(context).load(item.itemImageUrl)
                    .into(holder.itemIconImageView)

            holder.itemView.setOnClickListener {
                //deduct my point row and tab row
                Toast.makeText(context, "Press item law kab $position", Toast.LENGTH_LONG).show()
            }
        } else {

        }
    }

    fun setSelectedTab(tab: MobileListTab) {
        currentTab = tab
    }

    class MobileListSectionViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var itemIconImageView: ImageView? = null
        var itemMobileName: TextView? = null
        var itemMobileDes: TextView? = null
        var itemMobilePrice: TextView? = null
        var itemMobileRating: TextView? = null

        init {
            itemIconImageView = view.findViewById(R.id.imageMobile) as? ImageView
            itemMobileName = view.findViewById(R.id.mobileName) as? TextView
            itemMobileDes = view.findViewById(R.id.mobileDetail) as? TextView
            itemMobilePrice = view.findViewById(R.id.mobilePrice) as? TextView
            itemMobileRating = view.findViewById(R.id.moblieRating) as? TextView
        }
    }

    class MobileListFavoriteSectionViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var itemIconImageView: ImageView? = null
        var itemMobileName: TextView? = null
        var itemMobilePrice: TextView? = null
        var itemMobileRating: TextView? = null

        init {
            itemIconImageView = view.findViewById(R.id.imageMobile) as? ImageView
            itemMobileName = view.findViewById(R.id.mobileName) as? TextView
            itemMobilePrice = view.findViewById(R.id.mobilePrice) as? TextView
            itemMobileRating = view.findViewById(R.id.mobileRating) as? TextView
        }
    }


}