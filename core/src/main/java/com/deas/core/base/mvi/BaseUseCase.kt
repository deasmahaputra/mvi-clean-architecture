package com.deas.core.base.mvi

import com.deas.core.base.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.annotation.Nullable

abstract class BaseUseCase<Model, Params> {

    abstract suspend fun buildRequest(@Nullable params : Params?) : Flow<DataState<Model>>

    suspend fun execute(@Nullable params: Params?) : Flow<DataState<Model>> {
        return try {
            buildRequest(params)
        }catch (e : Exception){
            flow { emit(DataState.Error(e))}
        }
    }
}