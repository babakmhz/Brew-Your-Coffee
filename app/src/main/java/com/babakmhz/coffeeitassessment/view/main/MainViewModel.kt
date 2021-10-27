package com.babakmhz.coffeeitassessment.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babakmhz.coffeeitassessment.data.RepositoryHelper
import com.babakmhz.coffeeitassessment.data.model.device.Device
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

    fun getTypes() {
        viewModelScope.launchWithException(_typesLiveData) {
            _typesLiveData.postValue(State.Loading)
            val result = repositoryHelper.getDeviceCoffees()
            _typesLiveData.postValue(State.Success(result.types))
        }
    }


}