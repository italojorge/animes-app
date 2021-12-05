package br.com.animes.core.extensions

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import br.com.animes.base_feature.R

fun Fragment.hideActionBar() = (activity as? AppCompatActivity)?.supportActionBar?.hide()

fun Fragment.showActionBar() = (activity as? AppCompatActivity)?.supportActionBar?.show()

fun Fragment.setToolbarLogo(@DrawableRes resId: Int?) {
    if (resId == null) {
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayUseLogoEnabled(false)
    } else {
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayUseLogoEnabled(true)
        (activity as? AppCompatActivity)?.supportActionBar?.setLogo(resId)
    }
}

fun Fragment.setToolbarTitle(title: String) {
    (context as? AppCompatActivity)?.title = title
}

fun Fragment.setNavigationIcon(id: Int?) {
    (activity as AppCompatActivity?)?.supportActionBar?.apply {
        id?.let { setHomeAsUpIndicator(it) }
        setDisplayHomeAsUpEnabled(id != null)
    }
    showActionBar()
}

fun Fragment.getFont(fontId: Int) = ResourcesCompat.getFont(requireContext(), fontId)

fun Fragment.makeCall(phoneNumber: String) {
    startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber")))
}

fun Fragment.openWhatsapp(number: String, message: String = "") {
    val packageName = "com.whatsapp"
    if (!isPackageInstalled(packageName)) {
        openPackageInStore(packageName)
        return
    }

    startActivity(
        Intent(Intent.ACTION_VIEW, Uri.parse("smsTo:$number/*")).apply {
            data = Uri.parse("http://api.whatsapp.com/send?phone=$number&text=$message")
        }
    )
}

private fun Fragment.isPackageInstalled(packageName: String) = try {
    requireContext().packageManager.getPackageInfo(packageName, 0)
    true
} catch (e: PackageManager.NameNotFoundException) {
    false
}

fun Fragment.openPackageInStore(packageName: String) = try {
    val appStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
    appStoreIntent.setPackage("com.android.vending")
    startActivity(appStoreIntent)

} catch (exception: ActivityNotFoundException) {
    try {
        startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName"))
        )
    } catch (e: Exception) {
    }
}

fun Fragment.getColor(@ColorRes colorId: Int) = ContextCompat.getColor(requireContext(), colorId)
fun Fragment.getDrawable(@ColorRes drawableId: Int) = ContextCompat.getColor(requireContext(), drawableId)

fun Fragment.copyToClipboard(text: String, label: String = "clipboard") {
    val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText(label, text))
}

fun Fragment.openDetailInSettings() = Intent().run {
    action = ACTION_APPLICATION_DETAILS_SETTINGS
    addCategory(Intent.CATEGORY_DEFAULT)
    data = Uri.parse("package:" + requireContext().packageName)
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
    startActivity(this)
}

fun Fragment.softInputAdjustNothing() {
    requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
}

fun Fragment.hideKeyboard() {
    val activity = requireActivity()
    val view = activity.window.currentFocus
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    view?.let {
        imm?.let {
            if (it.isAcceptingText) {
                it.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }
}