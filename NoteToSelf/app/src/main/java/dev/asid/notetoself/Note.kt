package dev.asid.notetoself

import org.json.JSONException
import org.json.JSONObject

class Note {
    var title: String? = null
    var description: String? = null

    private val JSON_TITLE = "title"
    private val JSON_DESCRIPTION = "description"
    @Throws (JSONException::class)
    constructor(jo: JSONObject ){
        title = jo.getString(JSON_TITLE)
        description = jo.getString(JSON_DESCRIPTION)

    }
    constructor(){

    }

    @Throws (JSONException::class)
    fun convertToJSON(): JSONObject {
        val jo = JSONObject()
        jo.put(JSON_TITLE, title)
        jo.put(JSON_DESCRIPTION, description)

        return jo
    }
}