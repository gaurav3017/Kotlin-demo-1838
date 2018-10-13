@file:Suppress("NOTHING_TO_INLINE")

package `in`.browser.fiddle.extensions

import `in`.browser.fiddle.dialog.BrowserDialog
import androidx.appcompat.app.AlertDialog

/**
 * Ensures that the dialog is appropriately sized and displays it.
 */
inline fun AlertDialog.Builder.resizeAndShow() = BrowserDialog.setDialogSize(context, this.show())
