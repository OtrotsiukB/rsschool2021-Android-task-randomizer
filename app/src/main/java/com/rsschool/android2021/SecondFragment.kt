package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    private var backButton: Button? = null
    private var result: TextView? = null
    var listener: i_openFragment?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        result?.text = generate(min, max).toString()

        listener?.setnumerForBack(result?.text.toString().toInt())
        backButton?.setOnClickListener {
            listener?.openFirstFragmentViaInteface(result?.text.toString().toInt()?:0)
        }
    }

    private fun generate(min: Int, max: Int): Int {
        val rnds = (min..max).random()
        return rnds
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is i_openFragment){
            listener=context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listener=null
    }
    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putInt(SecondFragment.MIN_VALUE_KEY, min)
            args.putInt(SecondFragment.MAX_VALUE_KEY, max)
            fragment.arguments = args
            // TODO: implement adding arguments

            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}