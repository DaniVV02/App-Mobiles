package com.example.partie2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailsPaysFragment : Fragment() {

    private lateinit var txtNom: TextView
    private lateinit var txtCapitale: TextView
    private lateinit var txtPopulation: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_pays, container, false)

        txtNom = view.findViewById(R.id.txtNom)
        txtCapitale = view.findViewById(R.id.txtCapitale)
        txtPopulation = view.findViewById(R.id.txtPopulation)

        arguments?.let {
            val nom = it.getString("nom")
            val capitale = it.getString("capitale")
            val population = it.getInt("population")

            txtNom.text = "Pays : $nom"
            txtCapitale.text = "Capitale : $capitale"
            txtPopulation.text = "Population : $population"
        }

        return view
    }

    companion object {
        fun newInstance(pays: Pays): DetailsPaysFragment {
            val fragment = DetailsPaysFragment()
            val args = Bundle()
            args.putString("nom", pays.nom)
            args.putString("capitale", pays.capitale)
            args.putInt("population", pays.population)
            fragment.arguments = args
            return fragment
        }
    }
}