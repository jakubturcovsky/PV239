package cz.pv239.seminar2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ExerciseFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflating layout XML into a view
        val view = inflater.inflate(R.layout.fragment_exercise, container, false)

        // Add on button click listener
        // Inside it, get text from edittext and place it into textview

        return view
    }
}