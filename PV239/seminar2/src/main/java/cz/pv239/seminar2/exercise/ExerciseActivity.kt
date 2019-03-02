package cz.pv239.seminar2.exercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.pv239.seminar2.R

// TODO Don't forget to register this activity in manifest!
class ExerciseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set content view
        setContentView(R.layout.activity_exercise)

        // Get FragmentManager
        val fragmentManager = supportFragmentManager

        // Create Fragment
        val fragment = ExerciseFragment()

        // Add Fragment to the FragmentManager, don't forget to commit
        fragmentManager.beginTransaction().add(R.id.content,fragment).commit()
    }
}