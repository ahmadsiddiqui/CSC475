package dev.asid.notetoself;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView;



class NoteAdapter (
    private val mainActivity: MainActivity,
    private val noteList:List<Note>)
     :RecyclerView.Adapter<NoteAdapter.ListItemHolder>(){
    inner class ListItemHolder (view: View):
        RecyclerView.ViewHolder(view),
        View.OnClickListener{
            internal var title = view.findViewById<View>(R.id.textViewTitle) as TextView

            init{
                view.isClickable = true
                view.setOnClickListener(this)
            }
        override fun onClick(view: View){
            mainActivity.showNote(adapterPosition)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem, parent, false)
        return ListItemHolder(itemView)

    }

    override fun onBindViewHolder(holder:ListItemHolder, position: Int) {
        val note = noteList[position]
        holder.title.text = note.title

    }

    override fun getItemCount(): Int {
        if(noteList != null){
            return noteList.size
        }
        return -1;
    }

}

