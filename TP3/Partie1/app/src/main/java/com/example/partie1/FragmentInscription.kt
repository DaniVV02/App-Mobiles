package com.example.partie1

import android.os.Bundle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentInscription : Fragment() {

    private lateinit var etNom: EditText
    private lateinit var etUtilisateur: EditText
    private lateinit var etDateNaissance: EditText
    private lateinit var etTelephone: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var cbSport: CheckBox
    private lateinit var cbMusique: CheckBox
    private lateinit var cbLecture: CheckBox
    private lateinit var cbJV: CheckBox
    private lateinit var cbCinema: CheckBox
    private lateinit var cbDanse: CheckBox
    private lateinit var btnSoumettre: Button

    private lateinit var database: AppDB

    private lateinit var tvVersLogin: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inscription, container, false)

        // Initialisation des vues
        etNom = view.findViewById(R.id.etNom)
        etUtilisateur = view.findViewById(R.id.etUtilisateur)
        etDateNaissance = view.findViewById(R.id.etDateNaissance)
        etTelephone = view.findViewById(R.id.etTelephone)
        etEmail = view.findViewById(R.id.etEmail)
        etPassword = view.findViewById(R.id.etPassword)
        cbSport = view.findViewById(R.id.cbSport)
        cbMusique = view.findViewById(R.id.cbMusique)
        cbLecture = view.findViewById(R.id.cbLecture)
        cbJV = view.findViewById(R.id.cbJV)
        cbCinema = view.findViewById(R.id.cbCinema)
        cbDanse = view.findViewById(R.id.cbDanse)
        btnSoumettre = view.findViewById(R.id.btnSoumettre)

        database = AppDB.getInstance(requireContext())

        tvVersLogin = view.findViewById(R.id.tvVersLogin)

        tvVersLogin.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragmentContainer, FragmentLogin())
                addToBackStack(null)
            }
        }



        btnSoumettre.setOnClickListener {
            enregistrerUtilisateur()
        }

        return view
    }

    private fun enregistrerUtilisateur() {
        val nom = etNom.text.toString()
        val nomUtilisateur = etUtilisateur.text.toString()
        val dateNaissance = etDateNaissance.text.toString()
        val telephone = etTelephone.text.toString()
        val email = etEmail.text.toString()
        val motDePasse = etPassword.text.toString()

        val centres = mutableListOf<String>()
        if (cbSport.isChecked) centres.add("Sport")
        if (cbMusique.isChecked) centres.add("Musique")
        if (cbLecture.isChecked) centres.add("Lecture")
        if (cbJV.isChecked) centres.add("Jeux-vidéos")
        if (cbCinema.isChecked) centres.add("Cinéma")
        if (cbDanse.isChecked) centres.add("Danse")

        val centresInteret = centres.joinToString(", ")

        val utilisateur = Utilisateur(
            nom = nom,
            nomUtilisateur = nomUtilisateur,
            dateNaissance = dateNaissance,
            telephone = telephone,
            email = email,
            motDePasse = motDePasse,
            centresInteret = centresInteret
        )

        lifecycleScope.launch {
            if (!estValide(nomUtilisateur, motDePasse)) return@launch
            if (emailEstDejaPris(email)) {
                Toast.makeText(requireContext(), "Ce email est déjà utilisé.", Toast.LENGTH_SHORT).show()
                return@launch
            }

            if (nomUtilisateurEstDejaPris(nomUtilisateur)) {
                Toast.makeText(requireContext(), "Ce nom d'utilisateur est déjà utilisé.", Toast.LENGTH_SHORT).show()
                return@launch
            }

            withContext(Dispatchers.IO) {
                database.utilisateurDao().inserer(utilisateur)
            }

            //pour aller sur le fragment d'affichage
            parentFragmentManager.commit {
                replace(R.id.fragmentContainer, FragmentAffichage())
                addToBackStack(null)
            }
        }
    }

    private suspend fun emailEstDejaPris(email: String): Boolean {
        return withContext(Dispatchers.IO) {
            database.utilisateurDao().existeEmail(email) > 0
        }
    }

    private suspend fun nomUtilisateurEstDejaPris(nomUtilisateur: String): Boolean {
        return withContext(Dispatchers.IO) {
            database.utilisateurDao().existeNomUtilisateur(nomUtilisateur) > 0
        }
    }

    private fun estValide(nomUti: String, motDePasse: String): Boolean {
        val regexLogin = Regex("^[a-zA-Z][a-zA-Z0-9]{0,9}$") // commence par lettre, max 10 caractères
        val valideLogin = regexLogin.matches(nomUti)
        val valideMdp = motDePasse.length >= 6

        if (!valideLogin) {
            Toast.makeText(requireContext(), "Le nom d'utilisateur doit commencer par une lettre et contenir max 10 caractères.", Toast.LENGTH_LONG).show()
            return false
        }

        if (!valideMdp) {
            Toast.makeText(requireContext(), "Le mot de passe doit contenir au moins 6 caractères.", Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    fun viderChamps() {
        etNom.text.clear()
        etUtilisateur.text.clear()
        etDateNaissance.text.clear()
        etTelephone.text.clear()
        etEmail.text.clear()
        etPassword.text.clear()

        cbSport.isChecked = false
        cbMusique.isChecked = false
        cbLecture.isChecked = false
        cbJV.isChecked = false
        cbCinema.isChecked = false
        cbDanse.isChecked = false
    }

}