
package com.inaki.starwarsmvpapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inaki.starwarsmvpapp.R
import com.inaki.starwarsmvpapp.model.characters.Character
import com.inaki.starwarsmvpapp.presenters.CharactersPresenter
import com.inaki.starwarsmvpapp.presenters.ICharactersView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * This fragment is implementing the [ICharactersView] that is our view contract
 * In order to update the UI
 */
class CharactersFragment : Fragment(), ICharactersView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // This variable is creating our presenter that we need to use
    private val presenter: CharactersPresenter by lazy {
        CharactersPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onResume() {
        super.onResume()

        // I am calling the get characters method from presenter
        // this method will allow us to retrieve data from server
        // and the result will be posted into the view contract by the methods
        // character updated and onError data
        presenter.getCharacterFromServer()
    }

    override fun characterUpdated(characters: List<Character>) {
        // Here you add the logic to update the recycler view
    }

    override fun onErrorData(error: Throwable) {
        // here you add logic for showing the error to the user
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CharactersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            CharactersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}