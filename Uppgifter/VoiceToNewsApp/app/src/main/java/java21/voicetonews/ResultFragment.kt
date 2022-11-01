package java21.voicetonews

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
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
        /*
        val scroll = view.findViewById(R.id.articleFeed) as ScrollView
        inflater.inflate(R.layout.article_item, null)
        scroll.addView(view)*/

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
}