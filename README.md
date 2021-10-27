# What is this?

This plugin adds distinctive `path` attributes to every form element
inside Jenkins so that automated test programs like Selenium can be used
more effectively to automate/test Jenkins.

### How it works

With this plugin enabled, every control inside a form gets the `path`
attribute. The value of this attribute is something like
"/hudson-tasks-ArtifactArchiver/artifacts", and is modeled after XPath.

Configuration pages in Jenkins are assembled from small pieces that
plugins contribute. Furthermore, in general they form a tree structure,
and each such fragment can be used multiple times in different contexts.
For example, think of the [Conditional BuildStep
Plugin](https://github.com/jenkinsci/conditional-buildstep-plugin)
that allows you to add arbitrary builder as a nested builder inside the
"conditional build step" builder.

Because of this reusability of fragments, the `id` and `name` attributes
cannot be used reliably to point to a specific fragment. That's why we
need XPath-like expression.

### Find a path that's assigned to element

Use JavaScript DOM inspector in your browser to pick up 
INPUT/BUTTON/SELECT elements, and look for the `path` attribute.
