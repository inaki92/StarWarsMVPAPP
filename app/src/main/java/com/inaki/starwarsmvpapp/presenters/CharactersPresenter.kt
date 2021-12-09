package com.inaki.starwarsmvpapp.presenters

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.inaki.starwarsmvpapp.model.characters.Character
import com.inaki.starwarsmvpapp.rest.StarWarsApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * This class will be your presenter that will handle the logic for the characters.
 *
 * Everytime we instantiate the presenter class we need to pass the view contract as a parameter
 *
 * @param characterViewContract This is the view contract that will be implemented in the fragment
 * @param disposables This parameter already initialized is to keep the rxjava calls and dispose those when needed
 */
class CharactersPresenter @Inject constructor(
    var networkApi: StarWarsApi,
    var connectivityManager: ConnectivityManager,
    private val disposables: CompositeDisposable = CompositeDisposable()
) : ICharactersPresenter {

    private var characterViewContract: ICharactersView? = null
    private var isNetworkAvailable = false

    override fun initPresenter(viewContract: ICharactersView) {
        characterViewContract = viewContract
    }

    // This method that is being override from the contract in presenter
    // this is one of the capabilities of our presenter
    // will make call to server and then retrieve the data
    override fun getCharacterFromServer() {
        // I am retrieving the character from server
        // changing to the worker thread
        // observing on the main thread
        // and subscribing to get the data
        if (isNetworkAvailable) {
            val starWarsDisposable = networkApi
                .retrieveCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { characters ->
                        // here I am calling the characters updated method from the view contract interface
                        // in order to update the view
                        characterViewContract?.characterUpdated(characters.characters)
                    },
                    { throwable ->
                        // I am updating hte view contract when an error occurs
                        characterViewContract?.onErrorData(throwable)
                    }
                )

            // adding the rxjava call into a disposable
            // we need to make sure we are disposing it at the right time
            disposables.add(starWarsDisposable)
        }
    }

    override fun checkNetworkConnection() {
        isNetworkAvailable = getActiveNetworkCapabilities()?.let {
            it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    it.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } ?: false
    }

    override fun destroyPresenter() {
        // here I am clearing hte disposable so we know when to dispose the call to the back end
        disposables.clear()
        // here I am resetting the view contract
        characterViewContract = null
    }

    private fun getActiveNetworkCapabilities(): NetworkCapabilities? {
        return connectivityManager.activeNetwork.let {
            connectivityManager.getNetworkCapabilities(it)
        }
    }

    //    companion object {
//        // Singleton design pattern
//
//        // this is the variable to store the instance of the presenter
//        // it has to be nullable, so we can check the value
//        private var presentInstance: CharactersPresenter? = null
//
//        // this method will get the instance of the presenter
//        // we pass any parameter needed for the presenter
//        fun getInstance(characterViewContract: ICharactersView): CharactersPresenter {
//            // we need to check the instance variable for a null value
//            // the second access to this instance is not going to be null
//            // so the check is false
//            if (presentInstance == null) {
//                // if it is null, we assign the a new instance for the presenter
//                presentInstance = CharactersPresenter(
//                    characterViewContract = characterViewContract
//                )
//            }
//
//            // here we return that instance
//            // for the second time we access this instance
//            // we are going to return this value directly
//            return presentInstance as CharactersPresenter
//        }
//    }
}

interface ICharactersPresenter {
    fun initPresenter(viewContract: ICharactersView)
    fun getCharacterFromServer()
    fun checkNetworkConnection()
    fun destroyPresenter()
}

interface ICharactersView {
    fun characterUpdated(characters: List<Character>)
    fun onErrorData(error: Throwable)
}