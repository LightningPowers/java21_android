package java21.voicetonews

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment


class ResultFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_result, container, false)

        //Todo: Add items (articles) dynamically using companion arraylist

        //var bean1 = MainActivity.beanList[0]
        //addArticle(view, bean1)
        for (bean in MainActivity.beanList){
            addArticle(view, bean)
        }

        //Todo: Add articles with foreach loop

        //Resets companion variables when using backstack
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isEnabled){
                    MainActivity.searchTerm = ""
                    MainActivity.sortByMethod = ""
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
            }
        })

        return view
    }

    // Adds article item with provided bean data
    @SuppressLint("MissingInflatedId")
    private fun addArticle (view: View, bean: ArticleBean){

        // Finds scrollview in parent (fragment_result) layout xml
        val articleContainer = view.findViewById<View>(R.id.articleContainer) as ScrollView

        // Finds linearLayout in parent (articleFeed) layout xml
        val articleFeed = articleContainer.findViewById<View>(R.id.articleFeed) as LinearLayout

        // Identifies article item layout xml
        val article: View = layoutInflater.inflate(R.layout.article_item,null)

        val sourceText: TextView = article.findViewById(R.id.articleSource)
        sourceText.text = "I Made it Up" // MainActivity.source

        val titleText: TextView = article.findViewById(R.id.articleTitle)
        titleText.text = "Man Can’t Remember What He Ran Into Burning Building For"

        val imageUrl: ImageView = article.findViewById(R.id.articleImage)
        //Todo: Set image url

        articleFeed.addView(article)
    }
}