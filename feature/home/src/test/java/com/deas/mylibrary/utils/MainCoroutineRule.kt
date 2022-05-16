package com.deas.mylibrary.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MainCoroutineRule(val dispacher : CoroutineDispatcher = TestCoroutineDispatcher()) : TestWatcher(), CoroutineScope by TestCoroutineScope(dispacher){

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispacher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}