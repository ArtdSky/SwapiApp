package com.example.swapiapp.data.storage.network

/**
 * [ResponseWrapper] является sealed классом, предоставляющим различные варианты оберток для сетевых ответов.
 * Может содержать успешный результат [Success], информацию о сетевой ошибке  [NetworkError]
 * или обобщенную информацию об ошибке [GenericError].
 * @param  T  обобщенный тип данных, содержащихся в успешном результате [Success].
 */
sealed class ResponseWrapper<out T> {

    /**
     * Представляет успешный результат сетевого вызова.
     *
     * @property value значение результата сетевого вызова.
     */
    data class Success<out T>(val value: T) : ResponseWrapper<T>()

    /**
     * Представляет ошибку при сетевом вызове. (например нет интернета)
     */
    object NetworkError : ResponseWrapper<Nothing>()

    /**
     * Представляет обобщенную ошибку при сетевом вызове.(различные http ошибки)
     *
     * @property code код ошибки HTTP, если применимо.
     */
    data class GenericError(val code: Int? = null) : ResponseWrapper<Nothing>()
}
