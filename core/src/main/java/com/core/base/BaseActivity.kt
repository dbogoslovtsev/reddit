package com.core.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.core.di.viewmodel.ViewModelFactory
import javax.inject.Inject

open class BaseActivity<VIEW_MODEL : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var viewModelClass: Class<VIEW_MODEL>

    protected val viewModel: VIEW_MODEL by lazy {
        ViewModelProvider(this, viewModelFactory)[viewModelClass]
    }


    override fun onStart() {
        super.onStart()

        with(viewModel) {
            errorLiveData.observe(this@BaseActivity) {
                showToast(it)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}