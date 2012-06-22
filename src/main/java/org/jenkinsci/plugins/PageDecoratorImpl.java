package org.jenkinsci.plugins;

import hudson.Extension;
import hudson.model.PageDecorator;

/**
 * @author Kohsuke Kawaguchi
 */
@Extension
public class PageDecoratorImpl extends PageDecorator {
    public PageDecoratorImpl() {
        super(PageDecoratorImpl.class);
    }
}
