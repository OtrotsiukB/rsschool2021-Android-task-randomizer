package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.lang.Exception
import kotlin.math.min

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    var listener: i_openFragment?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        // var result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        val result = listener?.getnumerForBack()

        previousResult?.text = "Previous result: ${result.toString()}"

        // TODO: val min = ...
        val min = view.findViewById<EditText>(R.id.min_value)
        // TODO: val max = ...
        val max = view.findViewById<EditText>(R.id.max_value)

        generateButton?.setOnClickListener {
            // TODO: send min and max to the SecondFragment
            try {
                if (min.text.toString() != "" && max.text.toString() != "") {
                    val minInt = min.text.toString().toInt() ?: -1
                    val maxInt = max.text.toString().toInt() ?: -1
                    if (minInt > -1 && maxInt > -1 && minInt < maxInt && maxInt < 2147483648 && maxInt < 2147483648) {
                        listener?.openSecondFragmentViaInteface(
                            min.text.toString().toInt(),
                            max.text.toString().toInt()
                        )
                    } else {
                        listener?.showToast("Bходные данные невалидны")
                    }
                } else {
                    listener?.showToast("Bходные данные невалидны")
                }
            } catch (e: Exception) {
                listener?.showToast("Bходные данные невалидны")
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}