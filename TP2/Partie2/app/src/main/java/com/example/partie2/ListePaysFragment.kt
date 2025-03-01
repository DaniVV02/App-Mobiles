package com.example.partie2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment


class ListePaysFragment : Fragment() {

    private lateinit var listView: ListView
    private var listener: OnPaysSelectedListener? = null

    private val paysList = listOf(
        Pays("France", "Paris", 67000000),
        Pays("Allemagne", "Berlin", 83000000),
        Pays("Espagne", "Madrid", 49000000),
        Pays("Italie", "Rome", 58000000),
        Pays("Perou", "Lima", 31914989),
        Pays("Brazil", "Brasilia", 212583000),
        Pays("China", "Pekin", 1300000000)

    )

    interface OnPaysSelectedListener {
        fun onPaysSelected(pays: Pays)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPaysSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context doit implémenter OnPaysSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_liste_pays, container, false)
        listView = view.findViewById(R.id.listViewPays)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, paysList.map { it.nom })
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            listener?.onPaysSelected(paysList[position])
        }

        return view
    }
}