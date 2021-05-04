package com.namshi.cardinput.model

import android.os.Parcelable

abstract class TokenParams(
    internal val attribution: Set<String> = emptySet()
) : Parcelable {
    abstract val typeDataParams: Map<String, Any>
}
