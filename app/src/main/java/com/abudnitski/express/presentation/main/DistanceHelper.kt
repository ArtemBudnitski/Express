package com.abudnitski.express.presentation.main

import com.abudnitski.express.domain.main.Station
import javax.inject.Inject
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class DistanceHelper @Inject constructor() {

    fun calculateDistance(start: Station, end: Station): Double {
        val lat1 = start.latitude.toDouble()
        val lon1 = start.longitude.toDouble()
        val lat2 = end.latitude.toDouble()
        val lon2 = end.longitude.toDouble()
        val earthRadius = 6371

        val latDistance = Math.toRadians(lat2 - lat1)
        val lonDistance = Math.toRadians(lon2 - lon1)
        val a = sin(latDistance / 2) * sin(latDistance / 2) +
                (cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                        sin(lonDistance / 2) * sin(lonDistance / 2))
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return earthRadius * c
    }
}