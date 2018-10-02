package com.phonebuyer.mossinwkung.mobilephonerguild.presentation

import com.phonebuyer.mossinwkung.mobilephonerguild.extensions.addTo
import com.phonebuyer.mossinwkung.mobilephonerguild.response.MobileListResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MobileListPresenter(
        private val view: MobileListContract.View?,
        private val mobileListUseCase: MobileListUseCase
) : MobileListContract.Presenter {

    private val disposeBag = CompositeDisposable()
    private var currentTab: MobileListTab = MobileListTab.LIST
    private var listMobileItem: ArrayList<MobileListResponse>? = null


    override fun getMobileList() {
        mobileListUseCase.getMobileItem()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { view?.showLoading() }
                .doFinally { view?.hideLoading() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listMobileItem = it
                    view?.renderList(currentTab, it)
                }, {

                })
                .addTo(disposeBag)
    }

    override fun setDefaultTab(tab: MobileListTab) {
        currentTab = tab
    }

    override fun onSelectTabMobileList() {
        currentTab = MobileListTab.LIST
        render()
    }

    override fun onSelectTabFavoriteList() {
        currentTab = MobileListTab.FAV
        render()
    }

    override fun getMobileImageList(mobileId: String) {
        mobileListUseCase.getMobileImageList(mobileId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.openSeeDetail(it)
                }, {

                })
                .addTo(disposeBag)

    }

    private fun render() {
        var list = ArrayList<MobileListResponse>()
        if (currentTab == MobileListTab.LIST) {
            listMobileItem?.let { view?.renderList(currentTab, it) }
        } else if (currentTab == MobileListTab.FAV) {
            listMobileItem?.forEach {
                if (it.favorite == true)
                    list.add(it)
            }
            if (list?.size == 0) {
                view?.showNotItem()
            } else {
                list?.let { view?.renderList(currentTab, it) }
            }
        }
    }

}