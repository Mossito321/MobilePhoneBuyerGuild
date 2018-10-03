package com.phonebuyer.mossinwkung.mobilephonerguild.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableRow
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.phonebuyer.mossinwkung.mobilephonerguild.R
import com.phonebuyer.mossinwkung.mobilephonerguild.response.MobileDetailImageListResponse
import com.phonebuyer.mossinwkung.mobilephonerguild.response.MobileListResponse
import kotlinx.android.synthetic.main.fragment_detail.*
import java.io.Serializable

class MobileDetailFragment : Fragment() {

    private var itemDetail: MobileListResponse? = null
    private var imageList: ArrayList<MobileDetailImageListResponse>? = null
    private lateinit var pagerAdapter: MobileDetailAdapter


    companion object {
        private const val KEY_MOBILE_DETAIL = "key_mobile"
        private const val KEY_IMAGE_LIST = "key_image_list"

        fun newInstance(itemDetail: MobileListResponse,
                        itemImage: ArrayList<MobileDetailImageListResponse>): MobileDetailFragment {
            val gson = GsonBuilder().create()
            val itemDetails = gson.toJson(itemDetail)
            val bundle = Bundle().apply {
                putString(KEY_MOBILE_DETAIL, itemDetails)
                putSerializable(KEY_IMAGE_LIST, itemImage as Serializable)
            }

            return MobileDetailFragment().apply {
                arguments = bundle
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = savedInstanceState ?: arguments
        bundle?.let {
            val gson = Gson()
            if (it.containsKey(KEY_MOBILE_DETAIL)) {
                itemDetail = gson.fromJson(it.getString(KEY_MOBILE_DETAIL), MobileListResponse::class.java)
            }

            if (it.containsKey(KEY_IMAGE_LIST)) {
                imageList = it.getSerializable(KEY_IMAGE_LIST) as ArrayList<MobileDetailImageListResponse>
            }
        }
        context?.let {
            imageList?.let { imageList ->
                pagerAdapter = MobileDetailAdapter(it, items = imageList)
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var toolbar = activity?.findViewById(R.id.tableRow) as TableRow
        var backImage = toolbar.findViewById(R.id.action_bar_back) as ImageView
        var filterImage = toolbar.findViewById(R.id.action_bar_forward) as ImageView

        backImage.visibility = View.VISIBLE
        filterImage.visibility = View.GONE

        pagerImageView.adapter = pagerAdapter

        mobileName.text = itemDetail?.itemName
        mobileDetail.text = itemDetail?.description
        mobileRating.text = "Rating:${itemDetail?.itemRating.toString()}"
        mobilePrice.text = "Price:$${itemDetail?.itemPrice}"
    }

    override fun onResume() {
        super.onResume()
        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = (resources.displayMetrics.heightPixels * 0.35).toInt()
        var lp = pagerImageView.layoutParams as ViewGroup.LayoutParams
        lp.height = height
        lp.width = width
    }

}