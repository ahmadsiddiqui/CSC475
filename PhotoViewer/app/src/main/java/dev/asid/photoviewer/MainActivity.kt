package dev.asid.photoviewer

import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.Manifest
import android.util.Log
import android.util.Log.INFO
import java.security.Permission

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: PhotoAdapter
    private var photoList: List<String> = ArrayList()
    private var perms:String = Manifest.permission.READ_MEDIA_IMAGES
    private val PERMISSION_CODE = 100


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.RVImages)
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        ActivityCompat.requestPermissions(this, arrayOf(perms),PERMISSION_CODE )
    }


    private fun loadPhotos() {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null)
        Log.i("INFO", "made the cursor")
        cursor?.use {
            Log.i("INFO", "use the cursor")
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            Log.i("INFO", "column index")
            val count = cursor.columnCount

            for( i in 0..count-1) {
                val photoPath = cursor.getString(columnIndex)
                photoList = photoList + photoPath
            }
        }

        photoAdapter = PhotoAdapter(photoList, this)
        recyclerView.adapter = photoAdapter
        photoAdapter.notifyDataSetChanged()
        cursor?.close()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Granted!!!", Toast.LENGTH_SHORT).show()
            Log.i("INFO", "permission granted")
            loadPhotos()
        } else {
            Toast.makeText(this, "Permission Denied!!!", Toast.LENGTH_SHORT).show()
        }
    }
}
