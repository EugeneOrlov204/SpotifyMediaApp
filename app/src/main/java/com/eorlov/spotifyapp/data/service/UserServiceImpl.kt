package com.eorlov.spotifyapp.data.service

import com.eorlov.spotifyapp.data.model.SearchResult
import com.eorlov.spotifyapp.domain.service.UserService
import com.eorlov.spotifyapp.presentation.model.User
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

class UserServiceImpl(
    private val client: HttpClient,
) : UserService{
    override suspend fun getUser(accessToken: String): User {
        return try {
            client.get(URL_USER)  {
                headers {
                    append(HttpHeaders.Authorization, accessToken)
                }
            }
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            User()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            User()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            User()
        }
    }

    override suspend fun search(accessToken: String, q: String, type: String): SearchResult {
        return try {
            client.get(URL_SEARCH_ALBUMS)  {
                headers {
                    append(HttpHeaders.Authorization, accessToken)
                }
                parameter("q", q)
                parameter("type", type)
            }
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            SearchResult()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            SearchResult()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            SearchResult()
        }
    }

    companion object {
        private const val URL_USER = "https://api.spotify.com/v1/me"
        private const val URL_SEARCH_ALBUMS = "https://api.spotify.com/v1/search"
        fun create(): UserService {
            return UserServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.BODY
                    }

                    install(JsonFeature) {
                        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                            ignoreUnknownKeys = true
                            isLenient = true
                        })
                    }
                }
            )
        }
    }
}