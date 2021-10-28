package com.babakmhz.coffeeitassessment.data
import com.babakmhz.coffeeitassessment.data.db.DbHelper
import com.babakmhz.coffeeitassessment.data.model.device.Type
import com.babakmhz.coffeeitassessment.data.network.ApiHelper


interface RepositoryHelper : ApiHelper,DbHelper.SharedMethods{

}