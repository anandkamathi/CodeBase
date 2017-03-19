package org.app.anand.gitinfobase.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by anand on 2/20/17.
 */
//PerActivity defines scope for dagger components
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
