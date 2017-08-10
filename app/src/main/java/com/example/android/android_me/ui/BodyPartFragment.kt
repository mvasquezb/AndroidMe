package com.example.android.android_me.ui

import android.os.Bundle
import android.support.v4.app.Fragment
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
class BodyPartFragment: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val rootView = container?.inflate(R.layout.fragment_body_part, false)
        val imageView = rootView?.findViewById(R.id.body_part_image) as ImageView

        imageView.setImageResource(AndroidImageAssets.heads[0])
        return rootView
    }
}