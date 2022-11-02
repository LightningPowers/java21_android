package java21.voicetonews

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ApiHelper {

    companion object {
        // Calls the api with given response (JsonObject)
        @RequiresApi(Build.VERSION_CODES.O)
        fun jsonToArticles(response: JSONObject) : ArrayList<ArticleBean> {
            val beanList: ArrayList<ArticleBean> = arrayListOf() //Todo: put in data here

            //Template bean
            val baseBean: ArticleBean = ArticleBean(
                "Man Can’t Remember What He Ran Into Burning Building For",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                "Fauxnews",
                "2022-11-01 06:14:21")

            val articleList: JSONArray = response.getJSONArray("results")
            for (i in 0 until articleList.length()) {
                //Set value to placeholder (template) bean
                val jsonArticle = articleList.getJSONObject(i)

                //Get variables from data
                val source: String = jsonArticle.getString("source_id")
                val title: String = jsonArticle.getString("title")
                val date: String = jsonArticle.getString("pubDate")

                // Prevent against URLs that are null
                /*if (jsonArticle.optString("image_url", null).isNullOrBlank()){
                    beanArticle.imageURL = baseBean.imageURL
                    Log.d("testApi", "ImageUrl: " + beanArticle.imageURL)
                }
                else {
                    beanArticle.imageURL = jsonArticle.getString("image_url")
                    Log.d("testApi", "ImageUrl: " + beanArticle.imageURL)
                }*/

                //Format date
                val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val localDate = LocalDateTime.parse(date, firstApiFormat)
                // Add a zero if minutes are less than 10
                val minutes: String = if (localDate.minute.toString().length > 1) {
                    localDate.minute.toString().substring((localDate.minute.toString().length)-2)
                } else {
                    "0" + localDate.minute.toString().substring((localDate.minute.toString().length)-1)
                }
                val month: String = localDate.month.toString().lowercase()
                val finalDate: String = ("${localDate.hour}:${minutes} - ${month.replaceFirstChar{it.uppercase()}} ${localDate.dayOfMonth}")
                Log.d("parseTesting", finalDate)

                //Add bean to beanList
                beanList.add(ArticleBean(title,baseBean.imageURL,source.replaceFirstChar{it.uppercase()},finalDate))
            }
            return beanList
        }
    }
}