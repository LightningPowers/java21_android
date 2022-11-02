package java21.voicetonews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    companion object {
        var searchTerm: String = ""
        var sortByMethod: String = ""
        lateinit var beanList: ArrayList<ArticleBean>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}