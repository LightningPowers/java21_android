package java21.voicetonews

import org.json.JSONObject

class ApiHelper {

    companion object {
        // Makes the api call and returns a list of beans made of call
        //public fun JsonToBeans(response: JSONObject, searchTerm: String, sortBy: String) :ArrayList<ArticleBean> {
        public fun JsonToBeans() :ArrayList<ArticleBean> {

            //Todo: Add timer delay when doing api call


            var placeholderBean: ArticleBean = ArticleBean(
                "Man Can’t Remember What He Ran Into Burning Building For",
                "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
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