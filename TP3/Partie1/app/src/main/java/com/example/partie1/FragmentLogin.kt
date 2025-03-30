package com.example.partie1

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentLogin : Fragment() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSoumettre: Button
    private lateinit var database: AppDB
    private lateinit var tvVersInscription: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        etEmail = view.findViewById(R.id.etEmail)
        etPassword = view.findViewById(R.id.etPassword)
        btnSoumettre = view.findViewById(R.id.btnSoumettre)

        database = AppDB.getInstance(requireContext())

        tvVersInscription = view.findViewById(R.id.tvVersInscription)

        tvVersInscription.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragmentContainer, FragmentInscription())
                addToBackStack(null)
            }
        }

        btnSoumettre.setOnClickListener {
            verifierLogin()
        }

        return view
    }

    private fun verifierLogin() {
        val emailOuNomUti = etEmail.text.toString()
        val motDePasse = etPassword.text.toString()

        lifecycleScope.launch {
            val utilisateur = withContext(Dispatchers.IO) {
                database.utilisateurDao().verifierLogin(emailOuNomUti, motDePasse)
            }

            if (utilisateur != null) {
                val intent = Intent(requireContext(), PlanningActivity::class.java)
                intent.putExtra("email", utilisateur.email)
                intent.putExtra("nomUtilisateur", utilisateur.nomUtilisateur)
                startActivity(intent)

            } else {
                Toast.makeText(requireContext(), "Identifiants incorrects", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
