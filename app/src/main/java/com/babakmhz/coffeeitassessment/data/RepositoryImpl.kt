package com.babakmhz.coffeeitassessment.data

import com.babakmhz.coffeeitassessment.data.network.ApiHelper
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper
) : RepositoryHelper {


}