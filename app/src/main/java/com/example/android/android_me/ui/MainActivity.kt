package com.example.android.android_me.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.GridView

import com.example.android.android_me.R
import com.example.android.android_me.data.AndroidImageAssets

class MainActivity : AppCompatActivity(), MasterListFragment.OnImageClickListener {
    var headIndex = 0
    var bodyIndex = 0
    var legIndex = 0

    // Track whether we are in two-pane or single-pane mode
    var mTwoPane = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (findViewById(R.id.android_me_linear_layout) != null) {
            mTwoPane = true

            // Hide 'Next' button
            (findViewById(R.id.btn_next) as Button).visibility = View.GONE
            // Set grid view columns to 2
            (findViewById(R.id.master_list_grid) as GridView).numColumns = 2

            if (savedInstanceState == null) {
                setupFragments()
            }
        }
    }

    override fun onImageSelected(position: Int) {
        val bodyPartIndex: Int = position / 12
        val listIndex: Int = position - 12 * bodyPartIndex

        when (bodyPartIndex) {
            0 -> headIndex = listIndex
            1 -> bodyIndex = listIndex
            2 -> legIndex = listIndex
        }

        if (mTwoPane) {
            var imageList: List<Int>
            var container: Int
            when (bodyPartIndex) {
                0 -> {
                    imageList = AndroidImageAssets.heads
                    container = R.id.head_container
                }
                1 -> {
                    imageList = AndroidImageAssets.bodies
                    container = R.id.body_container
                }
                2 -> {
                    imageList = AndroidImageAssets.legs
                    container = R.id.leg_container
                }
                else -> {
                    imageList = AndroidImageAssets.heads
                    container = R.id.head_container
                }
            }
            updateBodyPartFragment(container, imageList, listIndex)

        } else {
            val b = Bundle()
            b.putInt("headIndex", headIndex)
            b.putInt("bodyIndex", bodyIndex)
            b.putInt("legIndex", legIndex)

            val intent = Intent(this, AndroidMeActivity::class.java)
            intent.putExtras(b)

            val button = findViewById(R.id.btn_next) as Button
            button.setOnClickListener {
                startActivity(intent)
            }
        }
    }

    fun updateBodyPartFragment(container: Int, imageList: List<Int>, imageIndex: Int) {
        val fragment = BodyPartFragment(imageList, imageIndex)
        supportFragmentManager.beginTransaction()
                .add(container, fragment)
                .commit()
    }

    fun setupFragments() {
        val containers = arrayOf(R.id.head_container, R.id.body_container, R.id.leg_container)
        val partIndices = arrayOf(
                intent.getIntExtra("headIndex", headIndex),
                intent.getIntExtra("bodyIndex", headIndex),
                intent.getIntExtra("legIndex", headIndex)
        )
        val imageLists = arrayOf(
                AndroidImageAssets.heads,
                AndroidImageAssets.bodies,
                AndroidImageAssets.legs
        )
        val elements = containers.zip(partIndices).zip(imageLists)
        elements.map { (containerIndexPair, imageList) ->
            updateBodyPartFragment(containerIndexPair.first, imageList, containerIndexPair.second)
        }
    }
}
