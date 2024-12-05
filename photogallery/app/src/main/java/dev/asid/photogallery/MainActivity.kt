package dev.asid.photogallery

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var mSerializer :JSONSerializer? = null
    private var imageList: ArrayList<Image>? = null
    private var recyclerView: RecyclerView? = null
    private var imageAdapter: ImageAdapter? = null
    private var uri: Uri? = null
    private var imageTitle: String? = null

   var getContent =
       registerForActivityResult(ActivityResultContracts.GetContent()){
           showEnterTitleDialog(this){title ->
               imageTitle = title
        }
           val flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
           uri?.let { contentResolver.takePersistableUriPermission(it, flags) }

    }
    private fun saveImageData(){
        try {
            mSerializer!!.save(this.imageList!!)
            Toast.makeText(this, "Images saved", Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception){
            Toast.makeText(this, "Error saving images", Toast.LENGTH_SHORT).show()
        }
    }

    fun createNewImage(im: Image){
        imageList!!.add(im)
        imageAdapter!!.notifyDataSetChanged()
        saveImageData()
    }

    fun showImage (imageToShow: Int){
        //TODO: Add code to show image in a dialog box

    }

    override fun onPause() {
        super.onPause()
        saveImageData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            loadImage()
        }

        mSerializer = JSONSerializer("PhotoGallery.json", applicationContext)
        try{
            imageList = mSerializer!!.load()
            Toast.makeText(this, "Images loaded", Toast.LENGTH_SHORT).show()
            imageAdapter = ImageAdapter(this, imageList!!)
        }
        catch (e: Exception){
            imageList = ArrayList()
            Toast.makeText(this, "Error loading images", Toast.LENGTH_SHORT).show()
        }

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        imageAdapter = ImageAdapter(this,this.imageList!!)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = imageAdapter
        recyclerView!!.itemAnimator= DefaultItemAnimator()

    }
    fun loadImage() {

        getContent.launch("image/*")


        Toast.makeText(this, "Image Title: $imageTitle", Toast.LENGTH_SHORT).show()

        val im = Image()
        im.title = imageTitle
        im.uri = uri.toString()
        if(uri != null){createNewImage(im)}
        for(image in imageList!!){
            Log.i("ImageTitle", image.uri.toString())

        }
        imageAdapter!!.notifyDataSetChanged()
        saveImageData()


    }

    fun showEnterTitleDialog(context: Context, onSubmit: (String) -> Unit) {
        val dialogView =
            LayoutInflater.from(context).inflate(R.layout.dialog_enter_title, null)
        val editTextTitle = dialogView.findViewById<EditText>(R.id.editTextTitle)

        val dialog = AlertDialog.Builder(context)
            .setTitle("Enter Title")
            .setView(dialogView)
            .setPositiveButton("OK") { _, _ ->
                onSubmit(editTextTitle.text.toString())
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }


}