package com.example.citygame.data.repositories

import com.example.citygame.data.db.SavedWordsDao
import com.example.citygame.data.entities.SavedWord
import com.example.citygame.domain.repositories.SavedWordsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SavedWordsRepoImpl @Inject constructor(
    private val dao: SavedWordsDao
) : SavedWordsRepository {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            dao.clear()
        }
    }

    override suspend fun validateRepeatWords(word: String) =
        withContext(Dispatchers.IO) { dao.exist(word) }

    override suspend fun saveWord(word: String) =
        withContext(Dispatchers.IO) { dao.saveWord(SavedWord(word)) }

}