package com.phonebuyer.mossinwkung.mobilephonerguild.presentation

interface MobileListContract {
    interface View {
        fun renderList(tab: MobileListTab, item: List<Any>)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun setDefaultTab()
        fun onSelectItem()
        fun onSelectTabMoblieList()
        fun onSelectTabFavoriteList(mobileId: String)
        fun cleanup()
    }
}