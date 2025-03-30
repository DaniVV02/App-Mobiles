package com.example.partie1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "utilisateur")
data class Utilisateur(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nom: String,
    val nomUtilisateur: String,
    val dateNaissance: String,
    val telephone: String,
    val email: String,
    val motDePasse: String,
    val centresInteret: String
)

