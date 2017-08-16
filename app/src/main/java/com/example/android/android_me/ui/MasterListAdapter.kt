/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import com.example.android.android_me.R


// Custom adapter class that displays a list of Android-Me images in a GridView
class MasterListAdapter
/**
 * Constructor method
 * Keeps track of the context and list of images to display
 * @param imageIds The list of images to display
 */
(private val mContext: Context, private val mImageIds: List<Int>) : BaseAdapter() {
    private val mChecked = BooleanArray(mImageIds.size)

    /**
     * Returns the number of items the adapter will display
     */
    override fun getCount(): Int = mImageIds.size

    override fun getItem(i: Int): Any? = mImageIds[i]

    override fun getItemId(i: Int): Long = 0

    fun setCheckedAt(i: Int, checked: Boolean) {
        mChecked[i] = checked
    }

    fun toggleCheckedAt(i: Int) {
        mChecked[i] = !mChecked[i]
    }

    fun getCheckedAt(i: Int): Boolean {
        return mChecked[i]
    }

    /**
     * Creates a new ImageView for each item referenced by the adapter
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val imageView: ImageView
        if (convertView == null) {
            // If the view is not recycled, this creates a new ImageView to hold an image
            imageView = ImageView(mContext)
            // Define the layout parameters
            imageView.adjustViewBounds = true
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(8, 8, 8, 8)
        } else {
            imageView = convertView as ImageView
        }

        if (getCheckedAt(position)) {
            imageView.setBackgroundColor(R.color.material_blue_grey_800)
        } else {
            imageView.setBackgroundColor(android.R.color.transparent)
        }

        // Set the image resource and return the newly created ImageView
        imageView.setImageResource(mImageIds[position])
        return imageView
    }
}
