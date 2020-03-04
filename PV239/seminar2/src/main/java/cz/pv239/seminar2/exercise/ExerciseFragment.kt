package cz.pv239.seminar2.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_exercise.*

class ExerciseFragment : Fragment() {

    /**
     * Inflates your custom layout into the provided layout (container: ViewGroup?).
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflating layout XML into a view
        // Third parameter should always be false, because FragmentManager handles attachment of the view, not you
        // https://stackoverflow.com/a/45809756/2207083
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* Exercise 1 */
        // Add on button click listener
        // Inside it, get text from edittext and place it into textview

        /* Exercise 2 */
        startService.setOnClickListener {
            // Create Intent for starting ToastIntentServices

            // Put text from the editText into the intent as extra (check ToastService for a name of the extra)

            // start service using the created intent

        }
    }
}