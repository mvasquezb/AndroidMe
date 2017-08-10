package com.example.android.android_me.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Button

import com.example.android.android_me.R

class MainActivity : AppCompatActivity(), MasterListFragment.OnImageClickListener {
    var headIndex = 0
    var bodyIndex = 0
    var legIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onImageSelected(position: Int) {
//        Snackbar.make(findViewById(R.id.master_list_fragment), "Position clicked = " + position, Snackbar.LENGTH_SHORT).show()

        val bodyPartIndex = position / 12
        val listIndex = position - 12 * bodyPartIndex

        when (listIndex) {
            0 -> headIndex = listIndex
            1 -> bodyIndex = listIndex
            2 -> legIndex = listIndex
        }

        val b = Bundle()
        b.putInt("headIndex", headIndex)
        b.putInt("bodyIndex", bodyIndex)
        b.putInt("legIndex", legIndex)

        val intent = Intent(this, AndroidMeActivity::class.java)
        intent.putExtras(b)

        val button = findViewById(R.id.btn_next) as Button
        button.setOnClickListener { startActivity(intent) }
    }
}
