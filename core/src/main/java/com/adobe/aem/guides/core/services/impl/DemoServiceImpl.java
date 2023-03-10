package com.adobe.aem.guides.core.services.impl;
import com.adobe.aem.guides.core.models.ResolverUtil;
import com.adobe.aem.guides.core.services.DemoService;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import java.util.Iterator;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = DemoService.class,immediate = true)
public class DemoServiceImpl implements DemoService {
    private static final Logger LOG= LoggerFactory.getLogger(DemoServiceImpl.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Reference
    private SlingRepository slingRepository;

    @Activate
    public void activate(ComponentContext componentContext){
        LOG.info("\n ==============INSIDE ACTIVATE================");
        LOG.info("\n {} = {} ",componentContext.getBundleContext().getBundle().getBundleId(),componentContext.getBundleContext().getBundle().getSymbolicName());
    }

    @Deactivate
    public void deactivate(ComponentContext componentContext){
        LOG.info("\n ==============INSIDE DEACTIVATE================");
    }

    @Modified
    public void modified(ComponentContext componentContext){
        LOG.info("\n ==============INSIDE MODIFIED================");
    }

    @Override
    public String  getPages(){
        try {
        	 ResourceResolver resourceResolver= ResolverUtil.newResolver1(resourceResolverFactory);
             PageManager pageManager=resourceResolver.adaptTo(PageManager.class);
             Page page=pageManager.getPage("/content/we-retail/us/en");
             Iterator<Page> pages=page.listChildren();
             String pageNames="";
             
             while (pages.hasNext()) {
            	 pageNames=pageNames+"\n"+pages.next().getTitle();
             }
             
             return pageNames;
        }
        catch (Exception e) {
            LOG.info("\n Login Exception {} ",e.getMessage());
        }
		return null;
       
    }

	
}