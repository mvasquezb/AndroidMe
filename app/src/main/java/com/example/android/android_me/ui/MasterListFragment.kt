package com.example.android.android_me.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.android.android_me.R
import com.example.android.android_me.commons.extensions.inflate
import com.example.android.android_me.data.AndroidImageAssets

/**
 * Created by pmvb on 17-08-10.
 */

class MasterListFragment : Fragment() {
    var mImageClickCallback: OnImageClickListener? = null

    interface OnImageClickListener {
        fun onImageSelected(position: Int)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mImageClickCallback = context as OnImageClickListener
        } catch (ex: ClassCastException) {
            throw ClassCastException(context.toString()
                    + "must implement OnImageClickListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val rootView = container?.inflate(R.layout.fragment_master_list, inflater = inflater)
        val rootView = inflater?.inflate(R.layout.fragment_master_list, container, false)
        val gridView = rootView?.findViewById(R.id.master_list_grid) as GridView

        gridView.adapter = MasterListAdapter(context, AndroidImageAssets.all)

        gridView.setOnItemClickListener { adapterView, view, position, l ->
            mImageClickCallback?.onImageSelected(position)
        }

        return rootView
    }
}
