package br.com.animes.feature.auth.data.remote.datasource

import br.com.animes.domain.utils.randomString
import br.com.animes.feature.auth.data.remote.model.LoginResponse
import br.com.animes.feature.auth.data.remote.service.LoginWebService
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.Credentials
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
@Suppress("ClassName")
class AuthRemoteDataSourceImplTest {
    private val service: LoginWebService = mockk()
    private val subject = AuthRemoteDataSourceImpl(loginWebService = service)

    @BeforeEach
    fun setupMocks() {
        mockkStatic(Credentials::class)
    }

    @AfterEach
    fun resetMocks() {
        clearAllMocks()
    }

    @Nested
    inner class `GIVEN a call on doLogin` {
        @Test
        fun `THEN call service with auth credentials basic`() = runBlockingTest {
            val user = randomString
            val password = randomString
            val encryptedCredential = randomString
            val loginResponseStub = mockk<LoginResponse>(relaxed = true)

            coEvery { Credentials.basic(any(), any()) } returns encryptedCredential
            coEvery { service.doLogin(any()) } returns loginResponseStub

            subject.doLogin(user = user, password = password)

            coVerify(exactly = 1) {
                Credentials.basic(user, password)
                service.doLogin(encryptedCredential)
            }
        }

        @Test
        fun `WHEN has a error on service THEN returns error`() = runBlockingTest {
            val exceptionExpected = HttpException(
                Response.error<HttpException>(400, mockk(relaxed = true))
            )

            coEvery { service.doLogin(any()) } throws exceptionExpected

            val result = subject.doLogin(randomString, randomString)
            assertTrue(result.isFailure)
        }

        @Test
        fun `WHEN has a success on service THEN return bearer token in login response`() = runBlockingTest {
            val bearerToken = randomString
            val loginResponse = LoginResponse(bearerToken)

            coEvery { service.doLogin(any()) } returns loginResponse
            val result = subject.doLogin(randomString, randomString)

            assertEquals(bearerToken, result.getOrNull())
        }
    }
}
