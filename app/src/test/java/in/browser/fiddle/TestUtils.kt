@file:Suppress("NOTHING_TO_INLINE")

package `in`.browser.fiddle

/**
 * Use to implement an unimplemented method.
 */
inline fun unimplemented(): Nothing {
    throw NotImplementedError("Not implemented")
}
