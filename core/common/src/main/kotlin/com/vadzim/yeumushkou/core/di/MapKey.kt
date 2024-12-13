package com.vadzim.yeumushkou.core.di

import androidx.lifecycle.ViewModel
import com.vadzim.yeumushkou.core.di.deps.ComponentDependencies
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ComponentDependenciesClassKey(val key: KClass<out ComponentDependencies>)

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelClassKey(val key: KClass<out ViewModel>)