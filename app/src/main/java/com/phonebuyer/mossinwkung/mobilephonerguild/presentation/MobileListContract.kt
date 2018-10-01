package com.phonebuyer.mossinwkung.mobilephonerguild.presentation

import com.phonebuyer.mossinwkung.mobilephonerguild.response.MobileListResponse

interface MobileListContract {
    interface View {
        fun renderList(tab: MobileListTab,item: ArrayList<MobileListResponse>)
        fun showLoading()
        fun hideLoading()
        fun showNotItem()
        fun openSeeDetail()
    }

    interface Presenter {
        fun getMobileList()
        fun setDefaultTab(tab: MobileListTab)
        fun getMobileImageList(mobileId: String)
        fun onSelectItem()
        fun onSelectTabMobileList()
        fun onSelectTabFavoriteList()
    }
}