package com.example.partie1

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlanningViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDB.getInstance(application).planningDao()

    private val _planningsParEmail = MutableLiveData<List<Planning>>()
    val planningsParEmail: LiveData<List<Planning>> get() = _planningsParEmail

    fun chargerPlannings(email: String) {
        viewModelScope.launch {
            val liste = withContext(Dispatchers.IO) {
                dao.getPlanningsParEmailOuUsername(email)
            }
            _planningsParEmail.value = liste
        }
    }

    fun inserer(planning: Planning) {
        viewModelScope.launch {
            dao.inserer(planning)
            chargerPlannings(planning.userEmail) // mise à jour après ajout
        }
    }

    fun modifier(planning: Planning) {
        viewModelScope.launch {
            dao.modifier(planning)
            chargerPlannings(planning.userEmail)
        }
    }

    fun supprimer(planning: Planning) {
        viewModelScope.launch {
            dao.supprimer(planning)
            chargerPlannings(planning.userEmail)
        }
    }

    fun supprimerTous(email: String) {
        viewModelScope.launch {
            dao.getPlanningsParEmailOuUsername(email).forEach {
                dao.supprimer(it)
            }
            chargerPlannings(email)
        }
    }
}
