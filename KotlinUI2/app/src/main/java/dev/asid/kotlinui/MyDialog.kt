package dev.asid.kotlinui

import androidx.fragment.app.DialogFragment
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.app.Dialog

class MyDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(this.requireActivity())
        builder.setMessage("Make a selection")
            .setPositiveButton("OK", { dialog, id -> })
            .setNegativeButton("Cancel", {dialog, id ->})
        return builder.create()





    }


}