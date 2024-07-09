package project.fetchData

import org.json.JSONObject

fun jsonStringToMap(jsonString: String, checkFor: List<String>): Map<String, Any?> {
    val jsonObject = JSONObject(jsonString)
    val map = mutableMapOf<String, Any?>()
    for (key in jsonObject.keys()) {
        if (key !in checkFor) continue
        map[key] = jsonObject[key]
    }

    return map
}