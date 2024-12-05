package dev.asid.photogallery

import android.net.Uri
import org.json.JSONException
import org.json.JSONObject

class Image {
    var title: String? = null
    var uri: String? = null

    private val JSON_TITLE = "title"
    private val JSON_URI = "uri"

    @Throws(JSONException::class)
    constructor(jo: JSONObject) {
        title = jo.getString(JSON_TITLE)
        uri = jo.getString(JSON_URI)
    }
    constructor(){}

    @Throws(JSONException::class)
    fun toJSON(): JSONObject {
        val jo = JSONObject()
        jo.put(JSON_TITLE, title)
        jo.put(JSON_URI, uri)
        return jo
    }

}