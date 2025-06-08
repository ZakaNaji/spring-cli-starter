package com.znaji.clistarter.cli;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class ConsoleCommandRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        String basePackage = getBasePackage(importingClassMetadata);
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(CLICommand.class));
        for (BeanDefinition beanCandidate : scanner.findCandidateComponents(basePackage)) {
            registry.registerBeanDefinition(beanCandidate.getBeanClassName(), beanCandidate);
        }
    }

    private String getBasePackage(AnnotationMetadata importingClassMetadata) {
        String className = importingClassMetadata.getClassName();
        int lastDot = className.lastIndexOf(".");
        return (lastDot != -1)? className.substring(0, lastDot) : "";
    }
}
