package com.example.partie1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UtilisateurViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDB.getInstance(application).utilisateurDao()
    val tousLesUtilisateurs: LiveData<List<Utilisateur>> = dao.getAllUtilisateurs()

    fun inserer(utilisateur: Utilisateur) {
        viewModelScope.launch {
            dao.inserer(utilisateur)
        }
    }

    fun modifier(utilisateur: Utilisateur) {
        viewModelScope.launch {
            dao.modifier(utilisateur)
        }
    }

    fun supprimer(utilisateur: Utilisateur) {
        viewModelScope.launch {
            dao.supprimer(utilisateur)
        }
    }

    fun supprimerTous() {
        viewModelScope.launch {
            dao.supprimerTous()
        }
    }
}
