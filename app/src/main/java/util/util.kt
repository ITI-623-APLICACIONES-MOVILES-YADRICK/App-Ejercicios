package util

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

const val EXTRA_MESSAGE_CONTACTID = "BIENVENIDOS"

object Util {
    fun openActivity(context: Context, objClass: Class<*>, extraName: String, value: String?) {
        val intent = Intent(context, objClass).apply { putExtra(extraName, value) }
        startActivity(context, intent, null)
    }
}
