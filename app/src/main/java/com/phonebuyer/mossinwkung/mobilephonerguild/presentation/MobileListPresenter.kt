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
                    view?.renderList(currentTab, it)
                }, {

                })
                .addTo(disposeBag)
    }

    override fun setDefaultTab(tab: MobileListTab) {
        currentTab = tab
    }

    override fun onSelectItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSelectTabMoblieList() {
        currentTab = MobileListTab.LIST
        render()
    }

    override fun onSelectTabFavoriteList(mobileId: String) {
        currentTab = MobileListTab.FAV
        render()
    }

    override fun cleanup() {
        disposeBag.clear()
    }

    private fun render() {

    }

}