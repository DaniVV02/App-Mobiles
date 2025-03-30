package com.example.partie1

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UtilisateurDao {

    @Insert
    suspend fun inserer(utilisateur: Utilisateur)

    @Update
    suspend fun modifier(utilisateur: Utilisateur)

    @Delete
    suspend fun supprimer(utilisateur: Utilisateur)

    @Query("DELETE FROM utilisateur WHERE id = :id")
    suspend fun supprimerParId(id: Int)

    @Query("DELETE FROM utilisateur")
    suspend fun supprimerTous()
/*
    @Query("SELECT * FROM utilisateur")
    suspend fun getAllUtilisateurs(): List<Utilisateur>

 */

    @Query("SELECT * FROM utilisateur")
    fun getAllUtilisateurs(): LiveData<List<Utilisateur>> // LiveData<List><Utilisateur>

    @Query("SELECT * FROM utilisateur WHERE id = :id")
    suspend fun getUtilisateurById(id: Int): Utilisateur?

    @Query("SELECT * FROM utilisateur WHERE email = :email")
    suspend fun getUtilisateurByEmail(email: String): Utilisateur?

    // requete pour verif avec le email ou nom utilisateur
    @Query("SELECT * FROM utilisateur WHERE (email = :identifiant OR nomUtilisateur = :identifiant) AND motDePasse = :motDePasse LIMIT 1")
    suspend fun verifierLogin(identifiant: String, motDePasse: String): Utilisateur?


    @Query("SELECT COUNT(*) FROM utilisateur WHERE email = :email")
    suspend fun existeEmail(email: String ): Int

    @Query ("SELECT COUNT(*) FROM utilisateur WHERE nomUtilisateur = :nomUtilisateur")
    suspend fun existeNomUtilisateur(nomUtilisateur: String): Int



    @Query("SELECT * FROM utilisateur ORDER BY id DESC LIMIT 1")
    suspend fun getPremierUtilisateur(): Utilisateur
}
