package org.openl.rules.mapping;

import java.io.File;

import org.openl.rules.runtime.ApiBasedRulesEngineFactory;
import org.openl.syntax.exception.CompositeOpenlException;
import org.openl.syntax.exception.SyntaxNodeException;

public class RulesBeanMapperFactory {

    public static RulesBeanMapper createMapperInstance(File source) {
        ApiBasedRulesEngineFactory factory = new ApiBasedRulesEngineFactory(source);
        Class<?> instanceClass;
        Object instance;

        try {
            instanceClass = factory.getInterfaceClass();
            instance = factory.makeInstance();
            
            if (factory.getCompiledOpenClass().hasErrors()) {
                // TODO: remove OpenL specific exception
                //
                throw new CompositeOpenlException("Compilation failed", new SyntaxNodeException[0], factory.getCompiledOpenClass().getMessages());
            }

            return new RulesBeanMapper(instanceClass, instance);
        } catch (Exception e) {
            throw new RulesMappingException(String.format("Cannot load mapping definitions from file: %s", source
                .getAbsolutePath()), e);
        }

    }
}