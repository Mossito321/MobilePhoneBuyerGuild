package com.phonebuyer.mossinwkung.mobilephonerguild.presentation

import com.phonebuyer.mossinwkung.mobilephonerguild.response.MobileListResponse

interface MobileListContract {
    interface View {
        fun renderList(tab: MobileListTab,item: ArrayList<MobileListResponse>)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun getMobileList()
        fun setDefaultTab(tab: MobileListTab)
        fun onSelectItem()
        fun onSelectTabMoblieList()
        fun onSelectTabFavoriteList(mobileId: String)
        fun cleanup()
    }
}