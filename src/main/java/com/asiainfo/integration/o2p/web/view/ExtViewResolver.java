package com.asiainfo.integration.o2p.web.view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.asiainfo.foundation.log.Logger;

/**
 * @ClassName: ExtViewResolver
 * @Description: 
 * @author zhengpeng
 * @date 2016-7-12 上午10:57:26
 *
 */
public class ExtViewResolver implements ViewResolver{
	
	private static final Logger log = Logger.getLog(ExtViewResolver.class);
	
	private Map<Set<String>,ViewResolver> viewResolverMap = new HashMap<Set<String>,ViewResolver>();
    
    private ViewResolver defaultViewResolver = null;

	@Override
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		for (Map.Entry<Set<String>, ViewResolver> map : viewResolverMap.entrySet()) {
			Set<String> suffixs = map.getKey();
			for (String suffix : suffixs) {
				if (viewName.endsWith(suffix)) {
					ViewResolver viewResolver = map.getValue();
					if (null != viewResolver) {
						log.debug("found viewResolver '" + viewResolver + "' for viewName '" + viewName + "'");
						return viewResolver.resolveViewName(viewName, locale);
					}
				}
			}
		}

		if (defaultViewResolver != null) {
			return defaultViewResolver.resolveViewName(viewName, locale);
		}
		return null;
	}

	public Map<Set<String>, ViewResolver> getViewResolverMap() {
		return viewResolverMap;
	}

	public void setViewResolverMap(
			Map<Set<String>, ViewResolver> viewResolverMap) {
		this.viewResolverMap = viewResolverMap;
	}

	public ViewResolver getDefaultViewResolver() {
		return defaultViewResolver;
	}

	public void setDefaultViewResolver(ViewResolver defaultViewResolver) {
		this.defaultViewResolver = defaultViewResolver;
	}

}
