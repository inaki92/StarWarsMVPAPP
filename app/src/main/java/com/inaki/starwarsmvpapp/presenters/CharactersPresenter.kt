package com.inaki.starwarsmvpapp.presenters

import com.inaki.starwarsmvpapp.model.characters.Character
import com.inaki.starwarsmvpapp.rest.StarWarsRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * This class will be your presenter that will handle the logic for the characters.
 *
 * Everytime we instantiate the presenter class we need to pass the view contract as a parameter
 *
 * @param characterViewContract This is the view contract that will be implemented in the fragment
 * @param disposables This parameter already initialized is to keep the rxjava calls and dispose those when needed
 */
class CharactersPresenter(
    private val characterViewContract: ICharactersView,
    private val disposables: CompositeDisposable = CompositeDisposable()
) : ICharactersPresenter {

    // This method that is being override from the contract in presenter
    // this is one of the capabilities of our presenter
    // will make call to server and then retrieve the data
    override fun getCharacterFromServer() {
        // I am retrieving the character from server
        // changing to the worker thread
        // observing on the main thread
        // and subscribing to get the data
        val starWarsDisposable = StarWarsRetrofit.getNetworkApi()
            .retrieveCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { characters ->
                // here I am calling the characters updated method from the view contract interface
                // in order to update the view
                    characterViewContract.characterUpdated(characters.characters)
                },
                { throwable ->
                // I am updating hte view contract when an error occurs
                    characterViewContract.onErrorData(throwable)
                }
            )

        // adding the rxjava call into a disposable
        // we need to make sure we are disposing it at the right time
        disposables.add(starWarsDisposable)
    }

    override fun destroyPresenter() {
        // here I am clearing hte disposable so we know when to dispose the call to the back end
        disposables.clear()
    }
}

interface ICharactersPresenter {
    fun getCharacterFromServer()
    fun destroyPresenter()
}

interface ICharactersView {
    fun characterUpdated(characters: List<Character>)
    fun onErrorData(error: Throwable)
}