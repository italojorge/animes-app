package br.com.animes.base.feature.utils.extensions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Process
import br.com.animes.base_feature.BuildConfig
import kotlin.system.exitProcess

fun Activity.closeApp() {
    moveTaskToBack(true)
    Process.killProcess(Process.myPid())
    exitProcess(0)
}

fun Activity.openAppInPlaystoreAndFinish() {
    val appPackageName = if (BuildConfig.DEBUG) {
        packageName.removeSuffix(".debug")
    } else packageName

    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$appPackageName")
            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        )
        finish()
    } catch (exception: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        )
        finish()
    }
}
