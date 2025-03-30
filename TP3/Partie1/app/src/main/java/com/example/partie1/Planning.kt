package com.example.partie1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planning")
data class Planning(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userEmail: String, // pour associer à un utilisateur
    val username: String, // pour associer à un utilisateur
    val jour: String,
    val creneau1: String,
    val creneau2: String,
    val creneau3: String,
    val creneau4: String
)
