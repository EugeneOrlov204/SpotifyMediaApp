package com.eorlov.spotifyapp.data.storage

import android.content.Context
import com.eorlov.spotifyapp.domain.storage.Storage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesStorage @Inject constructor(@ApplicationContext private val context: Context) :
    Storage {

    override fun save(key: String, value: String) {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putString(key, value)
        prefsEdit.apply()
    }


    override fun save(key: String, value: Int) {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()

        prefsEdit.putInt(key, value)
        prefsEdit.apply()
    }


    override fun save(key: String, value: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()


        prefsEdit.putBoolean(key, value)
        prefsEdit.apply()
    }


    override fun save(key: String, value: Long) {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()


        prefsEdit.putLong(key, value)
        prefsEdit.apply()
    }


    override fun save(key: String, value: Double) {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()


        prefsEdit.putFloat(key, value.toFloat())
        prefsEdit.apply()
    }


    override fun save(key: String, value: Float) {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()


        prefsEdit.putFloat(key, value)
        prefsEdit.apply()
    }

    override fun getString(key: String, default: String): String? {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        return prefs.getString(key, default)
    }


    override fun getFloat(key: String): Float {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        return prefs.getFloat(key, 0f)
    }


    override fun getBoolean(key: String): Boolean {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        return prefs.getBoolean(key, false)
    }


    override fun getBoolean(key: String, def: Boolean): Boolean {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        return prefs.getBoolean(key, def)
    }


    override fun getInt(key: String): Int {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        return prefs.getInt(key, 0)
    }


    override fun getInt(key: String, default: Int): Int {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        return prefs.getInt(key, default)
    }


    override fun getLong(key: String, default: Long): Long {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        return prefs.getLong(key, default)
    }


    override fun getLong(key: String): Long {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        return prefs.getLong(key, 0L)
    }

    override fun removePrefValue(key: String) {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val prefsEdit = prefs.edit()
        prefsEdit.remove(key)
        prefsEdit.apply()
    }


    override fun contains(key: String): Boolean {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        return prefs.contains(key)
    }


    override fun clearPrefs() {
        val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }

    companion object {
        private const val PREFS_FILE = "SHARED_PREFERENCESKEY"
    }
}