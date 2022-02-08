package com.core.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.core.di.viewmodel.ViewModelFactory
import com.core.navigation.ScreenNavigator
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var viewModelClass: Class<VM>

    protected val viewModel: VM by lazy {
        ViewModelProvider(this, viewModelFactory)[viewModelClass]
    }

//    @Inject
    protected lateinit var screenNavigator: ScreenNavigator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()

        with(viewModel) {
            errorLiveData.observe(this@BaseActivity) {
                it.message?.run { showToast(this) }
            }
        }
    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    protected abstract fun injectDependencies()

}