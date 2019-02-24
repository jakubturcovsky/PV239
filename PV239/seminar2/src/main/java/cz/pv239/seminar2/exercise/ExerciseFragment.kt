package cz.pv239.seminar2.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cz.pv239.seminar2.R

class ExerciseFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        /* Exercise 1 */
        // Inflating layout XML into a view
        val view = inflater.inflate(R.layout.fragment_exercise, container, false)

        // Add on button click listener
        // Inside it, get text from edittext and place it into textview

        /* Exercise 2 */
        // Create Intent for starting ToastIntentService
        // Put text from the edittext into the intent as extra (check ToastIntentService, for some name of the extra)
        // start service using the created intent

        return view
    }
}