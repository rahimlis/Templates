package com.rahimlis.templates.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button

import com.rahimlis.templates.R

import java.util.ArrayList

class MainActivity : AppCompatActivity(), A {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = ArrayList<String>()
        list.add("Aaa")
        list.add("Baa")
        list.add("Caa")
        a()

        val button = Button(this)

        button.setOnClickListener { v -> Log.e("a", "a") }

    }


}

internal interface A {
    fun a() {
        Log.e("MainAct", "hee")
    }
}

internal interface B {
    fun b()
}
