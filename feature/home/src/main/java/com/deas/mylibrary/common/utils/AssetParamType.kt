package com.deas.mylibrary.common.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.deas.mylibrary.domain.model.Categories
import com.google.gson.Gson

class AssetParamType : NavType<Categories>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Categories? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Categories {
        return Gson().fromJson(value, Categories::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Categories) {
        bundle.putParcelable(key, value)
    }
}