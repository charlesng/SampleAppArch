package com.cn29.aac.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

@ExperimentalCoroutinesApi
class CoroutinesTestExtension(
        val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : BeforeEachCallback,
        AfterEachCallback,
        TestCoroutineScope by TestCoroutineScope(dispatcher) {

    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(dispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }
}

@ExperimentalCoroutinesApi
fun CoroutinesTestExtension.runBlocking(block: suspend () -> Unit) = this.dispatcher.runBlockingTest {
    block()
}

