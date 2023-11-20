package com.example.booking.domain.model

data class TouristInfo(
    val id: Int,
    val first_name: String?=null,
    val last_name: String?=null,
    val birth_date: String?=null,
    val citizenship: String?=null,
    val document_number: String?=null,
    val validate: String? =null,
    var checkFeilds: Boolean? = null,
    val collapsed: Boolean = false
)