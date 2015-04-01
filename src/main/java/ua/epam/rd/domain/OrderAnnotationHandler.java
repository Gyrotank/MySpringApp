package ua.epam.rd.domain;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class OrderAnnotationHandler implements ApplicationContextAware,
		InitializingBean {
	
	private ApplicationContext applicationContext;
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		final Map<String, Object> orderAnnotations = applicationContext.getBeansWithAnnotation(OrderAnnotation.class);
		 
	    for (final Object orderAnnotation : orderAnnotations.values()) {
	      final Class<? extends Object> orderAnnotationClass = orderAnnotation.getClass();
	      final OrderAnnotation annotation = orderAnnotationClass.getAnnotation(OrderAnnotation.class);
	      System.out.println("Found orderAnnotation class: " + orderAnnotationClass + ", with name: " + annotation.name());
	    }
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
