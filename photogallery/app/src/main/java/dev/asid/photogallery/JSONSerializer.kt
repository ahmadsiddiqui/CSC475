package dev.asid.photogallery

import android.content.Context
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.Writer

class JSONSerializer (
    private val filename:String,
    private val context: Context,
) {
    @Throws (IOException::class)
    fun save(notes:List<Image>){
        val jArray = JSONArray()
        for (n in notes){
            jArray.put(n.toJSON())
        }

        var writer: Writer? = null
        try{
            val out = context.openFileOutput(filename, Context.MODE_PRIVATE)
            writer = OutputStreamWriter(out)
            writer.write(jArray.toString())
        } finally {
            writer?.close()
        }

    }

    @Throws(IOException::class, JSONException::class)
    fun load():ArrayList<Image>{
        val imageList = ArrayList<Image>()
        var reader: BufferedReader? = null

        try{
            val `in` = context.openFileInput(filename)
            reader = BufferedReader(InputStreamReader(`in`))
            val jsonString= StringBuilder()

            for(line in reader.readLine()){
                jsonString.append(line)

            }
            val jArray = JSONTokener(jsonString.toString()).nextValue() as JSONArray

            for(i in 0 until jArray.length()){
                imageList.add(Image(jArray.getJSONObject(i)))

            }
        }catch(e:FileNotFoundException){
            Toast.makeText(context, "Data not found", Toast.LENGTH_SHORT).show()


        }finally{
            reader!!.close()
        }
        return imageList
    }

}