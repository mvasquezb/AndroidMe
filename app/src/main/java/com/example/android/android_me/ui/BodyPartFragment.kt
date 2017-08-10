package com.example.android.android_me.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.android.android_me.R
import com.example.android.android_me.commons.extensions.inflate
import com.example.android.android_me.data.AndroidImageAssets

/**
 * Created by pmvb on 17-08-09.
 */
class BodyPartFragment(
        var mImageIds: List<Int> = listOf(),
        var mListIndex: Int = 0
) : Fragment() {

    companion object {
        val TAG = "BodyPartFragment"
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val rootView = container?.inflate(R.layout.fragment_body_part, false)
        val imageView = rootView?.findViewById(R.id.body_part_image) as ImageView

        if (!mImageIds.isEmpty()) {
            imageView.setImageResource(mImageIds.get(mListIndex))
        } else {
            Log.v(TAG, "This Fragment has an empty list of image id's")
        }

        return rootView
    }
}