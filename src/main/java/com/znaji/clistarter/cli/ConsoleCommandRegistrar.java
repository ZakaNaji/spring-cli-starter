package com.znaji.clistarter.cli;

import com.znaji.clistarter.cli.exception.DefaultCLIExceptionHandler;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.support.DefaultConversionService;
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

        //register mandatory services in case they were not already set by consumer:
        registerBeanIfAbsent(registry, "conversionService", DefaultConversionService.class);
        registerBeanIfAbsent(registry, "commandArgsBinder", CommandArgsBinder.class);
        registerBeanIfAbsent(registry, "defaultInputSource", DefaultInputSource.class);
        registerBeanIfAbsent(registry, "defaultOutputTarget", DefaultOutputTarget.class);
        registerBeanIfAbsent(registry, "commandUI", CommandUI.class);
        registerBeanIfAbsent(registry, "commandDiscoverer", CommandDiscoverer.class);
        registerBeanIfAbsent(registry, "defaultCLIExceptionHandler", DefaultCLIExceptionHandler.class);
        registerBeanIfAbsent(registry, "cliRunner", CliRunner.class);
    }

    private void registerBeanIfAbsent(BeanDefinitionRegistry registry, String beanName, Class<?> clazz) {
        if (!registry.containsBeanDefinition(beanName)) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
            beanDefinitionBuilder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        }
    }

    private String getBasePackage(AnnotationMetadata importingClassMetadata) {
        String className = importingClassMetadata.getClassName();
        int lastDot = className.lastIndexOf(".");
        return (lastDot != -1)? className.substring(0, lastDot) : "";
    }


}
