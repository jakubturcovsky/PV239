package cz.pv239.seminar2.exercise

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cz.pv239.seminar2.R
import cz.pv239.seminar2.exercise.ToastService.Companion.EXTRA_MESSAGE
import kotlinx.android.synthetic.main.fragment_exercise.*

class ExerciseFragment : Fragment() {

    /**
     * Inflates your custom layout into the provided layout (container: ViewGroup?).
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflating layout XML into a view
        // Third parameter should always be false, because FragmentManager handles attachment of the view, not you
        // https://stackoverflow.com/a/45809756/2207083
        return inflater.inflate(R.layout.fragment_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* Exercise 1 */
        // Add on button click listener
        button.setOnClickListener { textview.text = edittext.text }
        // Inside it, get text from edittext and place it into textview

        /* Exercise 2 */
        // Create Intent for starting ToastIntentServices
        start_service.setOnClickListener {
            val intent = Intent(context, ToastService::class.java)
            // Put text from the edittext into the intent as extra (check ToastService for a name of the extra)
            intent.putExtra(EXTRA_MESSAGE, edittext.text.toString())
            // start service using the created intent
            context?.startService(intent)
        }
    }
}