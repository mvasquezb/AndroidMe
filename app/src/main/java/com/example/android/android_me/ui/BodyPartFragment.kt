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

/**
 * Created by pmvb on 17-08-09.
 */
class BodyPartFragment(
        var mImageIds: List<Int> = listOf(),
        var mImageIndex: Int = 0
) : Fragment() {

    companion object {
        val TAG = "BodyPartFragment"
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        // Restore saved state if present
        val imageIds = savedInstanceState?.getIntArray("image_ids")
        val imageIndex = savedInstanceState?.getInt("image_index", 0)
        mImageIds = imageIds?.toList() ?: mImageIds
        mImageIndex = imageIndex ?: mImageIndex


        val rootView = container?.inflate(R.layout.fragment_body_part, false)
        val imageView = rootView?.findViewById(R.id.body_part_image) as ImageView

        if (!mImageIds.isEmpty()) {
            imageView.setImageResource(mImageIds.get(mImageIndex))
        } else {
            Log.v(TAG, "This Fragment has an empty list of image id's")
        }

        imageView.setOnClickListener { view ->
            if (!mImageIds.isEmpty()) {
                if (mImageIndex < mImageIds.size - 1) {
                    mImageIndex++
                } else {
                    mImageIndex = 0
                }
                imageView.setImageResource(mImageIds.get(mImageIndex))
            }
        }

        return rootView
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putIntArray("image_ids", mImageIds.toIntArray())
        outState?.putInt("image_index", mImageIndex)
    }
}