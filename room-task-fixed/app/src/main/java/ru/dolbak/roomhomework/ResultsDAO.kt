package ru.dolbak.roomhomework

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ResultsDao {
    @Query("SELECT * FROM results ORDER BY :order")
    fun getAll(order: String): LiveData<List<ResultEntity>>
    @Insert
    fun insert(vararg result: ResultEntity)
    @Delete
    fun delete(result: ResultEntity)
    @Update
    fun update(vararg result: ResultEntity)

    @Query("DELETE FROM results WHERE name LIKE :query")
    fun deleteByName(query: String)

    @Query("SELECT SUM(result) FROM results")
    fun getTotalCapitalization(): LiveData<Int>

    @Query("SELECT COUNT(DISTINCT name) FROM results WHERE result > (SELECT AVG(result) FROM results)")
    fun getCompaniesAboveAverageCapitalization(): LiveData<Int>

    @Query("SELECT COUNT(DISTINCT name) FROM results WHERE name GLOB '*[A-Za-z]*'")
    fun getEnglishCompaniesCount(): LiveData<Int?>

    @Query("SELECT * FROM results ORDER BY result DESC LIMIT 1")
    fun getBestCompany(): LiveData<ResultEntity>

    @Query("SELECT name FROM results ORDER BY LENGTH(name) DESC LIMIT 1")
    fun getLongestCompanyName(): LiveData<String>
}