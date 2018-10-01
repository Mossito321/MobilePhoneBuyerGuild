package com.phonebuyer.mossinwkung.mobilephonerguild.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phonebuyer.mossinwkung.mobilephonerguild.R
import com.phonebuyer.mossinwkung.mobilephonerguild.api.MobileApi
import com.phonebuyer.mossinwkung.mobilephonerguild.response.MobileListResponse
import kotlinx.android.synthetic.main.fragment_mobile_list.*

class MobileListFragment : Fragment(), MobileListContract.View {
    private var items = mutableListOf<MobileListResponse>()
    private var selectTab: MobileListTab = MobileListTab.LIST
    private var adapter: MobileListAdapter? = null
    private var presenter: MobileListContract.Presenter? = null

    companion object {
        fun newInstance(): MobileListFragment {
            return MobileListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            adapter = MobileListAdapter(it, items)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mobile_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
    }

    override fun renderList(tab: MobileListTab, item: ArrayList<MobileListResponse>) {
        this.items.clear()
        this.items.addAll(item)
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
}