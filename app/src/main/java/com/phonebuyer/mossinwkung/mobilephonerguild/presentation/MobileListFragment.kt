package com.phonebuyer.mossinwkung.mobilephonerguild.presentation

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableRow
import android.widget.Toast
import com.phonebuyer.mossinwkung.mobilephonerguild.MainActivity
import com.phonebuyer.mossinwkung.mobilephonerguild.R
import com.phonebuyer.mossinwkung.mobilephonerguild.api.MobileApi
import com.phonebuyer.mossinwkung.mobilephonerguild.response.MobileDetailImageListResponse
import com.phonebuyer.mossinwkung.mobilephonerguild.response.MobileListResponse
import kotlinx.android.synthetic.main.fragment_mobile_list.*

class MobileListFragment : Fragment(), MobileListContract.View, MobileListAdapter.ITileListener {

    private var items = mutableListOf<MobileListResponse>()
    private var selectTab: MobileListTab = MobileListTab.LIST
    private var adapter: MobileListAdapter? = null
    private var presenter: MobileListContract.Presenter? = null
    private var flagListItem: ArrayList<MobileListResponse>? = null
    private var itemSelect: MobileListResponse? = null

    companion object {
        fun newInstance(): MobileListFragment {
            return MobileListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            adapter = MobileListAdapter(it, items)
            adapter?.mListener = this
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mobile_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var toolbar = activity?.findViewById(R.id.tableRow) as TableRow
        var backImage = toolbar.findViewById(R.id.action_bar_back) as ImageView
        var filterImage = toolbar.findViewById(R.id.action_bar_forward) as ImageView

        backImage.visibility = View.GONE
        filterImage.visibility = View.VISIBLE

        val mobileListUseCase = MobileListUseCaseImpl(MobileApi)

        presenter = MobileListPresenter(this, mobileListUseCase)
        presenter?.setDefaultTab(selectTab)
        presenter?.getMobileList()

        val layoutManager = LinearLayoutManager(context)
        var dividerItemDecoration = DividerItemDecoration(mobileListRecyclerView.context,
                layoutManager.orientation)
        mobileListRecyclerView?.layoutManager = layoutManager
        mobileListRecyclerView?.adapter = adapter
        mobileListRecyclerView?.setHasFixedSize(true)
        mobileListRecyclerView?.addItemDecoration(dividerItemDecoration)

        mobileListButton.setOnClickListener {
            presenter?.onSelectTabMobileList()
            favoriteButton.setTextColor(Color.GRAY)
            mobileListButton.setTextColor(Color.WHITE)
        }

        favoriteButton.setOnClickListener {
            presenter?.onSelectTabFavoriteList()
            mobileListButton.setTextColor(Color.GRAY)
            favoriteButton.setTextColor(Color.WHITE)
        }
    }

    override fun renderList(tab: MobileListTab, item: ArrayList<MobileListResponse>) {
        flagListItem = item
        mobileListRecyclerView.visibility = View.VISIBLE
        errorTextView.visibility = View.GONE
        this.items.clear()
        this.items.addAll(item)
        mobileListRecyclerView?.adapter = adapter
        adapter?.setSelectedTab(tab)
        adapter?.notifyDataSetChanged()
    }

    fun filterList(option: Int) {
        when (option) {
            0 -> this.items.sortWith(compareBy { it.itemPrice })
            1 -> this.items.sortByDescending { it.itemPrice }
            2 -> this.items.sortByDescending { it.itemRating }
        }
        adapter?.notifyDataSetChanged()
    }

    override fun showLoading() {
        loader.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loader.visibility = View.INVISIBLE
    }

    override fun showErrorMessage(message: String){
        Toast.makeText(context, "Error Message: ${message}",Toast.LENGTH_LONG).show()
    }


    override fun showNotItem() {
        errorTextView.visibility = View.VISIBLE
        mobileListRecyclerView.visibility = View.GONE
    }

    override fun onTileItemClick(item: MobileListResponse) {
        presenter?.getMobileImageList((item.itemId).toString())
        itemSelect = item
    }

    override fun openSeeDetail(imageList: ArrayList<MobileDetailImageListResponse>) {
        var mobileDetailFragment = itemSelect?.let { MobileDetailFragment.Companion.newInstance(it, imageList) }
        mobileDetailFragment?.let { (activity as MainActivity).setupFragment(it) }
    }
}