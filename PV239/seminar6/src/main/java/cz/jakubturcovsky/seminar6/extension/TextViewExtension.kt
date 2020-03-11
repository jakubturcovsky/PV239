import android.widget.TextView
import androidx.annotation.StringRes

fun TextView.setText(@StringRes resId: Int, vararg args: Any?) {
    text = context.getString(resId, *args)
}

fun TextView.setTextOrHide(text: CharSequence?) {
    if (text.isNullOrEmpty()) {
        hide()
    } else {
        this.text = text
        show()
    }
}

fun TextView.getIntValue(default: Int = 0): Int {
    return try {
        text?.toString()?.toInt() ?: default
    } catch (e: Exception) {
        default
    }
}

var TextView.intVal: Int
    get() = getIntValue(0)
    set(value) {
        text = value.toString()
    }

fun TextView.getLongValue(default: Long = 0L): Long {
    return try {
        text?.toString()?.toLong() ?: default
    } catch (e: Exception) {
        default
    }
}

var TextView.longVal: Long
    get() = getLongValue(0L)
    set(value) {
        text = value.toString()
    }