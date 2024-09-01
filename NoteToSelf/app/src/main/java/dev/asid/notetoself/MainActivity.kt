package dev.asid.notetoself

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.asid.notetoself.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var mSerializer: JSONSerializer? = null
    private var noteList: ArrayList<Note>? = null
    private var recyclerView: RecyclerView? = null
    private var adapter:NoteAdapter? = null

    private lateinit var binding: ActivityMainBinding

    private fun saveNotes(){
        try{
            mSerializer!!.save(this.noteList!!)
        } catch (e:Exception) {
            Log.e("Error Saving Note", "", e)
        }
    }
    fun createNewNote(n: Note){
        noteList!!.add(n)
        adapter!!.notifyDataSetChanged()
        saveNotes()
    }

    fun showNote(noteToShow: Int){
        val dialog = DialogShowNote()
        dialog.sendNoteSelected(noteList!![noteToShow])
        dialog.show(supportFragmentManager,"")
    }

    override fun onPause() {
        super.onPause()
        saveNotes()
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val dialog = DialogNewNote()
            dialog.show(supportFragmentManager, "")
        }

        mSerializer = JSONSerializer("NoteToSelf.json",applicationContext)
        try{
            noteList = mSerializer!!.load()
        } catch (e:Exception) {
            noteList = ArrayList()
            Log.e("Error loading notes","",e)

        }

        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        adapter = NoteAdapter(this, this.noteList!!)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()

        recyclerView!!.adapter = adapter

        recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent =Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun deleteNote(note: Note) {
        noteList?.remove(note)
        adapter!!.notifyDataSetChanged()
        saveNotes()
    }


}