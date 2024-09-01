package dev.asid.notetoself

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogShowNote: DialogFragment() {
    private var note:Note? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(this.requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_show_note, null)
        val txtTitle = dialogView.findViewById<TextView>(R.id.txtTitle)
        val txtDescription = dialogView.findViewById<TextView>(R.id.txtDescription)
        txtTitle.text = note!!.title
        txtDescription.text = note!!.description
        val btnDelete = dialogView.findViewById<Button>(R.id.btnDelete)
        val btnOK = dialogView.findViewById<Button>(R.id.btnOK)
        builder.setView(dialogView).setMessage("")
        btnOK.setOnClickListener{
            dismiss()
        }
        btnDelete.setOnClickListener(){
            val callingActivity = activity as MainActivity?
            callingActivity!!.deleteNote(note!!)
            dismiss()
        }

        return builder.create()
    }

    fun sendNoteSelected(noteSelected:Note){
        note = noteSelected
    }


}