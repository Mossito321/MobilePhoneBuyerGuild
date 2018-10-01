package com.phonebuyer.mossinwkung.mobilephonerguild.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phonebuyer.mossinwkung.mobilephonerguild.R
import io.reactivex.disposables.CompositeDisposable

class MobileListFragment : Fragment(), MobileListContract.View {
    private var items = mutableListOf<Any>()
    private var disposeBag = CompositeDisposable()
    private var selectTab: MobileListTab = MobileListTab.LIST

    companion object {
        fun newInstance(): MobileListFragment{
            return MobileListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mobile_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun renderList(tab: MobileListTab, item: List<Any>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}