package com.phonebuyer.mossinwkung.mobilephonerguild.response

import com.google.gson.annotations.SerializedName

open class MobileListResponse {

    @SerializedName("brand")
    val band: String? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("id")
    val itemId: Int? = null

    @SerializedName("name")
    val itemName: String? = null

    @SerializedName("price")
    val itemPrice: String? = null

    @SerializedName("thumbImageURL")
    val itemImageUrl: String? = null

    @SerializedName("rating")
    val itemRation: Double? = null
}