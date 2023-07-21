package com.example.testcachecontextbug.test;

import org.springframework.test.context.cache.ContextCache;
import org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate;

public class TestContextLoaderDelegate
    extends DefaultCacheAwareContextLoaderDelegate {

    @Override
    public ContextCache getContextCache() {
        return super.getContextCache();
    }
}
