package nworld.dev.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "nworld.dev.config.autoconfig.DispatcherServletConfig",
                "nworld.dev.config.autoconfig.TomcatWebServerConfig"
        };
    }
}
