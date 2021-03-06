package br.com.animes.core.dialog

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import br.com.animes.base_feature.R
import org.koin.core.component.KoinComponent

object AlertDialogExtension : KoinComponent {
//    private val analyticsEvent: AnalyticsEvent by inject()

    fun Context.showAlert(
        message: CharSequence,
        title: CharSequence = "Ops!",
        @StringRes positiveButtonMessage: Int = R.string.dialog_ok_button_message,
        positiveButtonClickListener: (() -> Unit)? = null
    ) {
        setupShowAlertDialog(title, message, positiveButtonMessage, positiveButtonClickListener)
    }

    fun Context.showAlert(
        @StringRes title: Int,
        @StringRes message: Int,
        @StringRes positiveButtonMessage: Int = R.string.dialog_ok_button_message,
        positiveButtonClickListener: (() -> Unit)? = null
    ) {
        setupShowAlertDialog(
            title = getString(title),
            message = getString(message),
            positiveButtonMessage = positiveButtonMessage,
            positiveButtonClickListener
        )
    }

    fun Context.showErrorAlert(error: Throwable, positiveButtonClickListener: (() -> Unit)? = null) {
        error.message?.let {
            showAlert(title = "Ops!", message = it, positiveButtonClickListener = positiveButtonClickListener)
        }
    }

    fun Fragment.showErrorAlert(error: Throwable, positiveButtonClickListener: (() -> Unit)? = null) {
        requireContext().showErrorAlert(error, positiveButtonClickListener = positiveButtonClickListener)
    }

    private fun Context.setupShowAlertDialog(
        title: CharSequence,
        message: CharSequence,
        positiveButtonMessage: Int,
        positiveButtonClickListener: (() -> Unit)?
    ) {
        if (this is FragmentActivity && (isFinishing || lifecycle.currentState == Lifecycle.State.DESTROYED)) return

        try {
            AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonMessage) { _, _ ->
                    positiveButtonClickListener?.invoke()
                }
                .setCancelable(false)
                .create()
                .show()
        } catch (e: Exception) {
//            analyticsEvent.recordException(e)
        }
    }
}
