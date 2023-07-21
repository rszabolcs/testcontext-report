package com.example.testcachecontextbug.test;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;
import org.springframework.test.context.cache.DefaultContextCache;

public class TestContextCleanerListener implements TestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) {
        final DefaultContextCache defaultContextCache = (DefaultContextCache) new TestContextLoaderDelegate()
            .getContextCache();

        // NOTE: for some reason removeEldestEntry() in the LRU cache makes the cache itself unstable such a way
        // that WireMock server starts behaving as connection reset was happening all the time.
        // Resetting the cache instead of eviction solves this issue.
        if (defaultContextCache.size() >= defaultContextCache.getMaxSize()) {
            defaultContextCache.reset();
        }
    }
}
