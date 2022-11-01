package java21.voicetonews

import org.json.JSONObject

class ApiHelper {

    companion object {
        // Makes the api call and returns a list of beans made of call
        //public fun JsonToBeans(response: JSONObject, searchTerm: String, sortBy: String) :ArrayList<ArticleBean> {
        public fun JsonToBeans() :ArrayList<ArticleBean> {

            //Todo: Add timer delay when doing api call


            var placeholderBean: ArticleBean = ArticleBean(
                "Placeholder Title",
                "blob:https://genrandom.com/fc8f389c-88b7-4ceb-a5dc-c997c0060d1e",
                "Fauxnews",
                "15:04 - Nov 1st")
            var beanList = arrayListOf<ArticleBean>(placeholderBean)
            beanList.add(placeholderBean)
            beanList.add(placeholderBean)
            beanList.add(placeholderBean)


            return beanList
        }
    }

    // Calls the api with given parameters, returns response object
    /*private fun apiCall() :JSONObject {
        ...
    }*/
}