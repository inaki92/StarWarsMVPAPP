
package com.inaki.starwarsmvpapp.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.inaki.starwarsmvpapp.R
import com.inaki.starwarsmvpapp.StarWarsApplication
import com.inaki.starwarsmvpapp.di.AppModule
import com.inaki.starwarsmvpapp.di.DaggerStarWarsComponent
import com.inaki.starwarsmvpapp.di.StarWarsComponent
import com.inaki.starwarsmvpapp.model.characters.Character
import com.inaki.starwarsmvpapp.presenters.CharactersPresenter
import com.inaki.starwarsmvpapp.presenters.ICharactersView
import javax.inject.Inject

class CharactersFragment : Fragment(), ICharactersView {

    // This variable is creating our presenter that we need to use
//    private val presenter: CharactersPresenter by lazy {
//        CharactersPresenter(this)
//    }

    // Here we are injecting our presenter
    @Inject lateinit var presenter: CharactersPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // here I am telling dagger to inject all the members annotated with @Inject in this fragment
        StarWarsApplication.startWarsComponent.inject(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.initPresenter(this)
        presenter.checkNetworkState()
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
        Toast.makeText(requireContext(), characters[0].name, Toast.LENGTH_LONG).show()
        Log.d("CharactersFragment", characters.toString())
    }

    override fun onErrorData(error: Throwable) {
        // here you add logic for showing the error to the user
        Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_LONG).show()
        Log.e("CharactersFragment", error.stackTraceToString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // this method will clear the disposable from the presenter
        presenter.destroyPresenter()
    }

    companion object {
        @JvmStatic
        fun newInstance() = CharactersFragment()
    }
}