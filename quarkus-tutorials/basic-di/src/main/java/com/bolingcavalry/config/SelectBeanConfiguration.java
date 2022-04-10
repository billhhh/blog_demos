package com.bolingcavalry.config;

import com.bolingcavalry.service.TryIfBuildProfile;
import com.bolingcavalry.service.TryLookupIfProperty;
import com.bolingcavalry.service.impl.*;
import io.quarkus.arc.DefaultBean;
import io.quarkus.arc.lookup.LookupIfProperty;
import io.quarkus.arc.profile.IfBuildProfile;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

public class SelectBeanConfiguration {

    @LookupIfProperty(name = "service.alpha.enabled", stringValue = "true")
    @ApplicationScoped
    public TryLookupIfProperty tryLookupIfPropertyAlpha() {
        return new TryLookupIfPropertyAlpha();
    }

    @LookupIfProperty(name = "service.beta.enabled", stringValue = "true")
    @ApplicationScoped
    public TryLookupIfProperty tryLookupIfPropertyBeta() {
        return new TryLookupIfPropertyBeta();
    }

    @Produces
    @IfBuildProfile("test")
    public TryIfBuildProfile tryIfBuildProfileProd() {
        return new TryIfBuildProfileProd();
    }

    @Produces
    @DefaultBean
    public TryIfBuildProfile tryIfBuildProfileDefault() {
        return new TryIfBuildProfileDefault();
    }

    @ApplicationScoped
    public ResourceManager getResourceManager() {
        return new ResourceManager();
    }

    public void closeResource(@Disposes ResourceManager resourceManager) {
        resourceManager.closeAll();
    }
}
