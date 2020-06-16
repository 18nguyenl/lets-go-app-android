package com.example.letsgo.data

import androidx.sqlite.db.SimpleSQLiteQuery

interface DataAccessObject<T> {

    fun insert(vararg elements: @JvmSuppressWildcards T)
    fun delete(vararg element: @JvmSuppressWildcards T)
    fun update(vararg element: @JvmSuppressWildcards T)

    fun getAll(): List<@JvmSuppressWildcards T>
    fun getByIDs(elementIDs: IntArray): List<@JvmSuppressWildcards T>
    fun fetchByID(elementID: Int): @JvmSuppressWildcards T
    fun getByQuery(query: SimpleSQLiteQuery): List<@JvmSuppressWildcards T>
    fun fetchByQuery(query: SimpleSQLiteQuery): @JvmSuppressWildcards T

}