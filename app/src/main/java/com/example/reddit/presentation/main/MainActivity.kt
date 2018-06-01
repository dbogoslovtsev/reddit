package com.example.reddit.presentation.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.reddit.R

class MainActivity : AppCompatActivity(), MainContract.View {

    private var presenter: MainContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter()
        presenter?.onCreate(this)
    }

    override fun onDestroy() {
        presenter?.onDestroy()

        super.onDestroy()
    }

    override fun renderError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
