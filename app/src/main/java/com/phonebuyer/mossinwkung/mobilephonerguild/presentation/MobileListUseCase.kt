package com.phonebuyer.mossinwkung.mobilephonerguild.presentation

import com.phonebuyer.mossinwkung.mobilephonerguild.api.MobileApi
import com.phonebuyer.mossinwkung.mobilephonerguild.response.MobileDetailImageListResponse
import com.phonebuyer.mossinwkung.mobilephonerguild.response.MobileListResponse
import io.reactivex.Observable

interface MobileListUseCase {
    fun getMobileItem():
            Observable<ArrayList<MobileListResponse>>

    fun getMobileImageList(mobileId: String):
            Observable<ArrayList<MobileDetailImageListResponse>>
}

class MobileListUseCaseImpl(
        private val api: MobileApi
) : MobileListUseCase {

    override fun getMobileItem(): Observable<ArrayList<MobileListResponse>> {
        return api.getMobileItem()
    }

    override fun getMobileImageList(mobileId: String): Observable<ArrayList<MobileDetailImageListResponse>> {
        return api.getMobileItemImage(mobileId)
    }

}