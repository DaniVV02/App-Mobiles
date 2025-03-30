package com.example.partie1

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlanningDao {

    @Insert
    suspend fun inserer(planning: Planning)

    @Query("SELECT * FROM planning WHERE userEmail = :email ORDER BY id DESC LIMIT 1")
    suspend fun getDernierPlanning(email: String): Planning?

    @Query("SELECT * FROM planning WHERE userEmail = :identifiant OR username = :identifiant")
    suspend fun getPlanningsParEmailOuUsername(identifiant: String): List<Planning>

    @Query("SELECT * FROM planning")
    fun getAllPlannings(): LiveData<List<Planning>>

    @Update
    suspend fun modifier(planning: Planning)

    @Delete
    suspend fun supprimer(planning: Planning)

    @Query("DELETE FROM planning")
    suspend fun supprimerTous()

    @Query("SELECT * FROM planning")
    suspend fun getAll(): List<Planning>

    @Query("SELECT * FROM planning WHERE id = :id")
    suspend fun getById(id: Int): Planning?
}
