package com.eorlov.spotifyapp.domain.storage


interface Storage {
    fun save(key: String, value: String)
    fun save(key: String, value: Int)
    fun save(key: String, value: Boolean)
    fun save(key: String, value: Long)
    fun save(key: String, value: Double)
    fun save(key: String, value: Float)
    fun getString(key: String, default: String = ""): String?
    fun getFloat(key: String): Float
    fun getBoolean(key: String): Boolean
    fun getBoolean(key: String, def: Boolean): Boolean
    fun getInt(key: String): Int
    fun getInt(key: String, default: Int): Int
    fun getLong(key: String, default: Long): Long
    fun getLong(key: String): Long
    fun removePrefValue(key: String)
    fun contains(key: String): Boolean
    fun clearPrefs()
}