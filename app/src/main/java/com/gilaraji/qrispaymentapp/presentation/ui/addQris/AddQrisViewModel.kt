package com.gilaraji.qrispaymentapp.presentation.ui.addQris

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilaraji.qrispaymentapp.domain.models.QrisModels
import com.gilaraji.qrispaymentapp.domain.models.Resource
import com.gilaraji.qrispaymentapp.domain.usecase.UsecaseQrisPayment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddQrisViewModel @Inject constructor(
    private val usecaseQrisPayment: UsecaseQrisPayment
) : ViewModel() {


    fun saveData(data: QrisModels): MutableLiveData<Resource<List<QrisModels>>> {
        val dataResult = MutableLiveData<Resource<List<QrisModels>>>()
        viewModelScope.launch {
            try {
                usecaseQrisPayment.addQrisPayment(data)
                val result =usecaseQrisPayment.getQrisPayment()
                dataResult.value = Resource.success(result)
            } catch (e: Exception) {
                val errorMessage = e.localizedMessage ?: "Unknown error occurred"
                dataResult.value = Resource.error(errorMessage)
            }
        }
        return dataResult
    }
}