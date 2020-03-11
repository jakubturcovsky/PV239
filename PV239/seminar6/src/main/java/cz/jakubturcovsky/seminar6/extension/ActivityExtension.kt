
import android.app.Activity
import cz.jakubturcovsky.seminar6.App

val Activity.context get() = this

val Activity.app get() = application as App
