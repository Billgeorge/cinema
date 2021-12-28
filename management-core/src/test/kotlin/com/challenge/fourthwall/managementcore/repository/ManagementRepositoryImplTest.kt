package com.challenge.fourthwall.managementcore.repository

import com.challenge.fourthwall.commons.dto.GenericResponse
import com.challenge.fourthwall.commons.dto.toEntity
import com.challenge.fourthwall.commons.entity.MovieShow
import com.challenge.fourthwall.commons.repository.MovieRepository
import com.challenge.fourthwall.commons.repository.MovieShowRepository
import com.challenge.fourthwall.commons.repository.RoomRepository
import com.challenge.fourthwall.commons.util.createMovieEntityTest
import com.challenge.fourthwall.commons.util.createMovieShow
import com.challenge.fourthwall.commons.util.createMovieShowDTO
import com.challenge.fourthwall.commons.util.createRoomEntityTest
import com.challenge.fourthwall.managementcore.controller.request.ChangeDateMovieShowRequest
import com.challenge.fourthwall.managementcore.controller.request.ChangePriceMovieShowRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*


@ExtendWith(MockitoExtension::class)
internal class ManagementRepositoryImplTest {

    @InjectMocks
    private lateinit var managementRepositoryImpl: ManagementRepositoryImpl

    @Mock
    private lateinit var movieShowRepository: MovieShowRepository

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var roomRepository: RoomRepository

    @Captor
    lateinit var movieShowCaptor: ArgumentCaptor<MovieShow>

    @Test
    fun `createMovieShow happy path`() {
        val movieId = "tt0322259"
        val roomId = UUID.randomUUID()

        Mockito.`when`(movieRepository.findById(movieId)).thenReturn(Optional.of(createMovieEntityTest()))
        Mockito.`when`(roomRepository.findById(roomId)).thenReturn(Optional.of(createRoomEntityTest()))
        Mockito.`when`(movieShowRepository.save(Mockito.any())).thenReturn(createMovieShow())

        val response = managementRepositoryImpl.createMovieShow(createMovieShowDTO(movieId, roomId))

        Assertions.assertTrue(response is GenericResponse.SuccessResponse)
    }

    @Test
    fun `createMovieShow movie does not exist`() {

        val movieId = "tt0322259"
        val roomId = UUID.randomUUID()

        Mockito.`when`(movieRepository.findById(movieId)).thenReturn(Optional.empty())

        val response = managementRepositoryImpl.createMovieShow(createMovieShowDTO(movieId, roomId))

        Assertions.assertTrue(response is GenericResponse.ErrorResponse)
    }

    @Test
    fun `createMovieShow room does not exist`() {

        val movieId = "tt0322259"
        val roomId = UUID.randomUUID()

        Mockito.`when`(movieRepository.findById(movieId)).thenReturn(Optional.of(createMovieEntityTest()))
        Mockito.`when`(roomRepository.findById(roomId)).thenReturn(Optional.empty())

        val response = managementRepositoryImpl.createMovieShow(createMovieShowDTO(movieId, roomId))

        Assertions.assertTrue(response is GenericResponse.ErrorResponse)
    }

    @Test
    fun `changePrice happy path`() {
        val request = createChangePriceMovieShowRequest()

        Mockito.`when`(movieShowRepository.findById(request.moviShowId!!)).thenReturn(
            Optional.of(
                createMovieShowDTO().toEntity(
                    createMovieEntityTest(), createRoomEntityTest()
                )
            )
        )
        Mockito.`when`(movieShowRepository.save(Mockito.any())).thenReturn(
            createMovieShowDTO().toEntity(
                createMovieEntityTest(), createRoomEntityTest()
            )
        )

        val response = managementRepositoryImpl.changePrice(request)

        Assertions.assertTrue(response is GenericResponse.SuccessResponse)
    }

    @Test
    fun `changePrice MovieShow does not exist`() {
        val request = createChangePriceMovieShowRequest()

        Mockito.`when`(movieShowRepository.findById(request.moviShowId!!)).thenReturn(Optional.empty())
        val response = managementRepositoryImpl.changePrice(request)

        Assertions.assertTrue(response is GenericResponse.ErrorResponse)
    }

    @Test
    fun `changeDateDuration MovieShow does not exist`() {
        val request = createChangeDateMovieShowRequest()

        Mockito.`when`(movieShowRepository.findById(request.movieShowId!!)).thenReturn(Optional.empty())
        val response = managementRepositoryImpl.changeDateAndDuration(request)

        Assertions.assertTrue(response is GenericResponse.ErrorResponse)
    }

    @Test
    fun `changeDateDuration only change initialDate`() {
        val request = createChangeDateMovieShowRequest(0L)
        val movieShow = createMovieShowDTO().toEntity(
            createMovieEntityTest(), createRoomEntityTest()
        )

        Mockito.`when`(movieShowRepository.findById(request.movieShowId!!)).thenReturn(
            Optional.of(
                movieShow
            )
        )
        Mockito.`when`(movieShowRepository.save(Mockito.any())).thenReturn(movieShow)


        val response = managementRepositoryImpl.changeDateAndDuration(request)
        Mockito.verify(movieShowRepository).save(movieShowCaptor.capture())
        val calculatedMovieShow = movieShowCaptor.value

        Assertions.assertTrue(response is GenericResponse.SuccessResponse)
        Assertions.assertTrue(calculatedMovieShow.initialDateTime == request.initialDateTime)
    }

    @Test
    fun `changeDateDuration change initialDate and duration`() {
        val minutes = 30L
        val request = createChangeDateMovieShowRequest(minutes)
        val movieShow = createMovieShowDTO().toEntity(
            createMovieEntityTest(), createRoomEntityTest()
        )

        Mockito.`when`(movieShowRepository.findById(request.movieShowId!!)).thenReturn(
            Optional.of(
                movieShow
            )
        )
        Mockito.`when`(movieShowRepository.save(Mockito.any())).thenReturn(movieShow)


        val response = managementRepositoryImpl.changeDateAndDuration(request)
        Mockito.verify(movieShowRepository).save(movieShowCaptor.capture())
        val calculatedMovieShow = movieShowCaptor.value

        Assertions.assertTrue(response is GenericResponse.SuccessResponse)
        Assertions.assertTrue(calculatedMovieShow.initialDateTime == request.initialDateTime)
        Assertions.assertTrue(calculatedMovieShow.duration == minutes)
        Assertions.assertTrue(calculatedMovieShow.finalDateTime == request.initialDateTime!!.plusMinutes(minutes))
    }


    private fun createChangePriceMovieShowRequest() = ChangePriceMovieShowRequest(
        moviShowId = UUID.randomUUID(), price = BigDecimal.TEN
    )

    private fun createChangeDateMovieShowRequest(duration: Long = 10L) = ChangeDateMovieShowRequest(
        movieShowId = UUID.randomUUID(), duration = duration, initialDateTime = LocalDateTime.now()
    )


}