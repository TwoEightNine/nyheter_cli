package global.msnthrp.nyheter.utils

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import global.msnthrp.nyheter.R

/**
 * Created by twoeightnine on 2/13/18.
 */

fun hasPermissions(activity: Activity) = ContextCompat.checkSelfPermission(activity,
        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

@TargetApi(23)
fun requestPermissions(activity: Activity, requestCode: Int) {
    activity.requestPermissions(arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE
    ), requestCode)
}