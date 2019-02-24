package cz.pv239.seminar2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    companion object {

        private val TAG = FirstFragment::class.java.simpleName

        private const val ARG_TEXT = "text"

        fun newInstance(): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putString(ARG_TEXT, "Random text")

            return fragment
        }
    }

    private var text: String? = null

    /**
     * Fragment attached to its Activity. Fragment yet merely exists.
     */
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }

    /**
     * Fragment creation or state restoration. No views.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        // Restore variable content after orientation change
        if (savedInstanceState != null) {
            text = savedInstanceState.getString(ARG_TEXT, null)
        }
    }

    /**
     * Here come the views.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        // Inflating Fragment with layout and its views
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    /**
     * Activity is completely created.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    /**
     * Orientation change or pressing home button has happened. Let's save variables' states.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        // Save variable content to the Bundle
        outState.putString(ARG_TEXT, text)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
    }
}
