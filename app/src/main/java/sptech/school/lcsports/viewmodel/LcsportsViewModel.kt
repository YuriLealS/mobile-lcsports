package sptech.school.lcsports.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import sptech.school.lcsports.DtoAuth
import sptech.school.lcsports.services.model.CadastroModel
import sptech.school.lcsports.services.model.LoginModel
import sptech.school.lcsports.services.repository.RemoteDataSource

class LcsportsViewModel(application : Application) : AndroidViewModel(application) {

    private val mRemoteDataSource = RemoteDataSource()

    private val mNasaResponse = MutableLiveData<DtoAuth>()
    val nasaResponse: LiveData<DtoAuth> = mNasaResponse

    private val mCadastroResponse = MutableLiveData<DtoAuth>()
    val cadastroResponse: LiveData<DtoAuth> = mCadastroResponse

    private val mErrorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = mErrorMessage

    fun login(body : LoginModel) {
        mRemoteDataSource.login(body, { retorno ->
            mNasaResponse.value = retorno
        }, { stringMessage ->
            mErrorMessage.postValue(stringMessage)
        })
    }

    fun cadastrar(body : CadastroModel) {
        mRemoteDataSource.cadastro(body, { retorno ->
            mCadastroResponse.value = retorno
        }, { stringMessage ->
            mErrorMessage.postValue(stringMessage)
        })
    }
}