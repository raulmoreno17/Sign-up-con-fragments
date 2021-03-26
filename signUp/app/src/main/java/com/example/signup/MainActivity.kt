package com.example.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.fragment_login.*

class MainActivity : AppCompatActivity() {

    lateinit var fragmentoLogin: LoginFragment
    lateinit var fragmentoSuccess: SuccessFragment
    lateinit var fragmentoFail: FailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentoLogin = LoginFragment()
        fragmentoSuccess = SuccessFragment()
        fragmentoFail = FailFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.contenedor,fragmentoLogin)
            commit()
        }

    }

    fun clickHandler(view: View) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.contenedor,fragmentoSuccess)
            commit()}
    }
}