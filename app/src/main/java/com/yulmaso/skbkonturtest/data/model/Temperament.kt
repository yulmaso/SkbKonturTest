package com.yulmaso.skbkonturtest.data.model

import com.google.gson.annotations.SerializedName

enum class Temperament{
    @SerializedName("melancholic")
    Melancholic,
    @SerializedName("phlegmatic")
    Phlegmatic,
    @SerializedName("sanguine")
    Sanguine,
    @SerializedName("choleric")
    Choleric;
}