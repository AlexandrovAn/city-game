package com.example.citygame.data.repositories

import com.example.citygame.data.api.CitiesService
import com.example.citygame.data.db.CitiesDao
import com.example.citygame.domain.repositories.CitiesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CitiesRepoImpl @Inject constructor(
    private val dao: CitiesDao,
    private val service: CitiesService
) : CitiesRepository {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            updateDB()
        }
    }

    private suspend fun updateDB() {
        dao.clear()
        dao.insertAll(service.getCities())
    }

    override suspend fun validation(word: String) = withContext(Dispatchers.IO) { dao.exist(word) }

}