package com.example.spacexapi.model

data class CompanyResponse(
        val ceo: String,
        val coo: String,
        val cto: String,
        val cto_propulsion: String,
        val employees: Int,
        val founded: Int,
        val founder: String,
        val headquarters: Headquarters,
        val launch_sites: Int,
        val name: String,
        val summary: String,
        val test_sites: Int,
        val valuation: Long,
        val vehicles: Int
)