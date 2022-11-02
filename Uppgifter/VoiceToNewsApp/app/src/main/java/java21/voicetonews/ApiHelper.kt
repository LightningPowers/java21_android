package java21.voicetonews

import android.util.Log
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class ApiHelper {

    companion object {
        // Takes api call and returns a list of beans
        //public fun JsonToBeans(response: JSONObject, searchTerm: String, sortBy: String) :ArrayList<ArticleBean> {
        public fun jsonToBeans() :ArrayList<ArticleBean> {

            //Todo: Add timer delay when doing api call


            val placeholderBean: ArticleBean = ArticleBean(
                "Man Can’t Remember What He Ran Into Burning Building For",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                "Fauxnews",
                "2022-10-31T14:05:01Z")
            val beanList = arrayListOf(placeholderBean)

            //Todo: Add (for?) loop that adds all articles

            beanList.add(placeholderBean)
            beanList.add(placeholderBean)
            beanList.add(placeholderBean)
            beanList.add(placeholderBean)
            beanList.add(placeholderBean)
            beanList.add(placeholderBean)
            beanList.add(placeholderBean)
            beanList.add(placeholderBean)


            return beanList
        }

        // Calls the api with given parameters, returns response object
        fun jsonToArticles(response: JSONObject) : ArrayList<ArticleBean> {
            val beanList: ArrayList<ArticleBean> = arrayListOf() //Todo: put in data here

            //Todo: Do json conversion here



            return beanList
        }
    }
}