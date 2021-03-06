package com.babakmhz.coffeeitassessment.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babakmhz.coffeeitassessment.data.RepositoryHelper
import com.babakmhz.coffeeitassessment.data.model.device.Extra
import com.babakmhz.coffeeitassessment.data.model.device.Size
import com.babakmhz.coffeeitassessment.data.model.device.Type
import com.babakmhz.coffeeitassessment.utils.State
import com.babakmhz.coffeeitassessment.utils.launchWithException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryHelper: RepositoryHelper,
) :
    ViewModel() {


    private var _typesLiveData = MutableLiveData<State<List<Type>>>()
    val typesLivedata: LiveData<State<List<Type>>> = _typesLiveData

    private var _overviewSizeLiveData = MutableLiveData<Int>()
    val overviewSizeLiveData: LiveData<Int> = _overviewSizeLiveData

    private var _overviewTypesLiveData = MutableLiveData<Type>()
    val overviewTypesLiveData: LiveData<Type> = _overviewTypesLiveData

    var orderedType: Type? = null

    fun getTypes() {
        viewModelScope.launchWithException(_typesLiveData) {
            _typesLiveData.postValue(State.Loading)
            val result = repositoryHelper.getDeviceCoffees()
            _typesLiveData.postValue(State.Success(result.types))
        }
    }

    fun getSizesForType(type: Type): List<Size> {
        return repositoryHelper.getSizesForType(type)
    }

    fun getExtrasForType(type: Type): List<Extra> {
        return repositoryHelper.getExtrasForType(type)
    }

    fun addToWishList(type:Type){
        orderedType = type
        _overviewSizeLiveData.postValue(1)
        _overviewTypesLiveData.postValue(orderedType)
    }

    fun brewYourCoffee(){
        orderedType = null
        _overviewTypesLiveData.postValue(orderedType)
        _overviewSizeLiveData.postValue(0)
    }
}