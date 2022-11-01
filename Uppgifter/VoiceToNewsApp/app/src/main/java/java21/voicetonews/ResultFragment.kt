package java21.voicetonews

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class ResultFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    private fun addArticle (view: View, bean: ArticleBean){

        // Finds scrollview in parent (fragment_result) layout xml
        val articleContainer = view.findViewById<View>(R.id.articleContainer) as ScrollView

        // Finds linearLayout in parent (articleFeed) layout xml
        val articleFeed = articleContainer.findViewById<View>(R.id.articleFeed) as LinearLayout

        // Identifies article item layout xml
        val article: View = layoutInflater.inflate(R.layout.article_item,null)

        // Author/Source
        val sourceText: TextView = article.findViewById(R.id.articleSource)
        sourceText.text = bean.source

        // Title
        val titleText: TextView = article.findViewById(R.id.articleTitle)
        titleText.text = bean.title

        // Image with Glide library
        val imageBox: ImageView = article.findViewById(R.id.articleImage)
        Glide.with(view).load(bean.imageURL).into(imageBox)
        articleFeed.addView(article)

        // Upload date
        val dateText: TextView = article.findViewById(R.id.articleDate)

        val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

        val date2 = LocalDateTime.parse(bean.uploadDate, firstApiFormat)
        // Add a zero if minutes are less than 10
        val minutes: String = if (date2.minute.toString().length > 1) {
            date2.minute.toString().substring((date2.minute.toString().length)-2)
        } else {
            "0" + date2.minute.toString().substring((date2.minute.toString().length)-1)
        }
        val finalDate: String = ("${date2.hour}:${minutes} - ${date2.month} ${date2.dayOfMonth}")
        Log.d("parseTesting", finalDate)
        dateText.text = finalDate
    }
}