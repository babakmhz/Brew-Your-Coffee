package com.babakmhz.coffieitassessment.data

import com.babakmhz.coffieitassessment.data.network.ApiHelper
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper
) : RepositoryHelper {


}