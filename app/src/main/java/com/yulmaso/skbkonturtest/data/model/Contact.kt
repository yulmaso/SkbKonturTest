package com.yulmaso.skbkonturtest.data.model

import java.io.Serializable

class Contact(
    val id: String,
    val name: String,
    val phone: String,
    val height: Float,
    val biography: String,
    val temperament: Temperament,
    val educationPeriod: EducationPeriod
) : Serializable