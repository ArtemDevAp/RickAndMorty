package com.uk.android.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.uk.android.data.EpisodePagingSource
import com.uk.android.data.RickiAndMortyApiService
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runners.MethodSorters
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import javax.inject.Inject

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@HiltAndroidTest
class RickAndMortyRepositoryTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var rickAndMortyRepository: RickAndMortyRepository

    @Inject
    lateinit var episodePagingSource: EpisodePagingSource

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getEpisodes() {
        val mockApi = mock(RickiAndMortyApiService::class.java)

        val data = runBlocking {
            given(mockApi.getPagedEpisode("https://rickandmortyapi.com/api/episode?page=1"))

            episodePagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = "https://rickandmortyapi.com/api/episode?page=1",
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        }

        Assert.assertTrue(data is PagingSource.LoadResult.Page)

    }
}