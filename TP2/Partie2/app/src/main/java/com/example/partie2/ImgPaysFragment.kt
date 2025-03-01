package com.example.partie2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ImgPaysFragment : Fragment() {

    private lateinit var imgView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)
        imgView = view.findViewById(R.id.imgFrag)


        arguments?.let {
            val nom = it.getString("nom")

            // association d' une image en fonction du pays
            val imageResId = getImageForCountry(nom)
            imgView.setImageResource(imageResId)
        }

        return view
    }


    companion object {
        private const val ARG_PAYS_NOM = "nom"

        fun newInstance(pays: Pays): ImgPaysFragment {
            val fragment = ImgPaysFragment()
            val args = Bundle()
            args.putString(ARG_PAYS_NOM, pays.nom)
            fragment.arguments = args
            return fragment
        }
    }

    // fct pour récupérer l'image correspondant au pays
    private fun getImageForCountry(paysNom: String?): Int {
        return when (paysNom) {
            "France" -> R.drawable.eiffel
            "Allemagne" -> R.drawable.berlin
            "Espagne" -> R.drawable.sagrasa_familia
            "Italie" -> R.drawable.rome
            "Perou" -> R.drawable.machu
            "Brazil" -> R.drawable.redentor
            "China" -> R.drawable.pekin
            else -> R.drawable.rome // Image par défaut si le pays n'est pas trouvé
        }
    }

}