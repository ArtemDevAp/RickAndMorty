package com.uk.android.repository

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import javax.inject.Inject


class RickAndMortyRepositoryTest {

    @Inject
    lateinit var rickAndMortyRepository: RickAndMortyRepository

    @Test
    @DelicateCoroutinesApi
    fun getEpisodes() = runBlocking {

        val data = rickAndMortyRepository.getCharacters()

        val info = data.info

        println(info)


    }

    @Test
    fun getCharacters() {
    }

    @Test
    fun getLocation() {
    }
}